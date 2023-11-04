// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.LightingSubsystem;

public class ObjectEject extends CommandBase {
  private final GripperSubsystem m_GripperSubsystem;
  private final AirSubsystem m_AirSubsystem;
  private final LightingSubsystem m_LightingSubsystem;

  /** Creates a new GripperEject. */
  public ObjectEject(GripperSubsystem gripperSubsystem, AirSubsystem airSubsystem, LightingSubsystem lightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_GripperSubsystem = gripperSubsystem;
    m_AirSubsystem = airSubsystem;
    m_LightingSubsystem = lightingSubsystem;
    addRequirements(m_LightingSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_GripperSubsystem.runFingerMotors(-0.28);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_GripperSubsystem.runFingerMotors(0);
    m_LightingSubsystem.setTeleOpLightShow();
    m_AirSubsystem.openGriper();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
