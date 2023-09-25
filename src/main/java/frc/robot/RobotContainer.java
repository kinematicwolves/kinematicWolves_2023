package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.GripperEject;
import frc.robot.commands.PickupCube;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.Auton.OutsidePosAuto;
import frc.robot.commands.LightshowCommands.SetDisabledState;
import frc.robot.commands.LightshowCommands.TechnicianLightshow;
import frc.robot.commands.LightshowCommands.TeleOpLightshow;
import frc.robot.commands.TechnitianCommands.ArmControl;
import frc.robot.commands.TechnitianCommands.GripperControl;
import frc.robot.commands.TechnitianCommands.GripperMotors;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.LightingSubsystem;
import frc.robot.subsystems.SwerveSubsytem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    /* Controllers */
    private final Joystick driverController = new Joystick(Constants.ControllerProfile.DRIVER_CONTROLLER);
    private final Joystick munipulatorController = new Joystick(Constants.ControllerProfile.MUNIPULATOR_CONTROLLER);
    private final Joystick technitianController = new Joystick(Constants.ControllerProfile.TECHNICIAM_CONTROLLER);

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
        m_LightsChooser.setDefaultOption("TeleOp Mode", new TeleOpLightshow(m_LightingSubsystem));
        m_LightsChooser.addOption("Technitian Mode" , new TechnicianLightshow(m_LightingSubsystem));
        SmartDashboard.putData(m_LightsChooser);

        /* Chooser for Auton Commands */
        m_AutonChooser.setDefaultOption("Taxi Auto", new OutsidePosAuto(m_SwerveSubsytem));
        SmartDashboard.putData(m_AutonChooser);
    }
    
    private void configureButtonBindings() {
/* Button Box */
        // Munipulator Controller
        JoystickButton rb_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kRightBumper.value);
        JoystickButton lb_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kLeftBumper.value);
                JoystickButton a_munipulatorButton = new JoystickButton(munipulatorController,
                XboxController.Button.kA.value);

        // Technitian Controller
        JoystickButton a_technitianButton = new JoystickButton(technitianController,
                XboxController.Button.kA.value);
        JoystickButton b_technitianButton = new JoystickButton(technitianController, 
                XboxController.Button.kB.value);
        JoystickButton x_technitianButton = new JoystickButton(technitianController, 
                XboxController.Button.kX.value);
        JoystickButton y_technitionButton = new JoystickButton(technitianController,
                XboxController.Button.kY.value);

/* Driver Button Commands */
        /* Munipulator Button Commands */
        lb_munipulatorButton.onTrue(new PickupCube(m_GripperSubsystem, m_ArmSubsystem, m_AirSubsystem, m_LightingSubsystem));
        rb_munipulatorButton.whileTrue(new GripperEject(m_GripperSubsystem, m_AirSubsystem, m_LightingSubsystem));
        a_munipulatorButton.onTrue(new ArmControl(m_ArmSubsystem, m_AirSubsystem, m_LightingSubsystem));

        /* Technitian button commands */
        a_technitianButton.onTrue(new ArmControl(m_ArmSubsystem, m_AirSubsystem, m_LightingSubsystem));
        b_technitianButton.whileTrue(new GripperMotors(m_GripperSubsystem, m_LightingSubsystem, 0.5));
        x_technitianButton.whileTrue(new GripperMotors(m_GripperSubsystem, m_LightingSubsystem, -0.5));
        y_technitionButton.onTrue(new GripperControl(m_GripperSubsystem, m_AirSubsystem, m_LightingSubsystem));
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
