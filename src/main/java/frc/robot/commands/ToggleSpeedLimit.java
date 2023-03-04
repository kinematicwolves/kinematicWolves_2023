// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsytem;

public class ToggleSpeedLimit extends CommandBase {
  private SwerveSubsytem m_SwerveSubsytem;

  /** Creates a new ToggleSpeedLimit. */
  public ToggleSpeedLimit(SwerveSubsytem swerveSubsytem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_SwerveSubsytem = swerveSubsytem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_SwerveSubsytem.isSpeedLimited()) {
      m_SwerveSubsytem.disableSpeedLimit();
    }
    else {
      m_SwerveSubsytem.enableSpeedLimit();
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
