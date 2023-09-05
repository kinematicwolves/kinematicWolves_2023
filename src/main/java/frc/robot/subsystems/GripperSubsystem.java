
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GripperSubsystem extends SubsystemBase {

  private final VictorSPX m_leftFingerMotor = new VictorSPX(Constants.GripperProfile.FINGER_ONE_ID);
  private final VictorSPX m_rightFingerMotor = new VictorSPX(Constants.GripperProfile.FINGER_TWO_ID);
  // private final CANSparkMax m_leftFingerMotor = new CANSparkMax(0, MotorType.kBrushless);
  // private final CANSparkMax m_rightFingerMotor = new CANSparkMax(0, MotorType.kBrushless);

  private boolean gripperIsOpen = false;

  /** Creates a new GripperSubsystem. */
  public GripperSubsystem() {
    m_leftFingerMotor.setInverted(false);
    m_rightFingerMotor.setInverted(true);
  }

  public boolean isGripperOpen() {
    return gripperIsOpen;
  }

  private void runFingerMotors(double commandedOutputFraction) {
    m_leftFingerMotor.set(ControlMode.PercentOutput, commandedOutputFraction);
    m_rightFingerMotor.set(ControlMode.PercentOutput, commandedOutputFraction);
  }

  public void setGripperOpen(AirSubsystem airSubsystem) {
    airSubsystem.openGriper();
    runFingerMotors(0);
    gripperIsOpen = true;
  }

  public void setGripperClosed(AirSubsystem airSubsystem) {
    airSubsystem.closeGriper();
    runFingerMotors(0.1);
    gripperIsOpen = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Gripper_Is_Open", isGripperOpen());
  }
}