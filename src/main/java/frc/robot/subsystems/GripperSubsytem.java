// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GripperSubsytem extends SubsystemBase {
  private final CANSparkMax m_leftFinger = new CANSparkMax(Constants.GripperProfile.RIGHT_FINGER, MotorType.kBrushless);
  private final CANSparkMax m_rightFinger = new CANSparkMax(Constants.GripperProfile.RIGHT_FINGER, MotorType.kBrushless);
  private final TimeOfFlight m_distanceSensor = new TimeOfFlight(0);

  private boolean gripperIsOpen = false;
  private boolean targetInRange = false;

  /** Creates a new GripperSubsytem. */
  public GripperSubsytem() {}

  public boolean isGripperOpen() {
    return gripperIsOpen;
  }

  public void setGriperOpen(AirSubsystem airSubsystem) {
    gripperIsOpen = true;
    airSubsystem.openGriper();
    runGripperWheels(0);
  }

  public void setGripperClosed(AirSubsystem airSubsystem, double wheelSpeeds) {
    gripperIsOpen = false;
    airSubsystem.closeGriper();
    runGripperWheels(wheelSpeeds);
  }

  public boolean isTargetInRange() {
    return targetInRange;
  }

   public boolean targetInRange() {
     targetInRange = true;
     var targetDistance = m_distanceSensor.getRange();
     return targetDistance < 10;
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

    // TimeOfFlightSensor
     double rangeOfTarget = m_distanceSensor.getRange();
     SmartDashboard.putNumber("Target_Distance_In_Millimeters", rangeOfTarget);
  }
}
