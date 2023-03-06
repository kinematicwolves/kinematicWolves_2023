package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsytem;

public class StopSwerve extends CommandBase {

    private Translation2d m_translation;

    private SwerveSubsytem m_swerveDrivetrain;

    /**
     * 
     * Stops the drivetrain movement, called at the end of every auto path
     * 
     */

    public StopSwerve(SwerveSubsytem swerveDrivetrain) {

        m_swerveDrivetrain = swerveDrivetrain;
        addRequirements(m_swerveDrivetrain);

    }

    @Override
    public void execute() {

        m_translation = new Translation2d(0, 0).times(Constants.SwerveProfile.maxSpeed);
        m_swerveDrivetrain.drive(m_translation, 0, true, true);

    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
