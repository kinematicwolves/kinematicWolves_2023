package frc.robot.Util;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;

import frc.robot.Constants;
import frc.robot.Constants.SwerveProfile;;

public final class CTREConfigs {
    public TalonFXConfiguration swerveAngleFXConfig;
    public TalonFXConfiguration swerveDriveFXConfig;
    public CANCoderConfiguration swerveCanCoderConfig;

    public CTREConfigs(){
        swerveAngleFXConfig = new TalonFXConfiguration();
        swerveDriveFXConfig = new TalonFXConfiguration();
        swerveCanCoderConfig = new CANCoderConfiguration();

        /* Swerve Angle Motor Configurations */
        SupplyCurrentLimitConfiguration angleSupplyLimit = new SupplyCurrentLimitConfiguration(
            Constants.SwerveProfile.angleEnableCurrentLimit, 
            Constants.SwerveProfile.angleContinuousCurrentLimit, 
            Constants.SwerveProfile.anglePeakCurrentLimit, 
            Constants.SwerveProfile.anglePeakCurrentDuration);

        swerveAngleFXConfig.slot0.kP = Constants.SwerveProfile.angleKP;
        swerveAngleFXConfig.slot0.kI = Constants.SwerveProfile.angleKI;
        swerveAngleFXConfig.slot0.kD = Constants.SwerveProfile.angleKD;
        swerveAngleFXConfig.slot0.kF = Constants.SwerveProfile.angleKF;
        swerveAngleFXConfig.supplyCurrLimit = angleSupplyLimit;

        /* Swerve Drive Motor Configuration */
        SupplyCurrentLimitConfiguration driveSupplyLimit = new SupplyCurrentLimitConfiguration(
            Constants.SwerveProfile.driveEnableCurrentLimit, 
            Constants.SwerveProfile.driveContinuousCurrentLimit, 
            Constants.SwerveProfile.drivePeakCurrentLimit, 
            Constants.SwerveProfile.drivePeakCurrentDuration);

        swerveDriveFXConfig.slot0.kP = Constants.SwerveProfile.driveKP;
        swerveDriveFXConfig.slot0.kI = Constants.SwerveProfile.driveKI;
        swerveDriveFXConfig.slot0.kD = Constants.SwerveProfile.driveKD;
        swerveDriveFXConfig.slot0.kF = Constants.SwerveProfile.driveKF;        
        swerveDriveFXConfig.supplyCurrLimit = driveSupplyLimit;
        swerveDriveFXConfig.openloopRamp = Constants.SwerveProfile.openLoopRamp;
        swerveDriveFXConfig.closedloopRamp = Constants.SwerveProfile.closedLoopRamp;
        
        /* Swerve CANCoder Configuration */
        swerveCanCoderConfig.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
        swerveCanCoderConfig.sensorDirection = Constants.SwerveProfile.canCoderInvert;
        swerveCanCoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
        swerveCanCoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;
    }
}