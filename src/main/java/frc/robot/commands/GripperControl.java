// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.GripperSubsytem;

public class GripperControl extends CommandBase {
  private final GripperSubsytem m_GripperSubsytem;
  private final AirSubsystem m_AirSubsystem;

  /** Creates a new GripperControl. */
  public GripperControl(GripperSubsytem gripperSubsytem, AirSubsystem airSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_GripperSubsytem = gripperSubsytem;
    m_AirSubsystem = airSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_GripperSubsytem.isGripperOpen()) {
    m_GripperSubsytem.setGripperClosed(m_AirSubsystem, 0.1); 
    }
    else {
      m_GripperSubsytem.setGriperOpen(m_AirSubsystem);
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
