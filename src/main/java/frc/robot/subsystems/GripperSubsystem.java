// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GripperSubsystem extends SubsystemBase {

  private boolean gripperIsOpen = false;

  /** Creates a new GripperSubsystem. */
  public GripperSubsystem() {}

  public boolean isGripperOpen() {
    return gripperIsOpen;
  }

  public void setGripperOpen(AirSubsystem airSubsystem) {
    airSubsystem.openGriper();
    gripperIsOpen = true;
  }

  public void setGripperClosed(AirSubsystem airSubsystem) {
    airSubsystem.closeGriper();
    gripperIsOpen = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Gripper_Is_Open", isGripperOpen());
  }
}
