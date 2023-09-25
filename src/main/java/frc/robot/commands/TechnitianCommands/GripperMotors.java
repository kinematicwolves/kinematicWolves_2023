// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TechnitianCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.LightingSubsystem;

public class GripperMotors extends CommandBase {
  private final GripperSubsystem m_GripperSubsystem;
  private final Double m_commandedOutputFraction;
  private final LightingSubsystem m_LightingSubsystem;

  /** Creates a new GripperTest. */
  public GripperMotors(GripperSubsystem gripperSubsystem, LightingSubsystem lightingSubsystem, double commandedOutputFraction) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_GripperSubsystem = gripperSubsystem;
    m_commandedOutputFraction = commandedOutputFraction;
    m_LightingSubsystem = lightingSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_GripperSubsystem.runFingerMotors(m_commandedOutputFraction);
    m_LightingSubsystem.setRedLightshow();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_GripperSubsystem.runFingerMotors(0);
    m_LightingSubsystem.setTehcnicianLightshow();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
