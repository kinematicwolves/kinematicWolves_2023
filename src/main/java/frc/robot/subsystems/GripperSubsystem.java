// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GripperSubsystem extends SubsystemBase {
  private WPI_VictorSPX m_Finger1 = new WPI_VictorSPX(Constants.GripperProfile.FINGER_ONE_ID);
  private WPI_VictorSPX m_Finger2 = new WPI_VictorSPX(Constants.GripperProfile.FINGER_TWO_ID);

  private boolean gripperIsOpen = false;

  /** Creates a new GripperSubsystem. */
  public GripperSubsystem() {
    m_Finger1.setInverted(false);
    m_Finger2.setInverted(true);
  }

  public boolean isGripperOpen() {
    return gripperIsOpen;
  }

  public void setGripperOpen(AirSubsystem airSubsystem) {
    airSubsystem.openGriper();
    runGripperWheels(0);
    gripperIsOpen = true;
  }

  public void setGripperClosed(AirSubsystem airSubsystem) {
    airSubsystem.closeGriper();
    runGripperWheels(0.1);
    gripperIsOpen = false;
  }

  private void runGripperWheels(double commandedOutputFraction) {
    m_Finger1.set(commandedOutputFraction);
    m_Finger2.set(commandedOutputFraction);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Gripper_Is_Open", isGripperOpen());
  }
}
