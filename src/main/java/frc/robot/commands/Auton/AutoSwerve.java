// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsytem;

public class AutoSwerve extends CommandBase {
  private final SwerveSubsytem m_SwerveSubsytem;

  private final double yTranslationSpeed;
  private final double xTranslationSpeed;
  private final double rotationSpeed;

  private final int m_timer;
  private Timer Time;

  /** Creates a new AutoSwerve. */
  public AutoSwerve(double yTranslationSpeed, double xTranslationSpeed, double rotationSpeed, int timer, SwerveSubsytem swerveSubsytem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.yTranslationSpeed = yTranslationSpeed;
    this.xTranslationSpeed = xTranslationSpeed;
    this.rotationSpeed = rotationSpeed;
    m_timer = timer;
    m_SwerveSubsytem = swerveSubsytem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Time.reset();
    // Time.start();
    //m_timer+=20;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_timer < m_timer) {
      m_SwerveSubsytem.drive(new Translation2d(xTranslationSpeed, yTranslationSpeed), rotationSpeed, false, false);
    }
    else {
      m_SwerveSubsytem.drive(new Translation2d(0, 0), 0, false, false);
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
