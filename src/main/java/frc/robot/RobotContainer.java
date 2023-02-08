package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveRobotOpenLoop;
import frc.robot.commands.ZeroGyro;
import frc.robot.subsystems.SwerveSubsytem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final XboxController m_driverController = new XboxController(0);
    
    /* Subsystems */
    private final SwerveSubsytem s_Swerve = new SwerveSubsytem();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {;
        // Configure the button bindings
        configureButtonBindings();
        setDefaultCommands();
    }

    public void setDefaultCommands(){
        s_Swerve.setDefaultCommand(new DriveRobotOpenLoop(s_Swerve, m_driverController));
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        JoystickButton a_driverController = new JoystickButton(m_driverController, XboxController.Button.kA.value);

        a_driverController.whenPressed(new ZeroGyro(s_Swerve));
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
