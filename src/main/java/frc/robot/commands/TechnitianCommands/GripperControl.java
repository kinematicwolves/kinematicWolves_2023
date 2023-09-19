// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TechnitianCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.GripperSubsystem;

public class GripperControl extends CommandBase {
  private final GripperSubsystem m_GripperSubsystem;
  private final AirSubsystem m_AirSubsystem;

  /** Creates a new GripperControl. */
  public GripperControl(GripperSubsystem gripperSubsystem, AirSubsystem airSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_AirSubsystem = airSubsystem;
    m_GripperSubsystem = gripperSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_GripperSubsystem.isGripperOpen()) {
      m_GripperSubsystem.setGripperClosed(m_AirSubsystem);
    }
    else {
      m_GripperSubsystem.setGripperOpen(m_AirSubsystem);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}