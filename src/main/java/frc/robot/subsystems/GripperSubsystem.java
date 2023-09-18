
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GripperSubsystem extends SubsystemBase {

  private final VictorSPX m_leftFingerMotor = new VictorSPX(Constants.GripperProfile.FINGER_ONE_ID);
  private final VictorSPX m_rightFingerMotor = new VictorSPX(Constants.GripperProfile.FINGER_TWO_ID);
  private final PowerDistribution m_pdp = new PowerDistribution(0, ModuleType.kRev);
  private Timer m_timer = new Timer();

  // private final CANSparkMax m_leftFingerMotor = new CANSparkMax(0, MotorType.kBrushless);
  // private final CANSparkMax m_rightFingerMotor = new CANSparkMax(0, MotorType.kBrushless);

  private boolean gripperIsOpen = false;

  /** Creates a new GripperSubsystem. */
  public GripperSubsystem() {
    m_leftFingerMotor.setInverted(true);
    m_rightFingerMotor.setInverted(false);
    //m_leftFingerMotor.current
  }

  public boolean isGripperOpen() {
    return gripperIsOpen;
  }

  public void runFingerMotors(double commandedOutputFraction) {
    m_leftFingerMotor.set(ControlMode.PercentOutput, commandedOutputFraction);
    m_rightFingerMotor.set(ControlMode.PercentOutput, commandedOutputFraction);
  }

  public void setGripperClosed(AirSubsystem airSubsystem) {
    airSubsystem.closeGriper();
    runFingerMotors(0.2);
    gripperIsOpen = false;
  }

  public void setGripperOpen(AirSubsystem airSubsystem) {
    airSubsystem.openGriper();
    runFingerMotors(0.5);
    gripperIsOpen = true;
  }

  private void objectCollected() {
    //if (m_pdp.getCurrent(0) < )
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Gripper_Is_Open", isGripperOpen());
    SmartDashboard.putNumber("Gripper Current", m_pdp.getCurrent(0));
  }
}