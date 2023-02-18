// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.TurretSubsystem;

public class TurnTurretToInitPos extends CommandBase {
  private TurretSubsystem m_turret;
  /** Creates a new TurnToTurretPos0. */
  public TurnTurretToInitPos(TurretSubsystem turret) {
    // Use addRequirements() here to declare subsystem dependencies.
    turret = m_turret; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_turret.getTurretPositionDegrees() > Constants.TurretProfile.TURRET_INITIAL_POSITION) {
      m_turret.setTurretMotorOutput(0.1);
    }
    else if (m_turret.getTurretPositionDegrees() < Constants.TurretProfile.TURRET_INITIAL_POSITION) {
      m_turret.setTurretMotorOutput(-0.1);
    }
    else {
      m_turret.setTurretMotorOutput(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_turret.getTurretState() == "Initial Turret State";
  }
}
