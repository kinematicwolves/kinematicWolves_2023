// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GripperSubsytem extends SubsystemBase {
  private final CANSparkMax m_leftFinger = new CANSparkMax(Constants.GripperProfile.RIGHT_FINGER, MotorType.kBrushless);
  private final CANSparkMax m_rightFinger = new CANSparkMax(Constants.GripperProfile.RIGHT_FINGER, MotorType.kBrushless);

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
    m_leftFinger.setSmartCurrentLimit(Constants.GripperProfile.GRIPPER_CURRENT_STALL_LIMIT, Constants.GripperProfile.GRIPPER_CURRENT_FREE_LIMIT,
     Constants.GripperProfile.GRIPPER_CURRENT_LIMIT_RPM);
    m_rightFinger.setSmartCurrentLimit(Constants.GripperProfile.GRIPPER_CURRENT_STALL_LIMIT, Constants.GripperProfile.GRIPPER_CURRENT_FREE_LIMIT,
    Constants.GripperProfile.GRIPPER_CURRENT_LIMIT_RPM);
  }
}
