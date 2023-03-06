// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GripperSubsytem extends SubsystemBase {
  private final WPI_VictorSPX m_leftFinger = new WPI_VictorSPX(Constants.GripperProfile.LEFT_FINGER);
  private final WPI_VictorSPX m_rightFinger = new WPI_VictorSPX(Constants.GripperProfile.RIGHT_FINGER);
  private final TimeOfFlight m_distanceSensor = new TimeOfFlight(Constants.GripperProfile.DISTANCE_SENSOR);

  /** Creates a new GripperSubsytem. */
  public GripperSubsytem() {}

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

  public void collectInRange(AirSubsystem airSubsystem) {
    // if (m_distanceSensor.getRange() < 10) {
      setGripperClosed(airSubsystem, 0.1);
    //}
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // TimeOfFlightSensor
    //  double rangeOfTarget = m_distanceSensor.getRange();
    //  SmartDashboard.putNumber("Target_Distance_In_Millimeters", rangeOfTarget);
  }
}
