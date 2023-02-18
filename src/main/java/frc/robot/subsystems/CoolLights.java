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

public class CoolLights extends SubsystemBase {
  private final CANdle m_candle1 = new CANdle(Constants.LightProfile.CANDLE1_ID);
  private final CANdle m_candle2 = new CANdle(Constants.LightProfile.CANDLE2_ID);
  private Animation m_candle1Animation = null;
  private Animation m_candle2Animation = null;

  /** Creates a new LightshowSubsystem. */
  public CoolLights() {
    CANdleConfiguration cfg = new CANdleConfiguration();
    cfg.brightnessScalar = 1;
    cfg.vBatOutputMode = VBatOutputMode.Modulated;
    m_candle1.configAllSettings(cfg);
    m_candle1.configLEDType(LEDStripType.GRB);
    m_candle2.configAllSettings(cfg);
    m_candle2.configLEDType(LEDStripType.GRB);
  }

    /* CANdle 1 Animations */
  public void setCANdle1RainbowAnimation(){
    m_candle1Animation = new RainbowAnimation(0.8, 0.88, Constants.LightProfile.CANDLE1_LED_COUNT);
  }
  public void setCANdle1PurpleTwinkleAnimation(){
    m_candle1Animation = new TwinkleAnimation(255, 0, 255, 69, 0.9, Constants.LightProfile.CANDLE1_LED_COUNT, TwinklePercent.Percent100); 
  }
  public void setCANdle1RedTwinkleAnimation(){
    m_candle1Animation = new TwinkleAnimation(225, 0, 1, 0, 0.9, Constants.LightProfile.CANDLE1_LED_COUNT,TwinklePercent.Percent100);
  }
  public void setCANdle1BlueTwinkleAnimation(){
    m_candle1Animation = new TwinkleAnimation(0, 0, 225, 30, 0.9, Constants.LightProfile.CANDLE1_LED_COUNT,TwinklePercent.Percent100);
  }
  public void setCANdle1GreenTwinkleAnimation(){
    m_candle1Animation = new TwinkleAnimation(0, 225, 0, 0, 0.9, Constants.LightProfile.CANDLE1_LED_COUNT,TwinklePercent.Percent100); 
  }
  public void setCANdle1BlackAnimation(){
    m_candle1Animation = new TwinkleAnimation(0, 0, 0, 0 , 0 , Constants.LightProfile.CANDLE1_LED_COUNT,TwinklePercent.Percent100); 
  }

    /* CANdle 2 Animations */
  public void setCANdle2RainbowAnimation(){
    m_candle1Animation = new RainbowAnimation(0.8, 0.88, Constants.LightProfile.CANDLE2_LED_COUNT);
  }
  public void setCANdle2PurpleTwinkleAnimation(){
    m_candle1Animation = new TwinkleAnimation(255, 0, 255, 69, 0.9, Constants.LightProfile.CANDLE2_LED_COUNT, TwinklePercent.Percent100); 
  }
  public void setCANdle2RedTwinkleAnimation(){
    m_candle1Animation = new TwinkleAnimation(225, 0, 1, 0, 0.9, Constants.LightProfile.CANDLE2_LED_COUNT,TwinklePercent.Percent100);
  }
  public void setCANdle2BlueTwinkleAnimation(){
    m_candle1Animation = new TwinkleAnimation(0, 0, 225, 30, 0.9, Constants.LightProfile.CANDLE2_LED_COUNT,TwinklePercent.Percent100);
  }
  public void setCANdle2GreenTwinkleAnimation(){
    m_candle1Animation = new TwinkleAnimation(0, 225, 0, 0, 0.9, Constants.LightProfile.CANDLE2_LED_COUNT,TwinklePercent.Percent100); 
  }
  public void setCANdle2BlackAnimation(){
    m_candle1Animation = new TwinkleAnimation(0, 0, 0, 0 , 0 , Constants.LightProfile.CANDLE2_LED_COUNT,TwinklePercent.Percent100); 
  }

  public void setDisabledLightShow(){
    setCANdle1GreenTwinkleAnimation();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (m_candle1Animation != null){
      m_candle1.animate(m_candle1Animation);
    }
    else if (m_candle2Animation != null) {
      m_candle2.animate(m_candle2Animation);
    }
  }
}
