// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  private final WPI_TalonFX m_leftOuterArm = new WPI_TalonFX(Constants.ArmProfile.LEFT_OUTER_ARM);
  private final WPI_TalonFX m_rightOuterArm = new WPI_TalonFX(Constants.ArmProfile.RIGHT_OUTER_ARM);
  private final WPI_TalonSRX m_leftInnerArm = new WPI_TalonSRX(Constants.ArmProfile.LEFT_INNER_ARM);
  private final WPI_TalonSRX m_rightInnerArm = new WPI_TalonSRX(Constants.ArmProfile.RIGHT_INNER_ARM);
  private final WPI_TalonSRX m_wrist = new WPI_TalonSRX(Constants.ArmProfile.WRIST_MOTOR);

  /** Creates a new Arm. */
  public ArmSubsystem() {
    /* Outer Arm Profile */
    m_leftOuterArm.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    m_rightOuterArm.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    m_leftOuterArm.setInverted(TalonFXInvertType.CounterClockwise);
    m_rightOuterArm.setInverted(TalonFXInvertType.Clockwise);
    m_leftOuterArm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 
    50, 0.5),10);
    m_rightOuterArm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 
    50, 0.5),10);

    /* Inner Arm Profile */
    m_leftInnerArm.configFactoryDefault(10);
    m_rightInnerArm.configFactoryDefault(10);
    m_rightInnerArm.setInverted(true);
    m_leftInnerArm.setInverted(false);
    // m_leftInnerArm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 35, 
    // 45, 0.5),10);
    // m_rightInnerArm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 35, 
    // 45, 0.5),10);

    /* Wrist Profile */
  }

  public void armSafteyZero() {
    double outerEncoderCounts = m_rightOuterArm.getSelectedSensorPosition();
    double innerEncoderCounts = m_rightInnerArm.getSelectedSensorPosition();
    double wristEncoderCounts = m_wrist.getSelectedSensorPosition();
  }
  //   if (outerEncoderCounts < 0) { // Saftey encoder position
  //     runOuterArm(0);
  //   }
  //   else if (outerEncoderCounts > 0) {
  //     runOuterArm(-0.1);
  //   }
  //   if (innerEncoderCounts < 0) {
  //     runInnerArm(0);
  //   }
  //   else if (innerEncoderCounts > 0) {
  //     runInnerArm(-0.1);
  //   }
  //   if (wristEncoderCounts < 0) {
  //     runWrist(0);
  //   }
  //   else if (wristEncoderCounts > 0) {
  //     runWrist(-0.1);
  //   }
  // }

  public void zeroArm() {
    // double outerEncoderCounts = m_rightOuterArm.getSelectedSensorPosition();
    // double innerEncoderCounts = m_rightInnerArm.getSelectedSensorPosition();
    // double wristEncoderCounts = m_wrist.getSelectedSensorPosition();

    // if (outerEncoderCounts <= 0) { // zero count
    //   runOuterArm(0);
    // }
    // else if (outerEncoderCounts > 0) {
    //   runOuterArm(-0.1);
    // } 
    // if (innerEncoderCounts <= 0) {
    // runInnerArm(0);
    // }
    // else if (outerEncoderCounts <= 0 & innerEncoderCounts > 0) {
    //   runInnerArm(0.1);
    // }
    // if (wristEncoderCounts <= 0) {
    //   runWrist(0);
    // }
    // else if (innerEncoderCounts <= 0 & wristEncoderCounts > 0) {
    //   runWrist(0.1);
    // }
  }

  public void setArmToMidNode() {
    double outerEncoderCounts = m_rightOuterArm.getSelectedSensorPosition();
    double innerEncoderCounts = m_rightInnerArm.getSelectedSensorPosition();
    double wristEncoderCounts = m_wrist.getSelectedSensorPosition();

    if (innerEncoderCounts <= -1600) { // Is at target position
      runInnerArm(0.165);
    }
    else if (innerEncoderCounts > -1600) { // Is before target position
      runInnerArm(0.48);
    }
  }

  public void setWristToMidNode() {
    var wristEncoderCounts = m_wrist.getSelectedSensorPosition();
    if (wristEncoderCounts <= -700) {
      runWrist(-0.09);
    }
    else if (wristEncoderCounts > -700) {
      runWrist(0.25);
    }
  }

  public void ZeroWrist() {
    var wristEncoderCounts = m_wrist.getSelectedSensorPosition();
    if (wristEncoderCounts <= 10) {
      runWrist(0);
    }
    else if (wristEncoderCounts <= 10) {
      runWrist(0.25);
    }
  }

  public void runOuterArm(double commandedOutFraction){
    m_leftOuterArm.set(commandedOutFraction);
    m_rightOuterArm.set(commandedOutFraction);
  }

  public void runInnerArm(double commandedOutputFraction){
    m_leftInnerArm.set(commandedOutputFraction);
    m_rightInnerArm.set(commandedOutputFraction);
  }

  public void runWrist(double commandedOutFraction) {
    m_wrist.set(commandedOutFraction);
  }

  @Override
  // This method will be called once per scheduler run
  public void periodic() {
    /* All encoder counts come from encoder one one arm (right side) */
    SmartDashboard.putNumber("Outer_Arm", m_rightOuterArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("Inner_Arm", m_rightInnerArm.getSelectedSensorPosition());
    SmartDashboard.putNumber("Wrist_Encoder_Pos", m_wrist.getSelectedSensorPosition());
  }
}
