// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsytem;

public class DriveRobotOpenLoop extends CommandBase {
  private final SwerveSubsytem m_swerve; 
  private final XboxController m_driverController;


  /** Creates a new Swerve. */
  public DriveRobotOpenLoop(SwerveSubsytem swerve, XboxController driverController) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_swerve = swerve;
    m_driverController = driverController;
    addRequirements(m_swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_swerve.drive( new Translation2d(m_driverController.getLeftY(), m_driverController.getLeftX()).times(Constants.Swerve.maxSpeed),
    m_driverController.getRightX() * Constants.Swerve.maxAngularVelocity, false, true);     
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
