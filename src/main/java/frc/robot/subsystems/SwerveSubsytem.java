package frc.robot.subsystems;

import java.util.function.Consumer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Util.SwerveModule;

public class SwerveSubsytem extends SubsystemBase {
    public SwerveDriveOdometry swerveOdometry;
    public SwerveModule[] mSwerveMods;
    public Pigeon2 gyro;
    private static final double alignWindow = 2;
    private static final double distancealignWindow = 48;


    public boolean speedIsLimited = false;

    public SwerveSubsytem() {
        gyro = new Pigeon2(Constants.SwerveProfile.pigeonID, "canivore1");
        gyro.configFactoryDefault();
        zeroGyro();

        mSwerveMods = new SwerveModule[] {
            new SwerveModule(0, Constants.SwerveProfile.Mod0.constants),
            new SwerveModule(1, Constants.SwerveProfile.Mod1.constants),
            new SwerveModule(2, Constants.SwerveProfile.Mod2.constants),
            new SwerveModule(3, Constants.SwerveProfile.Mod3.constants)

        };

        mSwerveMods[3].mDriveMotor.setInverted(true);

        /*
         * By pausing init for a second before setting module offsets, we avoid a bug
         * with inverting motors.
         * See https://github.com/Team364/BaseFalconSwerve/issues/8 for more info.
         */
        Timer.delay(1.0);
        resetModulesToAbsolute();

        swerveOdometry = new SwerveDriveOdometry(Constants.SwerveProfile.swerveKinematics, getYaw(), getModulePositions());
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        SwerveModuleState[] swerveModuleStates =
            Constants.SwerveProfile.swerveKinematics.toSwerveModuleStates(
                true ? ChassisSpeeds.fromFieldRelativeSpeeds(
                                    translation.getX(), 
                                    translation.getY(), 
                                    rotation, 
                                    getYaw()
                                )
                                : new ChassisSpeeds(
                                    translation.getX(), 
                                    translation.getY(), 
                                    rotation)
                                );
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.SwerveProfile.maxSpeed);
        SmartDashboard.putNumber("Swerve Yaw", gyro.getYaw());

        for (SwerveModule mod : mSwerveMods) {
            mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
        }
    }

    public void deadCat()
    {
        SwerveModuleState[] moduleStates = new SwerveModuleState[4] ;
        moduleStates[0] = new SwerveModuleState(0,Rotation2d.fromDegrees(225));
        moduleStates[1] = new SwerveModuleState(0,Rotation2d.fromDegrees(135));
        moduleStates[2] = new SwerveModuleState(0,Rotation2d.fromDegrees(315));
        moduleStates[3] = new SwerveModuleState(0,Rotation2d.fromDegrees(45));
        setModuleStatesOverride(moduleStates);
    }
    
    public void setModuleStatesOverride(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.SwerveProfile.maxSpeed);
        
        for(SwerveModule mod : mSwerveMods){
            mod.setDesiredStateOverride(desiredStates[mod.moduleNumber], false);
        }
    }  

    public boolean isSpeedLimited() {
        return speedIsLimited;
    }
/* 
    public void limitDriveTrainSpeed(XboxController driverController) {
        if (speedIsLimited) {
            drive(new Translation2d(0.3 * driverController.getLeftY(), 0.3 * driverController.getLeftX()), 
            0.3 * driverController.getRightX(), true, false);
        }
    }
*/
    public void enableSpeedLimit() {
        speedIsLimited = true;   
    }

    public void disableSpeedLimit() {
        speedIsLimited = false;
    }


    /* Used by SwerveControllerCommand in Auto */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.SwerveProfile.maxSpeed);
        
        for(SwerveModule mod : mSwerveMods){
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }

    public Pose2d getPose() {
        return swerveOdometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        swerveOdometry.resetPosition(getYaw(), getModulePositions(), pose);
    }

    public SwerveModuleState[] getModuleStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for (SwerveModule mod : mSwerveMods) {
            states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }

    public SwerveModulePosition[] getModulePositions() {
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for (SwerveModule mod : mSwerveMods) {
            positions[mod.moduleNumber] = mod.getPosition();
        }
        return positions;
    }

    public void zeroGyro() {
        gyro.setYaw(180);
    }

    public Rotation2d getYaw() {
        // return (Constants.SwerveProfile.invertGyro) ? Rotation2d.fromDegrees(360 - gyro.getYaw()) : Rotation2d.fromDegrees(gyro.getYaw());
        return Rotation2d.fromDegrees(gyro.getYaw());
    }

    public void resetModulesToAbsolute() {
        for (SwerveModule mod : mSwerveMods) {
            mod.resetToAbsolute();
        }
    }

    public boolean isLinedUp(VisionSubsystem visionSubsystem) {
        var horizalAngle = visionSubsystem.getFilteredHorizontalAngle();
        // degrees
        return (horizalAngle < alignWindow) & (horizalAngle > (-1 * alignWindow));
    }

    public void strafeDrivetrainToTarget(double strafeSpeed, VisionSubsystem visionSubsystem) {
        // This assumes the limelight has a target
        var horizalAngle = visionSubsystem.getFilteredHorizontalAngle();
        if (isLinedUp(visionSubsystem)) {
            /* Lined up Horazontally */
            ChassisSpeeds.fromFieldRelativeSpeeds(0, 0, 0, getYaw());
        } else if (horizalAngle < (-1 * alignWindow)) {
            /* Strafe right */
            ChassisSpeeds.fromFieldRelativeSpeeds(-1 * strafeSpeed, 0, 0, getYaw());
        } else if (horizalAngle > alignWindow) {
            /* Strafe left */
            ChassisSpeeds.fromFieldRelativeSpeeds(strafeSpeed, 0, 0, getYaw());
        }
    }

    public boolean isLinedUpInDistance(VisionSubsystem visionSubsystem) {
        var distance = visionSubsystem.getFilteredDistance();
        return (distance == alignWindow);
    }

    public void yTranslateDrivetrainToTarget(double yTranslationSpeed, VisionSubsystem visionSubsystem) {
        var distance = visionSubsystem.getFilteredDistance();
        if (isLinedUpInDistance(visionSubsystem) & isLinedUp(visionSubsystem)){
            ChassisSpeeds.fromFieldRelativeSpeeds( 0, yTranslationSpeed, 0, getYaw());
        }
            else if (distance < (-1 * distancealignWindow)) {
                ChassisSpeeds.fromFieldRelativeSpeeds(0, 1 * yTranslationSpeed, 0, getYaw());
        }
            else if (distance > distancealignWindow){
                ChassisSpeeds.fromFieldRelativeSpeeds(0, yTranslationSpeed, 0, getYaw());
            }
    }

    @Override
    public void periodic() {
        swerveOdometry.update(getYaw(), getModulePositions());

        for (SwerveModule mod : mSwerveMods) {
            /* Date print */
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Cancoder (This_one_Jose) ",
                    mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);
            SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());
        }
    }

    public Consumer<SwerveModuleState[]> setModuleStates() {
        return null;
    }
}