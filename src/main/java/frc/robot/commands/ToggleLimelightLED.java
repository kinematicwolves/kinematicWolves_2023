// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.VisionSubsystem;

public class ToggleLimelightLED extends CommandBase {
  private VisionSubsystem m_VisionSubsystem;

  /** Creates a new ToggleLimelightLED. */
  public ToggleLimelightLED(VisionSubsystem visionSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_VisionSubsystem = visionSubsystem; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_VisionSubsystem.isLimeLightOn()) {
      m_VisionSubsystem.turnLimelightOn();
    }
    else {
      m_VisionSubsystem.turnLimelightOff();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
