package frc.robot;

import javax.management.openmbean.OpenType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArmTest;
import frc.robot.commands.TeleOpLightshow;
import frc.robot.commands.SetDisabledState;
import frc.robot.commands.CollectWithSensor;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.TurnTurretToFwdPos;
import frc.robot.commands.TurnTurretToInitPos;
import frc.robot.commands.TurnTurretToRvsPos;
import frc.robot.commands.ToggleSpeedLimit;
import frc.robot.commands.ZeroGyro;
import frc.robot.subsystems.LightingSubsystem;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsytem;
import frc.robot.subsystems.SwerveSubsytem;
import frc.robot.subsystems.TurretSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Driver Controller Map
        * A = Zero Gyro
        */
    /* Munipulator Controller Map
        * 
        */

    /* Controllers */
    private final Joystick driverController = new Joystick(Constants.ControllerProfile.DRIVER_CONTROLLER);
    private final Joystick munipulatorController = new Joystick(Constants.ControllerProfile.MUNIPULATOR_CONTROLLER);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    private final boolean robotCentric = false;
    /* Subsystems */
    private final SwerveSubsytem m_SwerveSubsytem = new SwerveSubsytem();
    private final LightingSubsystem m_LightingSubsystem = new LightingSubsystem();
    private final GripperSubsytem m_GripperSubsytem = new GripperSubsytem();
    private final AirSubsystem m_AirSubsystem = new AirSubsystem();
    private final TurretSubsystem m_TurretSubsystem = new TurretSubsystem();
    private final ArmSubsystem m_ArmSubsystem = new ArmSubsystem();

    /* Sendable Choosers */
    SendableChooser<Command> m_LightsChooser = new SendableChooser<>(); 

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
        
        // A chooser for Lightshow commands
        m_LightsChooser.setDefaultOption("Red Alliance Lighshow", new TeleOpLightshow(m_LightingSubsystem));
        SmartDashboard.putData(m_LightsChooser);
    }

    private void setDefaultCommands(){
       //m_GripperSubsytem.setDefaultCommand(new CollectWithSensor(m_GripperSubsytem, m_AirSubsystem, m_LightingSubsystem));
       //m_ArmSubsystem.setDefaultCommand(new runAutomatic(m_ArmSubsystem));
    }

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
        //b_driverButton.whileTrue(new ArmTest(m_ArmSubsystem));
        // a_driverButton.onTrue(new InstantCommand(() -> m_GripperSubsytem.runGripperWheels(0.25)));
        // x_riverButton.onTrue(new InstantCommand(() -> m_ArmSubsystem.runOuterArm(0.1)));
       // y_driverButton.onTrue(new InstantCommand(() -> m_ArmSubsystem.runOuterArm(0.1)));

        b_driverButton.onTrue(new RedAllianceLightshow(m_LightingSubsystem));
        lt_driverButton.whileTrue(new ToggleSpeedLimit(m_SwerveSubsytem));

        /* Munipulator Button Commands */
        x_munipulatorButton.onTrue(new TurnTurretToRvsPos(m_TurretSubsystem));
        y_munipulatorButton.onTrue(new TurnTurretToInitPos(m_TurretSubsystem));
        b_munipulatorButton.onTrue(new TurnTurretToFwdPos(m_TurretSubsystem));

    }

/*  Things to add from Tomai.
/*  Wrist inputs
/*  Outer Arm
/*  Outer Arm
/*  Add buttons for Arm Setpoints
/*  Auto_Align
/*  Turret rotation input by driver
/*  Wrist cannot go past an encoder count where it hits the robot in initial position based off driver input
 *  Turret rotation not allowed when not in initial position
 *  
 */

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }

    public Command getTeleopLightingCommand(){
        // Alliance color selector for leds
        Command teleOp = new TeleOpLightshow(m_LightingSubsystem);
        return teleOp;
       }

    public Command getDisabledCommand(){
        // Command to reset robot to initial state
        Command disabled = new SetDisabledState(m_LightingSubsystem);
        return disabled;
      }
}
