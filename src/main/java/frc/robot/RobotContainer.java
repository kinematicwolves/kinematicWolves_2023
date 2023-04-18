package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArmControl;
import frc.robot.commands.GripperControl;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.Auton.OutsidePosAuto;
import frc.robot.commands.LightshowCommands.RedAllianceLightShow;
import frc.robot.commands.LightshowCommands.SetDisabledState;
import frc.robot.commands.LightshowCommands.TechnicianLightshow;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.LightingSubsystem;
import frc.robot.subsystems.SwerveSubsytem;


public class RobotContainer {

    /* Controllers */
    private final Joystick driverController = new Joystick(Constants.ControllerProfile.DRIVER_CONTROLLER);
    private final Joystick munipulatorController = new Joystick(Constants.ControllerProfile.MUNIPULATOR_CONTROLLER);
    private final Joystick technicianController = new Joystick(Constants.ControllerProfile.TECHNICIAM_CONTROLLER);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Subsystems */
    private final SwerveSubsytem m_SwerveSubsytem = new SwerveSubsytem();
    private final GripperSubsystem m_GripperSubsystem = new GripperSubsystem();
    private final ArmSubsystem m_ArmSubsystem = new ArmSubsystem();
    private final AirSubsystem m_AirSubsystem = new AirSubsystem();
    private final LightingSubsystem m_LightingSubsystem = new LightingSubsystem();

    /* Sendable Choosers */
    SendableChooser<Command> m_LightsChooser = new SendableChooser<>();
    SendableChooser<Command> m_AutonChooser = new SendableChooser<>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();

        // SwerveDrive default command
        m_SwerveSubsytem.setDefaultCommand(
                new TeleopSwerve(
                        m_SwerveSubsytem,
                        () -> driverController.getRawAxis(translationAxis),
                        () -> driverController.getRawAxis(strafeAxis),
                        () -> driverController.getRawAxis(rotationAxis),
                        () -> true));

        // A chooser for Lightshow commands
        m_LightsChooser.setDefaultOption("TeleOp Mode", new RedAllianceLightShow(m_LightingSubsystem));
        m_LightsChooser.addOption("test mode", new TechnicianLightshow(m_LightingSubsystem));
        SmartDashboard.putData(m_LightsChooser);

        /* Chooser for Auton Commands */
        m_AutonChooser.setDefaultOption("Outside Auto", new OutsidePosAuto(m_SwerveSubsytem));
        SmartDashboard.putData(m_AutonChooser);
    }
    
    private void configureButtonBindings() {
        /* Button Box */
        JoystickButton a_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kA.value);
        JoystickButton b_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kB.value);
        JoystickButton x_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kX.value);
        JoystickButton y_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kY.value);
        JoystickButton rb_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kRightBumper.value);
        JoystickButton lb_driverButton = new JoystickButton(driverController,  
                XboxController.Button.kRightBumper.value);
        JoystickButton start_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kStart.value);
        JoystickButton back_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kBack.value);
        JoystickButton rs_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kRightStick.value);
        JoystickButton ls_driverButton = new JoystickButton(driverController, 
                XboxController.Button.kLeftStick.value);
        JoystickButton rt_driverButton = new JoystickButton(driverController, 
                XboxController.Axis.kRightTrigger.value);
        JoystickButton lt_driverButton = new JoystickButton(driverController, 
                XboxController.Axis.kLeftTrigger.value);

        JoystickButton a_munipulatorButton = new JoystickButton(munipulatorController, 
                XboxController.Button.kA.value);
        JoystickButton b_munipulatorButton = new JoystickButton(munipulatorController, 
                XboxController.Button.kB.value);
        JoystickButton x_munipulatorButton = new JoystickButton(munipulatorController, 
                XboxController.Button.kX.value);
        JoystickButton y_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kY.value);
        JoystickButton rb_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kRightBumper.value);
        JoystickButton lb_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kLeftBumper.value);
        JoystickButton start_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kStart.value);
        JoystickButton back_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kBack.value);
        JoystickButton rs_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kRightStick.value);
        JoystickButton ls_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kLeftStick.value);
        JoystickButton rt_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Axis.kRightTrigger.value);
        JoystickButton lt_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Axis.kLeftTrigger.value);

        JoystickButton a_technicianButton = new JoystickButton(technicianController, 
                XboxController.Button.kA.value);
        JoystickButton b_technicianButton = new JoystickButton(technicianController, 
                XboxController.Button.kB.value);
        JoystickButton x_technicianButton = new JoystickButton(technicianController, 
                XboxController.Button.kX.value);
        JoystickButton y_technicianButton = new JoystickButton(technicianController, 
                XboxController.Button.kY.value);
        JoystickButton rb_technicianButton = new JoystickButton(technicianController,
                XboxController.Button.kRightBumper.value);
        JoystickButton lb_technicianButton = new JoystickButton(technicianController,
                XboxController.Button.kRightBumper.value);
        JoystickButton start_technicianButton = new JoystickButton(technicianController,
                XboxController.Button.kStart.value);
        JoystickButton back_technicianButton = new JoystickButton(technicianController,
                XboxController.Button.kBack.value);
        JoystickButton rs_technicianButton = new JoystickButton(technicianController,
                XboxController.Button.kRightStick.value);
        JoystickButton ls_technicianButton = new JoystickButton(technicianController,
                XboxController.Button.kLeftStick.value);
        JoystickButton rt_technicianButton = new JoystickButton(technicianController,
                XboxController.Axis.kRightTrigger.value);
        JoystickButton lt_technicianButton = new JoystickButton(technicianController,
                XboxController.Axis.kLeftTrigger.value);

        /* Driver Button Commands */

        /* Munipulator Button Commands */
        a_munipulatorButton.onTrue(new GripperControl(m_GripperSubsystem, m_AirSubsystem));
        y_munipulatorButton.onTrue(new ArmControl(m_ArmSubsystem, m_AirSubsystem));
        /* Technician Command */
        
    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return m_AutonChooser.getSelected();
    }

    public Command getTeleopLightingCommand() {
        // Alliance color selector for leds
        return m_LightsChooser.getSelected();        
    }

    public Command getDisabledCommand() {
        // Command to reset robot to initial state
        Command disabled = new SetDisabledState(m_LightingSubsystem);
        return disabled;        
    }
}
