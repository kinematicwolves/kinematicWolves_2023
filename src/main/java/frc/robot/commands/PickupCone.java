// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.LightingSubsystem;

public class PickupCone extends CommandBase {
  private GripperSubsystem m_GripperSubsystem;
  private ArmSubsystem m_ArmSubsystem;
  private AirSubsystem m_AirSubsystem;
  private LightingSubsystem m_LightingSubsystem;

  /** Creates a new PickupCone. */
  public PickupCone(GripperSubsystem gripperSubsystem, ArmSubsystem armSubsystem, AirSubsystem airSubsystem, LightingSubsystem lightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_AirSubsystem = airSubsystem;
    m_ArmSubsystem = armSubsystem;
    m_GripperSubsystem = gripperSubsystem;
    m_LightingSubsystem = lightingSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ArmSubsystem.setArmDeployed(m_AirSubsystem);
    m_GripperSubsystem.setGripperOpen(m_AirSubsystem);
    m_LightingSubsystem.setRedLightshow();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_GripperSubsystem.getGripperCurrent() > 20) {
      m_GripperSubsystem.setGripperClosed(m_AirSubsystem);
      m_ArmSubsystem.setArmUndeployed(m_AirSubsystem);
      m_LightingSubsystem.setGreenLightShow();
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
