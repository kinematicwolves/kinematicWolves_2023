// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.LightingSubsystem;

public class ArmReset extends CommandBase {
  private final ArmSubsystem m_ArmSubsystem;
  private final GripperSubsystem m_GripperSubsystem;
  private final LightingSubsystem m_LightingSubsystem;
  private final AirSubsystem m_AirSubsystem;

  /** Creates a new ArmReset. */
  public ArmReset(ArmSubsystem armSubsystem, GripperSubsystem gripperSubsystem, LightingSubsystem lightingSubsystem, AirSubsystem airSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ArmSubsystem = armSubsystem;
    m_GripperSubsystem = gripperSubsystem;
    m_LightingSubsystem = lightingSubsystem;
    m_AirSubsystem = airSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ArmSubsystem.setArmUndeployed(m_AirSubsystem);
    m_GripperSubsystem.resetGripper(m_AirSubsystem);
    m_LightingSubsystem.setTeleOpLightShow();
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
