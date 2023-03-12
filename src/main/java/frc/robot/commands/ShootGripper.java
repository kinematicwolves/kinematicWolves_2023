// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GripperSubsytem;

public class ShootGripper extends CommandBase {
  private final GripperSubsytem m_GripperSubsytem;

  /** Creates a new ShootGripper. */
  public ShootGripper(GripperSubsytem gripperSubsytem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_GripperSubsytem = gripperSubsytem;
    addRequirements(m_GripperSubsytem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  m_GripperSubsytem.runGripperWheels(0.35);
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_GripperSubsytem.runGripperWheels(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
