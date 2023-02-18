package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoAlignment;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.ZeroGyro;
import frc.robot.subsystems.SwerveSubsytem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driverController = new Joystick(Constants.ControllerProfile.DRIVER_CONTROLLER);
    private final Joystick munipulatorController = new Joystick(Constants.ControllerProfile.MUNIPULATOR_CONTROLLER);

    /* Driver Controller Map
        * A = Zero Gyro
        * B = Auto align with mid node
        */
    /* Munipulator Controller Map
        * 
        */

    /* Joystick Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    private final boolean robotCentric = true;

    /* Subsystems */
    private final SwerveSubsytem m_SwerveSubsytem = new SwerveSubsytem();
    private final VisionSubsystem m_VisionSubsystem = new VisionSubsystem();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        // Set Default Commands
        setDefaultCommands();

        m_SwerveSubsytem.setDefaultCommand(
            new TeleopSwerve(
                m_SwerveSubsytem, 
                () -> driverController.getRawAxis(translationAxis), 
                () -> driverController.getRawAxis(strafeAxis), 
                () -> driverController.getRawAxis(rotationAxis), 
                () -> robotCentric
            )
        );
    }

    private void setDefaultCommands(){}

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Button Box */
        JoystickButton a_driverButton = new JoystickButton(driverController, XboxController.Button.kA.value);
        JoystickButton b_driverButton = new JoystickButton(driverController, XboxController.Button.kB.value);
        JoystickButton x_riverButton = new JoystickButton(driverController, XboxController.Button.kX.value);
        JoystickButton y_driverButton = new JoystickButton(driverController, XboxController.Button.kY.value);
        JoystickButton rb_driverButton = new JoystickButton(driverController, XboxController.Button.kRightBumper.value);
        JoystickButton lb_driverButton = new JoystickButton(driverController, XboxController.Button.kRightBumper.value);
        JoystickButton start_driverButton = new JoystickButton(driverController, XboxController.Button.kStart.value);
        JoystickButton back_driverButton = new JoystickButton(driverController, XboxController.Button.kBack.value);
        JoystickButton rs_driverButton = new JoystickButton(driverController, XboxController.Button.kRightStick.value);
        JoystickButton ls_driverButton = new JoystickButton(driverController, XboxController.Button.kLeftStick.value);
        JoystickButton rt_driverButton = new JoystickButton(driverController, XboxController.Axis.kRightTrigger.value);
        JoystickButton lt_driverButton = new JoystickButton(driverController, XboxController.Axis.kLeftTrigger.value);
        
        JoystickButton a_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kA.value);
        JoystickButton b_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kB.value);
        JoystickButton x_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kX.value);
        JoystickButton y_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kY.value);
        JoystickButton rb_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kRightBumper.value);
        JoystickButton lb_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kRightBumper.value);
        JoystickButton start_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kStart.value);
        JoystickButton back_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kBack.value);
        JoystickButton rs_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kRightStick.value);
        JoystickButton ls_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kLeftStick.value);
        JoystickButton rt_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Axis.kRightTrigger.value);
        JoystickButton lt_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Axis.kLeftTrigger.value);


        /* Driver Button Commands */
        a_driverButton.onTrue(new ZeroGyro(m_SwerveSubsytem));
        b_driverButton.onTrue(new AutoAlignment(m_VisionSubsystem, m_SwerveSubsytem, 0.2, 0.2));

        /* Munipulator Button Commands */

            /* Driver Controller Map
         * A = Zero Gyro
         */
            /* Munipulator Controller Map 
         * 
        */
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }
}
