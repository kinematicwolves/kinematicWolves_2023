// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TestCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.LightingSubsystem;

public class RunWrist extends CommandBase {
  private final ArmSubsystem m_ArmSubsystem;
  private final LightingSubsystem m_LightingSubsystem;

  /** Creates a new RunWrist. */
  public RunWrist(ArmSubsystem armSubsystem, LightingSubsystem lightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_LightingSubsystem = lightingSubsystem;
    m_ArmSubsystem = armSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_LightingSubsystem.setArmCandleRedTwinkleAnimation();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ArmSubsystem.runWrist(0.2);
    m_LightingSubsystem.setArmCandleOrangeTwinkleAnimation();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ArmSubsystem.runWrist(0);
    m_LightingSubsystem.setArmCandleOrangeTwinkleAnimation();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
