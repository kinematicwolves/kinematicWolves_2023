// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TechnitianCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.LightingSubsystem;

public class GripperControl extends CommandBase {
  private final GripperSubsystem m_GripperSubsystem;
  private final AirSubsystem m_AirSubsystem;
  private final LightingSubsystem m_LightingSubsystem;

  /** Creates a new GripperControl. */
  public GripperControl(GripperSubsystem gripperSubsystem, AirSubsystem airSubsystem, LightingSubsystem lightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_AirSubsystem = airSubsystem;
    m_GripperSubsystem = gripperSubsystem;
    m_LightingSubsystem = lightingSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_GripperSubsystem.isGripperOpen()) {
      m_AirSubsystem.closeGriper();;
      m_LightingSubsystem.setRedLightshow();
    }
    else {
      m_AirSubsystem.openGriper();
      m_LightingSubsystem.setRedLightshow();
      m_LightingSubsystem.setTehcnicianLightshow();
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