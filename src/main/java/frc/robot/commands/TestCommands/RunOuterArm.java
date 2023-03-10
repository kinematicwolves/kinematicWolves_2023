// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TestCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.LightingSubsystem;

public class RunOuterArm extends CommandBase {
  private ArmSubsystem m_ArmSubsystem;
  private LightingSubsystem m_LightingSubsystem;

  /** Creates a new ArmTest. */
  public RunOuterArm(ArmSubsystem armSubsystem, LightingSubsystem lightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ArmSubsystem = armSubsystem; 
    m_LightingSubsystem = lightingSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_LightingSubsystem.setArmCandleRedTwinkleAnimation();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ArmSubsystem.runOuterArm(-0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ArmSubsystem.runOuterArm(0);
    m_LightingSubsystem.setArmCandleOrangeTwinkleAnimation();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
