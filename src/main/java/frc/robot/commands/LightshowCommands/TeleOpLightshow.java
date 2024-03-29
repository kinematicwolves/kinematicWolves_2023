// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LightshowCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LightingSubsystem;

public class TeleOpLightshow extends CommandBase {

  private final LightingSubsystem m_LightingSubsystem;

  /** Creates a new TeleOpLightshow. */
  public TeleOpLightshow(LightingSubsystem lightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_LightingSubsystem = lightingSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_LightingSubsystem.setTeleOpLightShow();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_LightingSubsystem.setDisabledLightShow();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
