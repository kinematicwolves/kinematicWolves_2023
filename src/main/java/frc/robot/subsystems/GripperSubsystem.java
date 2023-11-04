
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GripperSubsystem extends SubsystemBase {

  private final VictorSPX m_leftFingerMotor = new VictorSPX(Constants.GripperProfile.FINGER_ONE_ID);
  private final VictorSPX m_rightFingerMotor = new VictorSPX(Constants.GripperProfile.FINGER_TWO_ID);

  private final TimeOfFlight m_distanceSensor = new TimeOfFlight(0);

  private boolean gripperIsOpen = false;
  private boolean objectInRange = false;

  /** Creates a new GripperSubsystem. */
  public GripperSubsystem() {
    m_leftFingerMotor.setInverted(true);
    m_rightFingerMotor.setInverted(false);
  }

  public boolean isObjectInRange() {
    return objectInRange;
  }

  public boolean isGripperOpen() {
    return gripperIsOpen;
  }

  public void distanceSensorFeedback(LightingSubsystem lightingSubsystem) {
    if (m_distanceSensor.getRange() < 130) {
      lightingSubsystem.setGreenLightShow();
      objectInRange = true;
    }
    else {
      lightingSubsystem.setRedLightshow();
      objectInRange = false;
    }
  }

  public void runFingerMotors(double commandedOutputFraction) {
    m_leftFingerMotor.set(ControlMode.PercentOutput, commandedOutputFraction);
    m_rightFingerMotor.set(ControlMode.PercentOutput, commandedOutputFraction);
  }

  public void resetGripper(AirSubsystem airSubsystem) {
    runFingerMotors(0);
    airSubsystem.openGriper();
  }

  public void deployGripper(AirSubsystem airSubsystem) {
    airSubsystem.openGriper();
    runFingerMotors(0.15);
    gripperIsOpen = true;
  }

  public void undeployGripper(AirSubsystem airSubsystem) {
    airSubsystem.closeGriper();
    runFingerMotors(0.3);
    gripperIsOpen = false;
  }

  public double getDistance() {
    return m_distanceSensor.getRange();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Gripper_Is_Open", isGripperOpen());
    SmartDashboard.putNumber("Object Distance", getDistance());
    SmartDashboard.putBoolean("Object In Range", isObjectInRange());
  }
}