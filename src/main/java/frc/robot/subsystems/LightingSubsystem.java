// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.ctre.phoenix.led.CANdle.VBatOutputMode;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix.led.TwinkleAnimation;
import com.ctre.phoenix.led.TwinkleAnimation.TwinklePercent;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LightingSubsystem extends SubsystemBase {
  private final CANdle m_underGlow = new CANdle(Constants.LightProfile.UNDERGLOW_ID, "canivore1");
  private Animation m_Candle1Animation = null;

  /** Creates a new LightshowSubsystem. */
  public LightingSubsystem() {
    CANdleConfiguration cfg = new CANdleConfiguration();
    cfg.brightnessScalar = 0.6;
    cfg.vBatOutputMode = VBatOutputMode.Modulated;
    m_underGlow.configAllSettings(cfg);
    m_underGlow.configLEDType(LEDStripType.GRB);
  }

  public void setEndgameLightshow(){ // White
    m_Candle1Animation = new TwinkleAnimation(0, 0, 15, 225, 0.9, Constants.LightProfile.UNDERGLOW_LED_COUNT,TwinklePercent.Percent100);
  }

  public void setTeleOpLightShow(){ // Green
    m_Candle1Animation = new RainbowAnimation(1, 1, Constants.LightProfile.UNDERGLOW_LED_COUNT);
  }

  public void setRedLightshow() {
    m_Candle1Animation = new TwinkleAnimation(225, 0, 0, 50, 1, Constants.LightProfile.UNDERGLOW_LED_COUNT, TwinklePercent.Percent100);
  }

  public void setGreenLightShow() {
    m_Candle1Animation = new TwinkleAnimation(0, 225, 0, 50, 1, Constants.LightProfile.UNDERGLOW_LED_COUNT, TwinklePercent.Percent100);
  }

  public void setCubeLedSignal(){ // Purple
    m_Candle1Animation = new TwinkleAnimation(255, 0, 255, 69, 0.9, Constants.LightProfile.UNDERGLOW_LED_COUNT, TwinklePercent.Percent100); 
  }

  public void setTehcnicianLightshow(){ // Orange
    m_Candle1Animation = new TwinkleAnimation(225, 165, 0, 100 , 0.9, Constants.LightProfile.UNDERGLOW_LED_COUNT,TwinklePercent.Percent100); 
  }
  
  public void setDisabledLightShow(){
    m_Candle1Animation = new RainbowAnimation(1, 0.1, Constants.LightProfile.UNDERGLOW_LED_COUNT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (m_Candle1Animation != null){
      m_underGlow.animate(m_Candle1Animation);
    }
  }
}
