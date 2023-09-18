// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsystem;

public class ArmControl extends CommandBase {
  private final ArmSubsystem m_ArmSubsystem;
  private final AirSubsystem m_AirSubsystem;
  private final GripperSubsystem m_GripperSubsystem;
  
  /** Creates a new ArmControl. */
  public ArmControl(ArmSubsystem armSubsystem, AirSubsystem airSubsystem, GripperSubsystem gripperSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ArmSubsystem = armSubsystem;
    m_AirSubsystem = airSubsystem;
    m_GripperSubsystem = gripperSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_ArmSubsystem.isArmDeployed()) {
      m_ArmSubsystem.setArmUndeployed(m_AirSubsystem);
    }
    else {
      m_ArmSubsystem.setArmDeployed(m_AirSubsystem);
      m_GripperSubsystem.runFingerMotors(-0.5);
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