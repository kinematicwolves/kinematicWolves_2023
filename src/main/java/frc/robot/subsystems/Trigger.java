// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Trigger extends SubsystemBase {
  // Motor
  TalonFX m_front;
  TalonFX m_back;

  /** Creates a new trigger. */
  public Trigger(int InsideID, int OutsideID, boolean Reversed) {
    m_front = new TalonFX(InsideID);
    m_back = new TalonFX(OutsideID);
    m_front.setInverted(Reversed);
    m_back.setInverted(Reversed);
    m_back.setNeutralMode(NeutralMode.Brake);
    m_front.setNeutralMode(NeutralMode.Brake);

    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_4_AinTempVbat, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_6_Misc, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_7_CommStatus, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_8_PulseWidth, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_Brushless_Current, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_11_UartGadgeteer, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_14_Turn_PIDF1, 233, 0);
    m_back.setStatusFramePeriod(StatusFrameEnhanced.Status_15_FirmwareApiStatus, 233, 0);

    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_4_AinTempVbat, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_6_Misc, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_7_CommStatus, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_8_PulseWidth, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_Brushless_Current, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_11_UartGadgeteer, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_14_Turn_PIDF1, 233, 0);
    m_front.setStatusFramePeriod(StatusFrameEnhanced.Status_15_FirmwareApiStatus, 233, 0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  /**
   * Set the power of the motor
   * 
   * @param power the power to set to
   */
  public void setfrontPower(double power) {
    m_front.set(TalonFXControlMode.PercentOutput, power);
  }

  public void setbackPower(double power) {
    m_back.set(TalonFXControlMode.PercentOutput, power);
  }
}
