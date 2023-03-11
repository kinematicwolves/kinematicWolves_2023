// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class SetArmToMid extends CommandBase {
  private ArmSubsystem armSubsystem;
  /** Creates a new ScoreMidNode. */
  public SetArmToMid(ArmSubsystem armSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.armSubsystem = armSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    armSubsystem.setArmToMidNode();
    armSubsystem.setWristToMidNode();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armSubsystem.runInnerArm(0.165);
    armSubsystem.runWrist(-0.05);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}