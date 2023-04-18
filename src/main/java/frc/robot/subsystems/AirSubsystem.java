// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AirSubsystem extends SubsystemBase {
  private final PneumaticHub m_PneumaticHub = new PneumaticHub(Constants.PneumaticProfile.PNEUMATIC_HUB_ID);

  private final DoubleSolenoid m_gripperSolenoid = m_PneumaticHub.makeDoubleSolenoid(
    Constants.PneumaticProfile.GRIPPER_SOL_FWD, Constants.PneumaticProfile.GRIPPER_SOL_RVS);
  private final DoubleSolenoid m_armSolenoid = m_PneumaticHub.makeDoubleSolenoid(
    Constants.PneumaticProfile.ARM_SOL_FWD, Constants.PneumaticProfile.ARM_SOL_RVS);

  private boolean compressorIsOn = false;

  /** Creates a new AirSubsystem. */
  public AirSubsystem() {}

  public boolean isCompressorOn() {
    return compressorIsOn;
  }

  public void deployArm() {
    m_armSolenoid.set(Value.kForward);
  }

  public void undeployArm() {
    m_armSolenoid.set(Value.kReverse);
  }

  public void openGriper() {
    m_gripperSolenoid.set(Value.kForward);
  }

  public void closeGriper() {
    m_gripperSolenoid.set(Value.kReverse);
  }

  public void enableCompressor() {
    m_PneumaticHub.enableCompressorAnalog(60, 120);
    compressorIsOn = true;
  }

  public void disableCompressor() {
    m_PneumaticHub.disableCompressor();
    compressorIsOn = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("compressor_Is_On", isCompressorOn());
  }
}