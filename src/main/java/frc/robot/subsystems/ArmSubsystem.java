// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder.Type;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  private final WPI_TalonFX m_leftInnerArm = new WPI_TalonFX(Constants.ArmProfile.LEFT_INNER_ARM);
  private final WPI_TalonFX m_rightInnerArm = new WPI_TalonFX(Constants.ArmProfile.RIGHT_INNER_ARM);
  private final WPI_TalonSRX m_leftOuterArm = new WPI_TalonSRX(Constants.ArmProfile.LEFT_OUTER_ARM);
  private final WPI_TalonSRX m_rightOuterArm = new WPI_TalonSRX(Constants.ArmProfile.RIGHT_OUTER_ARM);
  private final CANSparkMax m_wrist = new CANSparkMax(Constants.ArmProfile.WRIST_MOTOR, MotorType.kBrushless);
  private final RelativeEncoder m_wristEncoder = m_wrist.getEncoder(Type.kHallSensor, 42);

  //private final Timer m_timer;

  private TrapezoidProfile m_profile;
  private TrapezoidProfile.State m_state;  

  private final TrapezoidProfile.State state = new TrapezoidProfile.State(m_rightInnerArm.getSelectedSensorPosition(), m_rightInnerArm.getSelectedSensorVelocity());
  private final TrapezoidProfile.State goal = new TrapezoidProfile.State( 0, 0);

  private String innerArmState = "init State";

  private double m_setpoint;
  
  /** Creates a new Arm. */
  public ArmSubsystem() {
    updateInnerArmMotionProfile();
    m_leftInnerArm.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    m_rightInnerArm.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    

    m_setpoint = Constants.ArmProfile.INNER_POSITION_0;

    // m_timer = new Timer();
    // m_timer.start();
    // m_timer.reset();
  }

//   public void moniterArmStatePos1() {
//     if (getInnerArmPos() > 0 ) {
//     innerArmState = "Test state";
//   }
//     else {
//       innerArmState = "Out of Bounds";
//   }  
// }

public void setTargetPosition(double _setpoint) {
  if (_setpoint != m_setpoint) {
    m_setpoint = _setpoint;
    updateInnerArmMotionProfile();
  }
}

private void updateInnerArmMotionProfile() {
  TrapezoidProfile.State state = new TrapezoidProfile.State(m_rightInnerArm.getSelectedSensorPosition(), m_rightInnerArm.getSelectedSensorVelocity());
  TrapezoidProfile.State goal = new TrapezoidProfile.State(m_setpoint, 0.0);
  m_profile = new TrapezoidProfile(Constants.ArmProfile.INNER_ARM_MOTION_CONSTRAINT, goal, state);
 // m_timer.reset();
}

public String getInnerArmState() {
  return innerArmState;
}

  public void getArmEncoderPositions() {
    getInnerArmPos();
    getOuterArmPos();
    getWristPos();
  }

  private void getInnerArmPos() {
    m_rightInnerArm.getSelectedSensorPosition();
  }

  private double getOuterArmPos() {
    return m_rightOuterArm.getSelectedSensorPosition(); 
  }

  private double getWristPos() {
    return m_wristEncoder.getPosition();
  }

  public void setInnerArmOutput(double commandedOutputFraction) {
    m_leftInnerArm.set(commandedOutputFraction);
    m_rightInnerArm.set(commandedOutputFraction);
  }

  public void setOuterArmOutput(double commandedOutputFraction) {
    m_leftOuterArm.set(commandedOutputFraction);
    m_rightOuterArm.set(commandedOutputFraction);
  }

  public void setWristArmOutput(double commandedOutputFraction) {
    m_wrist.set(commandedOutputFraction);
  }

  // public void runManual(double _power) {
  //   //reset and zero out a bunch of automatic mode stuff so exiting manual mode happens cleanly and passively
  //   m_setpoint = m_encoder.getPosition();
  //   targetState = new TrapezoidProfile.State(m_setpoint, 0.0);
  //   m_profile = new TrapezoidProfile(Constants.Arm.kArmMotionConstraint, targetState, targetState);
  //   //update the feedforward variable with the newly zero target velocity
  //   feedforward = Constants.Arm.kArmFeedforward.calculate(m_encoder.getPosition()+Constants.Arm.kArmZeroCosineOffset, targetState.velocity);
  //   m_motor.set(_power + (feedforward / 12.0));
  //   manualValue = _power;
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // moniterArmStatePos1();

    SmartDashboard.putNumber("moniterArmStatePos1", m_rightInnerArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("outer_arm_pos1", m_rightInnerArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("inner_arm_pos1", m_rightOuterArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("wrist_pos1", m_wristEncoder.getPosition());

  }
}
