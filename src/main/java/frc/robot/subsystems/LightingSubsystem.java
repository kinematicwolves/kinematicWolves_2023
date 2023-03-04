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
  private final CANdle m_armCandle = new CANdle(Constants.LightProfile.ARM_CANDLE_ID);
  // private final CANdle m_chassisCandle = new CANdle(Constants.LightProfile.CHASSIS_CANDLE_ID);
  private Animation m_ArmCandleAnimation = null;
  // private Animation m_ChassisCandle2Animation = null;

  /** Creates a new LightshowSubsystem. */
  public LightingSubsystem() {
    CANdleConfiguration cfg = new CANdleConfiguration();
    cfg.brightnessScalar = 1;
    cfg.vBatOutputMode = VBatOutputMode.Modulated;
    m_armCandle.configAllSettings(cfg);
    m_armCandle.configLEDType(LEDStripType.GRB);
    // m_chassisCandle.configAllSettings(cfg);
    // m_chassisCandle.configLEDType(LEDStripType.GRB);
    m_armCandle.configFactoryDefault();
  }

    /* CANdle 1 (ARM) Animations */
  public void setCANdle1RainbowAnimation(){
    m_ArmCandleAnimation = new RainbowAnimation(0.8, 0.88, Constants.LightProfile.Arm_LED_COUNT);
  }
  public void setCANdle1PurpleTwinkleAnimation(){
    m_ArmCandleAnimation = new TwinkleAnimation(255, 0, 255, 69, 0.9, Constants.LightProfile.Arm_LED_COUNT, TwinklePercent.Percent100); 
  }
  public void setCANdle1RedTwinkleAnimation(){
    m_ArmCandleAnimation = new TwinkleAnimation(225, 0, 1, 0, 0.9, Constants.LightProfile.Arm_LED_COUNT,TwinklePercent.Percent100);
  }
  public void setCANdle1BlueTwinkleAnimation(){
    m_ArmCandleAnimation = new TwinkleAnimation(0, 0, 225, 30, 0.9, Constants.LightProfile.Arm_LED_COUNT,TwinklePercent.Percent100);
  }
  public void setCANdle1GreenTwinkleAnimation(){
    m_ArmCandleAnimation = new TwinkleAnimation(0, 225, 0, 0, 0.9, Constants.LightProfile.Arm_LED_COUNT,TwinklePercent.Percent100); 
  }
  public void setCANdle1BlackAnimation(){
    m_ArmCandleAnimation = new TwinkleAnimation(0, 0, 0, 0 , 0 , Constants.LightProfile.Arm_LED_COUNT,TwinklePercent.Percent100); 
  }

    /* CANdle 2 Animations */
  // public void setCANdle2RainbowAnimation(){
  //   m_ChassisCandle2Animation = new RainbowAnimation(0.8, 0.88, Constants.LightProfile.CHASSIS_LED_COUNT);
  // }
  // public void setCANdle2PurpleTwinkleAnimation(){
  //   m_ChassisCandle2Animation = new TwinkleAnimation(255, 0, 255, 69, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT, TwinklePercent.Percent100); 
  // }
  // public void setCANdle2RedTwinkleAnimation(){
  //   m_ChassisCandle2Animation = new TwinkleAnimation(225, 0, 1, 0, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100);
  // }
  // public void setCANdle2BlueTwinkleAnimation(){
  //   m_ChassisCandle2Animation = new TwinkleAnimation(0, 0, 225, 30, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100);
  // }
  // public void setCANdle2GreenTwinkleAnimation(){
  //   m_ChassisCandle2Animation = new TwinkleAnimation(0, 225, 0, 0, 0.9, Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100); 
  // }
  // public void setCANdle2BlackAnimation(){
  //   m_ChassisCandle2Animation = new TwinkleAnimation(0, 0, 0, 0 , 0 , Constants.LightProfile.CHASSIS_LED_COUNT,TwinklePercent.Percent100); 
  // }

  public void setDisabledLightShow(){
    setCANdle1GreenTwinkleAnimation();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (m_ArmCandleAnimation != null){
      m_armCandle.animate(m_ArmCandleAnimation);
    }
    // else if (m_ChassisCandle2Animation != null) {
    //   m_chassisCandle.animate(m_ChassisCandle2Animation);
    // }
  }
}
