// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TurretCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class TurnTurretToRvsPos extends CommandBase {
  private TurretSubsystem m_TurretSubsystem;

  /** Creates a new TurnTurretToRvsPos. */
  public TurnTurretToRvsPos(TurretSubsystem turretSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_TurretSubsystem = turretSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_TurretSubsystem.moveTurretToRvsPos();
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
