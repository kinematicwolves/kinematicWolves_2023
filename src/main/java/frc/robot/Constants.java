package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.robot.Util.Lib.util.COTSFalconSwerveConstants;
import frc.robot.Util.Lib.util.SwerveModuleConstants;

public final class Constants {
    
    public static final class ControllerProfile {
        public static final double stickDeadband = 0.1;
        public static final int DRIVER_CONTROLLER = 0;
        public static final int MUNIPULATOR_CONTROLLER = 1;
    }

    public static final class SwerveProfile {
        public static final int pigeonID = 13;
        public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW-

        public static final COTSFalconSwerveConstants chosenModule =  
            COTSFalconSwerveConstants.SDSMK4i(COTSFalconSwerveConstants.driveGearRatios.SDSMK4i_L2);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(20.52); 
        public static final double wheelBase = Units.inchesToMeters(20.52); 
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final boolean angleMotorInvert = chosenModule.angleMotorInvert;
        public static final boolean driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final boolean canCoderInvert = chosenModule.canCoderInvert;

        /* Swerve Current Limiting */
        public static final int angleContinuousCurrentLimit = 25;
        public static final int anglePeakCurrentLimit = 40;
        public static final double anglePeakCurrentDuration = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveContinuousCurrentLimit = 35;
        public static final int drivePeakCurrentLimit = 60;
        public static final double drivePeakCurrentDuration = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        public static final double angleKP = chosenModule.angleKP;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;
        public static final double angleKF = chosenModule.angleKF;

        /* Drive Motor PID Values */
        public static final double driveKP = 0.05; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values 
         * Divide SYSID values by 12 to convert from volts to percent output for CTRE */
        public static final double driveKS = (0.32 / 12); //TODO: This must be tuned to specific robot
        public static final double driveKV = (1.51 / 12);
        public static final double driveKA = (0.27 / 12);

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 4.5; 
        /** Radians per Second */
        public static final double maxAngularVelocity = 10.0; 

        /* Neutral Modes */
        public static final NeutralMode angleNeutralMode = NeutralMode.Coast;
        public static final NeutralMode driveNeutralMode = NeutralMode.Brake;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { 
            public static final int driveMotorID = 1;
            public static final int angleMotorID = 2;
            public static final int canCoderID = 3;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(71);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { 
            public static final int driveMotorID = 4;
            public static final int angleMotorID = 5;
            public static final int canCoderID = 6;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(71);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { 
            public static final int driveMotorID = 7;
            public static final int angleMotorID = 8;
            public static final int canCoderID = 9;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(77);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { 
            public static final int driveMotorID = 10;
            public static final int angleMotorID = 11;
            public static final int canCoderID = 12;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(210);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
    }

    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }

    /* Turret profile */
    public static final class TurretProfile {
        public static final int TURRET_MOTOR = 10; // FIXME: change this in phoenix tuner    
        public static final double TURRET_FORWARD_POSITION = 1.0; //FIXMEGODPLEASE
        public static final double TURRET_REVERSE_POSITION = -1.0; //fixmepleasealso
        public static final double TURRET_INITIAL_POSITION = 0; 
    }

    /* LED's Profile */
    public static final class LightProfile {
        /* CANdle ID's */
        public static final int ARM_CANDLE_ID = 0; //FIXME
        public static final int CHASSIS_CANDLE_ID = 0; //FIXME
        public static final int Arm_LED_COUNT = 308; //FIXME
        public static final int CHASSIS_LED_COUNT = 100; //FIXME
    }

    public static final class GripperProfile {
        /* Gripper ID's */
        public static final int LEFT_FINGER = 0; //FIXME
        public static final int RIGHT_FINGER = 0; //FIXME
        public static final int DISTANCE_SENSOR = 0; //FIXME
        /* Current limits for Neo 550's 
         * Stall Limit - the current limit in amps at 0 rpm
         * Free Limit - the current limit at free speed (11000 for neo 550's)
         * Limit RPM - less than this value will be set to the stall limit, rpm  values greater than limit rpm will scale linearly to free limit 
        */
        public static final int GRIPPER_CURRENT_STALL_LIMIT = 10;
        public static final int GRIPPER_CURRENT_FREE_LIMIT = 11000;
        public static final int GRIPPER_CURRENT_LIMIT_RPM = 2;
    }

    public static final class PneumaticProfile {
        public static final int PNEUMATIC_HUB_ID = 0; // FIXME
        /* Pnuematic Hub Ports */
        public static final int GRIPPER_SOL_FWD = 0; // FIXME
        public static final int GRIPPER_SOL_RVS = 0; // FIXME
        /* Air Pressure */
        public static final int MIN_AIR_PRESSURE = 60;
        public static final int MAX_AIR_PRESSURE = 120;
    }

    public static final class ArmProfile {
        /* Arm ID's */
        public static final int LEFT_OUTER_ARM = 0; //FIXME
        public static final int RIGHT_OUTER_ARM = 0; //FIXME
        public static final int LEFT_INNER_ARM = 0; //FIXME
        public static final int RIGHT_INNER_ARM = 0; //FIXME
        public static final int WRIST_MOTOR = 0; //FIXME
        /* Arm Gear Ratios (gearbox + sprocket)*/
        public static final double OUTER_ARM_GEAR_RATIO = 28/1 + 44/14;
        public static final double INNER_ARM_GEAR_RATIO = 90/1 + 44/15;
        public static final double WRIST_GEAR_RATIO = 25/1;
    }

    /* Falcon counts per rotation */
    public static final int FALCON_ENCODER_COUNTS = 2048;
}
