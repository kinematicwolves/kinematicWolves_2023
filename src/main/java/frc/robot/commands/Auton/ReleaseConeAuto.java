// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.GripperSubsystem;

public class ReleaseConeAuto extends CommandBase {
  private final AirSubsystem m_AirSubsystem;
  private final GripperSubsystem m_GripperSubsystem;
  private Timer m_Timer = new Timer();

  /** Creates a new ScoreLowCubeAuto. */
  public ReleaseConeAuto(AirSubsystem airSubsystem, GripperSubsystem gripperSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_GripperSubsystem = gripperSubsystem;
    m_AirSubsystem = airSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Timer.reset();
    m_Timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_Timer.get() < 5) {
      m_GripperSubsystem.runFingerMotors(-0.3);
    }
    else {
      m_GripperSubsystem.runFingerMotors(0);
      m_GripperSubsystem.setGripperOpen(m_AirSubsystem);
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
