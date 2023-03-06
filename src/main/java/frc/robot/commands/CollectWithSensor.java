// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.GripperSubsytem;
import frc.robot.subsystems.LightingSubsystem;

public class CollectWithSensor extends CommandBase {
  private final GripperSubsytem m_GripperSubsytem;
  private final AirSubsystem m_AirSubsystem;
  private final LightingSubsystem m_LightingSubsystem;

  private Timer m_timer = new Timer();

  /** Creates a new GripperControl. */
  public CollectWithSensor(GripperSubsytem gripperSubsytem, AirSubsystem airSubsystem, LightingSubsystem lightingSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_GripperSubsytem = gripperSubsytem;
    m_AirSubsystem = airSubsystem;
    m_LightingSubsystem = lightingSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
    m_timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_GripperSubsytem.collectInRange(m_AirSubsystem);
    if (m_timer.get() < 3) {
      m_LightingSubsystem.setArmCandleBlueTwinkleAnimation();
    }
    else {
      m_LightingSubsystem.setArmCandleGreenTwinkleAnimation();
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
