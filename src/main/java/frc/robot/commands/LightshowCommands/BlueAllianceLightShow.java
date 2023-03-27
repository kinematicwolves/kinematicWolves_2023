// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LightshowCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LightingSubsystem;

public class BlueAllianceLightShow extends CommandBase {
  private LightingSubsystem m_LightingSubsystem;

  private Timer m_timer = new Timer();

  /** Creates a new RedAllianceLightshow. */
  public BlueAllianceLightShow(LightingSubsystem LightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_LightingSubsystem = LightingSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_timer.get() > 105){
      m_LightingSubsystem.setEndgameLightshow();
    }
    else {
      m_LightingSubsystem.setBlueAllianceLightShow();
    }
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
