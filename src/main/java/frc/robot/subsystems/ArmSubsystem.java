// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  private final WPI_TalonFX m_leftInnerArm = new WPI_TalonFX(Constants.ArmProfile.LEFT_INNER_ARM);
  private final WPI_TalonFX m_rightInnerArm = new WPI_TalonFX(Constants.ArmProfile.RIGHT_INNER_ARM);
  private final WPI_TalonSRX m_leftOuterArm = new WPI_TalonSRX(Constants.ArmProfile.LEFT_OUTER_ARM);
  private final WPI_TalonSRX m_rightOuterArm = new WPI_TalonSRX(Constants.ArmProfile.RIGHT_OUTER_ARM);
  private final CANSparkMax m_wrist = new CANSparkMax(Constants.ArmProfile.WRIST_MOTOR, MotorType.kBrushless);

  /** Creates a new Arm. */
  public ArmSubsystem() {
    m_leftInnerArm.configFactoryDefault();
    m_rightOuterArm.configFactoryDefault();
    m_leftOuterArm.configFactoryDefault();
    m_rightInnerArm.configFactoryDefault();
    m_wrist.restoreFactoryDefaults();
  }

  public void moveArmToFwdCommandedPos(double innerArmPosCount, double outerArmPosCount, double wristPosCount) {
      var innerArmEncoderCount = m_rightInnerArm.getSelectedSensorPosition();
      var outerArmEncoderCount = m_rightOuterArm.getSelectedSensorPosition();
      var wristEncoderCount = m_wrist.getEncoder(Type.kHallSensor, 42); // 25:1
  }

  public void moveArmToRvsCommandedPos(double innerArmPosCount, double outerArmPosCount, double wristPosCount) {
    var innerArmEncoderCount = m_rightInnerArm.getSelectedSensorPosition();
    var outerArmEncoderCount = m_rightOuterArm.getSelectedSensorPosition();
    var wristEncoderCount = m_wrist.getEncoder(Type.kHallSensor, 42); // 25:1
    // setInnerArmOutput(Constants.ArmProfile.INNER_ARM_DEFAULT_OUTPUT) == 
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



  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
