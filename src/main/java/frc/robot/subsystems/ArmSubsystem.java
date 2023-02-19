// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  private final WPI_TalonFX m_leftOuterArm = new WPI_TalonFX(Constants.ArmProfile.LEFT_OUTER_ARM);
  private final WPI_TalonFX m_rightOuterArm = new WPI_TalonFX(Constants.ArmProfile.RIGHT_OUTER_ARM);
  private final WPI_TalonSRX m_leftInnerArm = new WPI_TalonSRX(Constants.ArmProfile.LEFT_INNER_ARM);
  private final WPI_TalonSRX m_rightInnerArm = new WPI_TalonSRX(Constants.ArmProfile.RIGHT_INNER_ARM);
  private final CANSparkMax m_wrist = new CANSparkMax(Constants.ArmProfile.WRIST_MOTOR, MotorType.kBrushless);

  /** Creates a new Arm. */
  public ArmSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
