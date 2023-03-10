// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GripperSubsytem extends SubsystemBase {
  private final WPI_VictorSPX m_leftFinger = new WPI_VictorSPX(Constants.GripperProfile.LEFT_FINGER);
  private final WPI_VictorSPX m_rightFinger = new WPI_VictorSPX(Constants.GripperProfile.RIGHT_FINGER);

  private boolean gripperIsOpen = false;

  /** Creates a new GripperSubsytem. */
  public GripperSubsytem() {}

  public boolean isGripperOpen() {
    return gripperIsOpen;
  }

  public void setGriperOpen(AirSubsystem airSubsystem) {
    airSubsystem.openGriper();
    runGripperWheels(0);
  }

  public void setGripperClosed(AirSubsystem airSubsystem, double wheelSpeeds) {
    airSubsystem.closeGriper();
    runGripperWheels(wheelSpeeds);
  }

  public void runGripperWheels(double commandedOutFraction) {
    m_leftFinger.set(-1 * commandedOutFraction);
    m_rightFinger.set(commandedOutFraction);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("GRipperIsOpen", isGripperOpen());
  }
}
