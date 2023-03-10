// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TestCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.LightingSubsystem;

public class ToggleCompressor extends CommandBase {
  private final AirSubsystem m_AirSubsystem;
  private final LightingSubsystem m_LightingSubsystem;

  /** Creates a new ToggleCompressor. */
  public ToggleCompressor(AirSubsystem airSubsystem, LightingSubsystem lightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_AirSubsystem = airSubsystem;
    m_LightingSubsystem = lightingSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_LightingSubsystem.setArmCandleRedTwinkleAnimation();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_AirSubsystem.isCompressorOn()) {
      m_AirSubsystem.enableCompressor();
    }
    else { 
      m_AirSubsystem.disableCompressor();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_LightingSubsystem.setArmCandleOrangeTwinkleAnimation();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
