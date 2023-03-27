// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

  private boolean gripperIsOpen = false;

  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {}

  public boolean isArmDeployed() {
    return gripperIsOpen;
  }

  public void setArmDeployed(AirSubsystem airSubsystem) {
    airSubsystem.deployArm();
    gripperIsOpen = true;
  }

  public void setArmUndeployed(AirSubsystem airSubsystem) {
    airSubsystem.undeployArm();
    gripperIsOpen = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Arm_Is_Deployed", isArmDeployed());
  }
}
