// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsytem;

public class ZeroGyro extends CommandBase {
  /** Creates a new zeroGyro. */
  private SwerveSubsytem m_swerve;
  private XboxController m_driverController;

  public ZeroGyro(SwerveSubsytem swerve, XboxController driverController) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_swerve = swerve; 
    m_driverController = driverController;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_driverController.getAButton() == true){
      m_swerve.zeroGyro();
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
