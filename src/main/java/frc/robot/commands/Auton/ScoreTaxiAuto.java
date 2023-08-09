// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreTaxiAuto extends SequentialCommandGroup {
  /** Creates a new ScoreTaxiAuto. */
  public ScoreTaxiAuto(ArmSubsystem m_ArmSubsystem, AirSubsystem m_AirSubsystem, GripperSubsystem m_GripperSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DeployArmAuto(m_ArmSubsystem, m_AirSubsystem)
    );
  }
}
