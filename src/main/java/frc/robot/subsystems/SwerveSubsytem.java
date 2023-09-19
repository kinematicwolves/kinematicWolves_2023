package frc.robot.subsystems;

import java.lang.reflect.Field;
import java.util.function.Consumer;

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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Util.SwerveModule;

public class SwerveSubsytem extends SubsystemBase {
    public SwerveDriveOdometry swerveOdometry;
    public SwerveModule[] mSwerveMods;
    public Pigeon2 gyro;

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
        mSwerveMods[1].mDriveMotor.setInverted(true);

        Timer.delay(1.0);
        resetModulesToAbsolute();

        swerveOdometry = new SwerveDriveOdometry(Constants.SwerveProfile.swerveKinematics, getYaw(), getModulePositions());
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
        SwerveModuleState[] swerveModuleStates =
            Constants.SwerveProfile.swerveKinematics.toSwerveModuleStates(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                                    translation.getX(), 
                                    translation.getY(), 
                                    rotation, 
                                    getYaw()
                                )
                                );
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.SwerveProfile.maxSpeed);

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
        gyro.setYaw(90);
    }

    public Rotation2d getYaw() {
        return Rotation2d.fromDegrees(gyro.getYaw());
    }

    public void resetModulesToAbsolute() {
        for (SwerveModule mod : mSwerveMods) {
            mod.resetToAbsolute();
        }
    }

    @Override
    public void periodic() {
        swerveOdometry.update(getYaw(), getModulePositions());

        for (SwerveModule mod : mSwerveMods) {
            /* Date print */
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Raw Cancoder",
                    mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated Offset",
                    mod.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", 
                    mod.getState().speedMetersPerSecond);
            SmartDashboard.putNumber("Gyro",
                     gyro.getYaw());
                }
    }

    public Consumer<SwerveModuleState[]> setModuleStates() {
        return null;
    }
}