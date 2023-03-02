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
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {


  private double m_setpoint;
  private TrapezoidProfile m_profile;
  private TrapezoidProfile.State targetState;
  private double feedforward;

  private final Timer m_timer = new Timer();
  private final WPI_TalonFX m_leftOuterArm = new WPI_TalonFX(Constants.ArmProfile.LEFT_OUTER_ARM);
  private final WPI_TalonFX m_rightOuterArm = new WPI_TalonFX(Constants.ArmProfile.RIGHT_OUTER_ARM);
  private final WPI_TalonSRX m_leftInnerArm = new WPI_TalonSRX(Constants.ArmProfile.LEFT_INNER_ARM);
  private final WPI_TalonSRX m_rightInnerArm = new WPI_TalonSRX(Constants.ArmProfile.RIGHT_INNER_ARM);
  private final CANSparkMax m_wrist = new CANSparkMax(Constants.ArmProfile.WRIST_MOTOR, MotorType.kBrushless);
  private final RelativeEncoder m_wristEncoder = m_wrist.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
  private final SparkMaxPIDController m_pidcontroller = m_wrist.getPIDController();

  /** Creates a new Arm. */
  public ArmSubsystem() {
    updateOuterArmMotionProfile();

    m_leftOuterArm.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    m_rightOuterArm.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    m_leftOuterArm.setInverted(TalonFXInvertType.CounterClockwise);
    m_rightOuterArm.setInverted(TalonFXInvertType.Clockwise);
    m_leftOuterArm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 
    50, 0.5),10);
    m_rightOuterArm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 
    50, 0.5),10);
    m_leftOuterArm.configForwardSoftLimitEnable(true, 10);
    m_leftOuterArm.configReverseSoftLimitEnable(true, 10);
    m_leftOuterArm.configForwardSoftLimitThreshold(0);
    m_leftOuterArm.configReverseSoftLimitThreshold(5300);
    m_rightOuterArm.configForwardSoftLimitEnable(true, 10);
    m_rightOuterArm.configReverseSoftLimitEnable(true, 10);
    m_rightOuterArm.configForwardSoftLimitThreshold(0);
    m_rightOuterArm.configReverseSoftLimitThreshold(5300);

    m_setpoint = Constants.ArmProfile.OUTER_POSITION_2;
  }

  public void runWrist(double commandedOutFraction){
    m_wrist.set(commandedOutFraction);
  }

  public void runOuterArm(double commandedOutFraction){
    m_leftOuterArm.set(-1 * commandedOutFraction);
    m_rightOuterArm.set(commandedOutFraction);
  }
  public void setOuterTargetPosition(double _setpoint) {
    if (_setpoint != m_setpoint) {
      m_setpoint = _setpoint;
      updateOuterArmMotionProfile();
    }
  }

  private void updateOuterArmMotionProfile() {
    TrapezoidProfile.State state = new TrapezoidProfile.State(m_rightOuterArm.getSelectedSensorPosition(), m_rightOuterArm.getSelectedSensorVelocity());
    TrapezoidProfile.State goal = new TrapezoidProfile.State(m_setpoint, 0.0);
    new TrapezoidProfile(Constants.ArmProfile.OUTER_ARM_MOTION_CONSTRAINT, goal, state);
    m_timer.reset();
  }

  // public void runAutomatic() {
  //   double elapsedTime = m_timer.get();
  //   if (m_profile.isFinished(elapsedTime)) {
  //     targetState = new TrapezoidProfile.State(m_setpoint, 0.0);
  //   }
  //   else {
  //     targetState = m_profile.calculate(elapsedTime);
  //   }

  //   feedforward = Constants.ArmProfile.kArmFeedforward.calculate(m_rightOuterArm.getSelectedSensorPosition()+Constants.ArmProfile.kArmZeroCosineOffset
  //   ,targetState.velocity);
  //   m_pidcontroller.setReference(targetState.position, CANSparkMax.ControlType.kPosition, 0, feedforward);
  //   //m_rightOuterArm.
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("right_Outer_Arm", m_rightOuterArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("right_Inner_Arm", m_rightInnerArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("wrist", m_wristEncoder.getPosition());
  }
}
