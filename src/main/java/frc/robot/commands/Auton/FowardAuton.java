// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import org.opencv.features2d.FastFeatureDetector;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsytem;

public class FowardAuton extends CommandBase {
  private final SwerveSubsytem m_SwerveSubsytem;
  private int Timer;

  /** Creates a new FowardAuton. */
  public FowardAuton(SwerveSubsytem swerveSubsytem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_SwerveSubsytem = swerveSubsytem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Timer+=20;
    if (Timer < 5000) {
      m_SwerveSubsytem.drive(new Translation2d(0,0), 0, false, false);
    }
    else {
      m_SwerveSubsytem.drive(new Translation2d(0,0), 0, false, false);
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
