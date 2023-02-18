// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsytem;
import frc.robot.subsystems.VisionSubsystem;

public class AutoAlignment extends CommandBase {
  /** Creates a new AutoAlignment. */
  private VisionSubsystem m_VisionSubsystem;
  private SwerveSubsytem m_SwerveSubsytem;
  private final double m_strafeSpeed;

  public AutoAlignment(VisionSubsystem visionSubsystem, SwerveSubsytem swerveSubsytem, double strafeSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_VisionSubsystem = visionSubsystem;
    m_SwerveSubsytem = swerveSubsytem;
    m_strafeSpeed = strafeSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_SwerveSubsytem.strafeDrivetrainToTarget(m_strafeSpeed, m_VisionSubsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_SwerveSubsytem.isLinedUp(m_VisionSubsystem));
    m_SwerveSubsytem.yTranslateDrivetrainToTarget(m_strafeSpeed, m_VisionSubsystem);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_SwerveSubsytem.isLinedUpInDistance(m_VisionSubsystem);
  }
}
