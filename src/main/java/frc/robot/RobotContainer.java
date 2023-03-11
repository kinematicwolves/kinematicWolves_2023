package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ConeSignal;
import frc.robot.commands.CubeSignal;
import frc.robot.commands.IntakeControl;
import frc.robot.commands.SetArmToMid;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.ToggleSpeedLimit;
import frc.robot.commands.ZeroArmCloseLoop;
import frc.robot.commands.ZeroGyro;
import frc.robot.commands.toggleLimelight;
import frc.robot.commands.Auton.OneConeAuton;
import frc.robot.commands.LightshowCommands.SetDisabledState;
import frc.robot.commands.LightshowCommands.TeleOpLightshow;
import frc.robot.commands.LightshowCommands.TestingLightshow;
import frc.robot.commands.TestCommands.RunInnerArm;
import frc.robot.commands.TestCommands.RunOuterArm;
import frc.robot.commands.TestCommands.RunWrist;
import frc.robot.commands.TestCommands.SetArmToMidTest;
import frc.robot.commands.TestCommands.ToggleCompressor;
import frc.robot.commands.TurretCommands.TurnTurretToFwdPos;
import frc.robot.commands.TurretCommands.TurnTurretToInitPos;
import frc.robot.commands.TurretCommands.TurnTurretToRvsPos;
import frc.robot.subsystems.AirSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsytem;
import frc.robot.subsystems.LightingSubsystem;
import frc.robot.subsystems.SwerveSubsytem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Driver Controller Map
        * A = Zero Gyro
        * B = Auto align with mid node
        */
    /* Munipulator Controller Map
        * 
        */

    /* Joystick Controls */
    /* Controllers */
    private final Joystick driverController = new Joystick(Constants.ControllerProfile.DRIVER_CONTROLLER);
    private final Joystick munipulatorController = new Joystick(Constants.ControllerProfile.MUNIPULATOR_CONTROLLER);
    private final Joystick technicianController = new Joystick(Constants.ControllerProfile.TECHNICIAM_CONTROLLER);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    private final boolean robotCentric = true;
    /* Subsystems */
    private final SwerveSubsytem m_SwerveSubsytem = new SwerveSubsytem();
    private final VisionSubsystem m_VisionSubsystem = new VisionSubsystem();
    private final LightingSubsystem m_LightingSubsystem = new LightingSubsystem();
    private final GripperSubsytem m_GripperSubsytem = new GripperSubsytem();
    private final AirSubsystem m_AirSubsystem = new AirSubsystem();
    private final TurretSubsystem m_TurretSubsystem = new TurretSubsystem();
    private final ArmSubsystem m_ArmSubsystem = new ArmSubsystem();

    /* Sendable Choosers */
    SendableChooser<Command> m_LightsChooser = new SendableChooser<>(); 
    SendableChooser<Command> m_AutonChooser = new SendableChooser<>();

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
        m_LightsChooser.setDefaultOption("TeleOp Mode", new TeleOpLightshow(m_LightingSubsystem));
        m_LightsChooser.addOption("Testing Mode", new TestingLightshow(m_LightingSubsystem));
        SmartDashboard.putData(m_LightsChooser);

        /* Chooser for Auton Commands */
        m_AutonChooser.setDefaultOption("One Cone Auto", new OneConeAuton(m_SwerveSubsytem));
    }

    private void setDefaultCommands(){
        m_ArmSubsystem.setDefaultCommand(new ZeroArmCloseLoop(m_ArmSubsystem));
     //   m_ArmSubsystem.setDefaultCommand();
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
        JoystickButton x_driverButton = new JoystickButton(driverController, XboxController.Button.kX.value);
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
        JoystickButton lb_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kLeftBumper.value);
        JoystickButton start_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kStart.value);
        JoystickButton back_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kBack.value);
        JoystickButton rs_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kRightStick.value);
        JoystickButton ls_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Button.kLeftStick.value);
        JoystickButton rt_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Axis.kRightTrigger.value);
        JoystickButton lt_munipulatorButton = new JoystickButton(munipulatorController, XboxController.Axis.kLeftTrigger.value);

        JoystickButton a_technicianButton = new JoystickButton(technicianController, XboxController.Button.kA.value);
        JoystickButton b_technicianButton = new JoystickButton(technicianController, XboxController.Button.kB.value);
        JoystickButton x_technicianButton = new JoystickButton(technicianController, XboxController.Button.kX.value);
        JoystickButton y_technicianButton = new JoystickButton(technicianController, XboxController.Button.kY.value);
        JoystickButton rb_technicianButton = new JoystickButton(technicianController, XboxController.Button.kRightBumper.value);
        JoystickButton lb_technicianButton = new JoystickButton(technicianController, XboxController.Button.kRightBumper.value);
        JoystickButton start_technicianButton = new JoystickButton(technicianController, XboxController.Button.kStart.value);
        JoystickButton back_technicianButton = new JoystickButton(technicianController, XboxController.Button.kBack.value);
        JoystickButton rs_technicianButton = new JoystickButton(technicianController, XboxController.Button.kRightStick.value);
        JoystickButton ls_technicianButton = new JoystickButton(technicianController, XboxController.Button.kLeftStick.value);
        JoystickButton rt_technicianButton = new JoystickButton(technicianController, XboxController.Axis.kRightTrigger.value);
        JoystickButton lt_technicianButton = new JoystickButton(technicianController, XboxController.Axis.kLeftTrigger.value);



        /* Driver Button Commands */
        a_driverButton.onTrue(new ZeroGyro(m_SwerveSubsytem));
        lt_driverButton.whileTrue(new ToggleSpeedLimit(m_SwerveSubsytem));

        /* Munipulator Button Commands */
        a_munipulatorButton.onTrue(new IntakeControl(m_GripperSubsytem, m_AirSubsystem));
        rb_munipulatorButton.whileTrue(new SetArmToMid(m_ArmSubsystem));
        y_munipulatorButton.whileTrue(new TurnTurretToInitPos(m_TurretSubsystem));
        x_munipulatorButton.whileTrue(new TurnTurretToRvsPos(m_TurretSubsystem));
        b_munipulatorButton.whileTrue(new TurnTurretToFwdPos(m_TurretSubsystem));
        start_munipulatorButton.onTrue(new ConeSignal(m_LightingSubsystem));
        back_munipulatorButton.onTrue(new CubeSignal(m_LightingSubsystem));
        lb_munipulatorButton.whileTrue(new RunWrist(m_ArmSubsystem, m_LightingSubsystem));

        /* Technician Button Commands */
        a_technicianButton.onTrue(new ToggleCompressor(m_AirSubsystem, m_LightingSubsystem));
        y_technicianButton.whileTrue(new RunInnerArm(m_ArmSubsystem, m_LightingSubsystem));
        x_technicianButton.whileTrue(new RunOuterArm(m_ArmSubsystem, m_LightingSubsystem));
        b_technicianButton.whileTrue(new RunWrist(m_ArmSubsystem, m_LightingSubsystem));
        rb_technicianButton.whileTrue(new SetArmToMidTest(m_ArmSubsystem, m_LightingSubsystem));
        lb_technicianButton.whileTrue(new IntakeControl(m_GripperSubsytem, m_AirSubsystem));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return m_AutonChooser.getSelected();
    }

    public Command getTeleopLightingCommand(){
        // Alliance color selector for leds
        return m_LightsChooser.getSelected();
       }

    public Command getDisabledCommand(){
        // Command to reset robot to initial state
        Command disabled = new SetDisabledState(m_LightingSubsystem);
        return disabled;
      }
}
