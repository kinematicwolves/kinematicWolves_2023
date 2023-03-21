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
  private final CANdle m_armCandle1 = new CANdle(Constants.LightProfile.ARM_CANDLE_ID, "canivore1");
  //private final CANdle m_armCandle2 = new CANdle(Constants.LightProfile.CHASSIS_CANDLE_ID);
  private Animation m_ArmCandle1Animation = null;
  private Animation m_ArmCandle2Animation = null;

  private boolean coneSignalOn = false;
  private boolean cubeSignalOn = false;

  /** Creates a new LightshowSubsystem. */
  public LightingSubsystem() {
    CANdleConfiguration cfg = new CANdleConfiguration();
    cfg.brightnessScalar = 0.6;
    cfg.vBatOutputMode = VBatOutputMode.Modulated;
    m_armCandle1.configAllSettings(cfg);
    m_armCandle1.configLEDType(LEDStripType.GRB);
    // m_armCandle2.configAllSettings(cfg);
    // m_armCandle2.configLEDType(LEDStripType.GRB);
  }

  public boolean isConeSignalOn(){
    return coneSignalOn;
  }

  // public boolean isCubeSignalOn(){
  //   return cubeSignalOn;
  // }

  public void coneSignal(){
    coneSignalOn = true;
    setArmCandleYellowTwinkleAnimation();
  }

  // public void cubeSignal(){
  //   cubeSignalOn = true;
  //   setArmCandlePurpleTwinkleAnimation();
  // }

    /* CANdle 1 (ARM) Animations */
  public void setArmCandleRainbowAnimation(){
    m_ArmCandle1Animation = new RainbowAnimation(0.8, 0.5, Constants.LightProfile.Arm_LED_COUNT);
    m_ArmCandle2Animation = new RainbowAnimation(0.8, 0.5, Constants.LightProfile.CHASSIS_LED_COUNT);
  }
  // public void setArmCandlePurpleTwinkleAnimation(){
  //   m_ArmCandle1Animation = new TwinkleAnimation(255, 0, 255, 69, 0.9, Constants.LightProfile.Arm_LED_COUNT, TwinklePercent.Percent100); 
  //   m_ArmCandle2Animation = new TwinkleAnimation(255, 0, 255, 69, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT, TwinklePercent.Percent100); 
  // }
  public void setArmCandleYellowTwinkleAnimation(){
    m_ArmCandle1Animation = new TwinkleAnimation(210, 225, 0 , 100, 0.9, Constants.LightProfile.Arm_LED_COUNT, TwinklePercent.Percent100);
    m_ArmCandle2Animation = new TwinkleAnimation(210, 225, 0 , 100, 0.9, Constants.LightProfile.Arm_LED_COUNT, TwinklePercent.Percent100);
  }
  public void setTeleOpLightShow(){
    m_ArmCandle1Animation = new TwinkleAnimation(0, 225, 0, 10, 0.9, Constants.LightProfile.Arm_LED_COUNT,TwinklePercent.Percent100); 
    m_ArmCandle2Animation = new TwinkleAnimation(0, 225, 0, 0, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100); 
    coneSignalOn = false;
    cubeSignalOn = false;
  }
  public void setEndgameLightshow(){
    m_ArmCandle1Animation = new TwinkleAnimation(0, 0, 15, 225, 0.9, Constants.LightProfile.Arm_LED_COUNT,TwinklePercent.Percent100);
    m_ArmCandle2Animation = new TwinkleAnimation(0, 0, 15, 225, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100);
  }
  public void setArmCandleBlackAnimation(){
    m_ArmCandle1Animation = new TwinkleAnimation(0, 0, 0, 0 , 0, Constants.LightProfile.Arm_LED_COUNT,TwinklePercent.Percent100); 
    m_ArmCandle2Animation = new TwinkleAnimation(0, 0, 0, 0 , 0, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100); 
  }
  public void setArmCandleOrangeTwinkleAnimation(){
    m_ArmCandle1Animation = new TwinkleAnimation(225, 50, 0, 15 , 0.9, Constants.LightProfile.Arm_LED_COUNT,TwinklePercent.Percent100); 
    m_ArmCandle2Animation = new TwinkleAnimation(225, 50, 0, 15 , 0.9, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100); 
  }
  public void setArmCandleRedTwinkleAnimation(){
    m_ArmCandle1Animation = new TwinkleAnimation(225, 0, 0, 20, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100);
    m_ArmCandle2Animation = new TwinkleAnimation(225, 0, 0, 20, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100);  
  }

  public void setDisabledLightShow(){
    setArmCandleRainbowAnimation();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (m_ArmCandle1Animation != null){
      m_armCandle1.animate(m_ArmCandle1Animation);
    }
    if (m_ArmCandle2Animation != null) {
      // m_armCandle2.animate(m_ArmCandle2Animation);
    }
  }
}
