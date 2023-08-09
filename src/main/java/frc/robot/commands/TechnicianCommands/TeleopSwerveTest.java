package frc.robot.commands.TechnicianCommands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsytem;


public class TeleopSwerveTest extends CommandBase {    
    private SwerveSubsytem s_Swerve;    
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;

    public TeleopSwerveTest(SwerveSubsytem s_Swerve, DoubleSupplier translationSup, DoubleSupplier strafeSup, DoubleSupplier rotationSup, BooleanSupplier robotCentricSup) {
        this.s_Swerve = s_Swerve;
        addRequirements(s_Swerve);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
    }

    @Override
    public void execute() {
        /* Get Values, Deadband */
        double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble() * 0.5, Constants.ControllerProfile.stickDeadband);
        double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble() * 0.5, Constants.ControllerProfile.stickDeadband);
        double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble() * 0.5, Constants.ControllerProfile.stickDeadband);

        /* Drive */
        s_Swerve.drive(
            new Translation2d(translationVal, strafeVal).times(Constants.SwerveProfile.maxSpeed), 
            rotationVal * Constants.SwerveProfile.maxAngularVelocity, 
            true, 
            true
        );
    }
}