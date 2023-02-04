// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  private final WPI_TalonFX m_turretMotor = new WPI_TalonFX(Constants.TURRET_MOTOR);

  //variables 
 // private String TurretStates = "TurretStateInitial";

  /** Creates a new Turret. */
  public Turret() {
    m_turretMotor.configFactoryDefault();
    m_turretMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    m_turretMotor.setSelectedSensorPosition(0);
    m_turretMotor.setInverted(TalonFXInvertType.Clockwise);

    //Digital Limits
    m_turretMotor.configReverseSoftLimitThreshold(convertEncoderCounts(Constants.TURRET_REVERSE_POSITION));
    m_turretMotor.configForwardSoftLimitThreshold(convertEncoderCounts(Constants.TURRET_FORWARD_POSITION));

  }

  public void TurretStateInitial(){
    m_turretMotor.setSelectedSensorPosition(0);
  }
  
  public void TurretStateForward(){
    m_turretMotor.setSelectedSensorPosition(Constants.TURRET_FORWARD_POSITION);
  }

  public void TurretStateReverse(){
    m_turretMotor.setSelectedSensorPosition(Constants.TURRET_REVERSE_POSITION);
  }

  public double convertEncoderCounts(double encoderPosition){
    return Math.abs(encoderPosition / Constants.FALCON_ENCODER_COUNTS * 0.5);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    }
  }

