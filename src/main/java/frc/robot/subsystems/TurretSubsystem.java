// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
// import com.ctre.phoenix.sensors.SensorInitializationStrategy;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class TurretSubsystem extends SubsystemBase {
//   private final WPI_TalonFX m_turretMotor = new WPI_TalonFX(Constants.TurretProfile.TURRET_MOTOR, "canivore1") ;

//   /** Creates a new Turret. */
//   public TurretSubsystem() {
//     m_turretMotor.configFactoryDefault();
//     m_turretMotor.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero, 10);
//     m_turretMotor.setSelectedSensorPosition(0);
//     m_turretMotor.setInverted(TalonFXInvertType.Clockwise);
//   }
  
//   public void moveTurretToZeroPos() {
//     double turretEncoderPos = m_turretMotor.getSelectedSensorPosition();
//     if (turretEncoderPos == 0) { // Zero encoder position
//       setTurretMotorOutput(0);
//     }
//     else if (turretEncoderPos > 0) {
//       setTurretMotorOutput(0.2);
//     }
//     else if (turretEncoderPos > 0) {
//       setTurretMotorOutput(0.2);
//     }
//   }

//   public void moveTurretToFwdPos() {
//     double turretEncoderPos = m_turretMotor.getSelectedSensorPosition();
//     if (turretEncoderPos == 1) { // Fwd encoder position
//       setTurretMotorOutput(0);
//     }
//     else if (turretEncoderPos < 1) {
//       setTurretMotorOutput(0.1);
//     }
//   }

//   public void moveTurretToRvsPos() {
//     double turretEncoderPos = m_turretMotor.getSelectedSensorPosition();
//     if (turretEncoderPos == -1) { // Rvs encoder pos
//       setTurretMotorOutput(0);
//     }
//     else if (turretEncoderPos > -1) {
//       setTurretMotorOutput(0.1);
//     }
//   }

//   public void setTurretMotorOutput(double commandedOutput){
//     m_turretMotor.set(commandedOutput);
//   }
  
//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//     double turretEncoder = m_turretMotor.getSelectedSensorPosition();
//     SmartDashboard.putNumber("Turret_Encoder_Count", turretEncoder);
//     }
//   }

