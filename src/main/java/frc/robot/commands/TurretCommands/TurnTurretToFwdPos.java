// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TurretCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class TurnTurretToFwdPos extends CommandBase {
  /** Creates a new TurnTurretPosFwd. */
  private final TurretSubsystem m_TurretSubsystem; 
  public TurnTurretToFwdPos(TurretSubsystem TurretSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.'
    m_TurretSubsystem = TurretSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_TurretSubsystem.moveTurretToFwdPos();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_TurretSubsystem.setTurretMotorOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
