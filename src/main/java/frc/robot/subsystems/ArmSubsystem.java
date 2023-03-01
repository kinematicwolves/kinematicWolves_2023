// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  private final WPI_TalonFX m_leftOuterArm = new WPI_TalonFX(Constants.ArmProfile.LEFT_OUTER_ARM);
  private final WPI_TalonFX m_rightOuterArm = new WPI_TalonFX(Constants.ArmProfile.RIGHT_OUTER_ARM);
  private final WPI_TalonSRX m_leftInnerArm = new WPI_TalonSRX(Constants.ArmProfile.LEFT_INNER_ARM);
  private final WPI_TalonSRX m_rightInnerArm = new WPI_TalonSRX(Constants.ArmProfile.RIGHT_INNER_ARM);
  private final CANSparkMax m_wrist = new CANSparkMax(Constants.ArmProfile.WRIST_MOTOR, MotorType.kBrushless);
  private final RelativeEncoder m_wristEncoder = m_wrist.getEncoder(Type.kHallSensor, 42);

  private double m_setpoint;

  private final Timer m_timer = new Timer();
  
  /** Creates a new Arm. */
  public ArmSubsystem() {
    updateInnerArmMotionProfile();

    //m_leftInnerArm.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
   // m_rightInnerArm.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    m_leftInnerArm.setInverted(InvertType.InvertMotorOutput);
    m_rightInnerArm.setInverted(InvertType.InvertMotorOutput.None);
    m_leftInnerArm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 
    50, 0.5),10);
    m_rightInnerArm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 
    50, 0.5),10);
    m_leftInnerArm.configForwardSoftLimitEnable(true, 10);
    m_leftInnerArm.configReverseSoftLimitEnable(true, 10);
    m_leftInnerArm.configForwardSoftLimitThreshold(0);
    m_leftInnerArm.configReverseSoftLimitThreshold(5300);
    m_rightInnerArm.configForwardSoftLimitEnable(true, 10);
    m_rightInnerArm.configReverseSoftLimitEnable(true, 10);
    m_rightInnerArm.configForwardSoftLimitThreshold(0);
    m_rightInnerArm.configReverseSoftLimitThreshold(5300);

    m_setpoint = Constants.ArmProfile.INNER_POSITION_2;
  }

  public void setInnerTargetPosition(double _setpoint) {
    if (_setpoint != m_setpoint) {
      m_setpoint = _setpoint;
      updateInnerArmMotionProfile();
    }
  }

  private void updateInnerArmMotionProfile() {
    TrapezoidProfile.State state = new TrapezoidProfile.State(m_rightInnerArm.getSelectedSensorPosition(), m_rightInnerArm.getSelectedSensorVelocity());
    TrapezoidProfile.State goal = new TrapezoidProfile.State(m_setpoint, 0.0);
    new TrapezoidProfile(Constants.ArmProfile.INNER_ARM_MOTION_CONSTRAINT, goal, state);
    m_timer.reset();
  }
                                                                                                                                                                                                          
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("right_Inner_Arm", m_rightInnerArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("right_Outer_Arm", m_rightOuterArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("wrist", m_wristEncoder.getPosition());
  }
}
