// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import frc.robot.Util.Lib.Conversions;

public class TurretSubsystem extends SubsystemBase {
  private final WPI_TalonFX m_turretMotor = new WPI_TalonFX(Constants.TurretProfile.TURRET_MOTOR);

private static final int initPosition = 0;
private static final int positionThreshold = 10;
private String turretState = "Initial_Position";
private static final double gearRatio = 10/140;

  //variables 
 // private String TurretStates = "TurretStateInitial";

  /** Creates a new Turret. */
  public TurretSubsystem() {
    m_turretMotor.configFactoryDefault();
    m_turretMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
    m_turretMotor.setSelectedSensorPosition(0);
    m_turretMotor.setInverted(TalonFXInvertType.Clockwise);

    //Digital Limits
    m_turretMotor.configReverseSoftLimitThreshold(Conversions.falconToDegrees(Constants.TurretProfile.TURRET_REVERSE_POSITION, gearRatio));
    m_turretMotor.configForwardSoftLimitThreshold(Conversions.falconToDegrees(Constants.TurretProfile.TURRET_FORWARD_POSITION, gearRatio));

  }

  public void monitorTurretStates(){
    if (getTurretPositionDegrees() == Constants.TurretProfile.TURRET_INITIAL_POSITION){
      turretState = "Initial_Position";
    }
    else if (getTurretPositionDegrees() == Constants.TurretProfile.TURRET_FORWARD_POSITION){
      turretState = "Foward Position";
    }
    else if (getTurretPositionDegrees() == Constants.TurretProfile.TURRET_REVERSE_POSITION){
      turretState = "Reverse Position";
    } 
  }

  public boolean turretPositionInit(){
    return getTurretState() == "Initial Turret State"; 
  }

  public boolean turretPositonFwd(){
    return getTurretState() == "Foward Turret State";
  }

  public boolean turretPositionRvs(){
    return getTurretState() == "Reverse Turret State";
  }

  public String getTurretState(){
    return turretState;
  }

  public void setTurretMotorOutput(double commandedOutput){
    m_turretMotor.set(commandedOutput);
  }

  public double getTurretPositionDegrees() {
    return converTurretPositionToDegrees(m_turretMotor.getSelectedSensorPosition());
  }

  public double converTurretPositionToDegrees(double positionDegrees) {
    return Conversions.falconToDegrees(positionDegrees, gearRatio);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    monitorTurretStates();
    
    double currentPosition = m_turretMotor.getSelectedSensorPosition();
    SmartDashboard.putNumber("Turret_Position_Degrees", Conversions.falconToDegrees(currentPosition, gearRatio));
    SmartDashboard.putString("Turret_State", getTurretState());
    }
  }

