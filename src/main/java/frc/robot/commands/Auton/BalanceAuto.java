package frc.robot.commands.Auton;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;

/*
import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;*/

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsytem;


public class BalanceAuto extends SequentialCommandGroup {

    private SwerveSubsytem m_drivetrain;

    public static final Rotation2d rotationOffset = Rotation2d.fromDegrees(180);

    public BalanceAuto(SwerveSubsytem drivetrain)
    {
        //Assign local variables to parameters
        m_drivetrain = drivetrain;
            PathPlannerTrajectory traj1 = PathPlanner.generatePath(
        new PathConstraints(1, 2), 
        new PathPoint(new Translation2d(0.0, 0.0), Rotation2d.fromDegrees(0)), // position, heading
        new PathPoint(new Translation2d(3.5, 0), Rotation2d.fromDegrees(0))); // position, heading


        //seems to work when you reverse the coordinate 
        //Todo: Create a second case here. 

        var thetaController =
        new ProfiledPIDController(
            Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand =
            new SwerveControllerCommand(
                traj1,
                drivetrain::getPose,
                Constants.SwerveProfile.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                drivetrain::setModuleStates,
                drivetrain);


         addCommands(
            //new Instants(() -> )
           new InstantCommand(() -> m_drivetrain.resetOdometry(traj1.getInitialPose())),//exampleTrajectory

            //Todo add a second command to finish the rotation
            new InstantCommand(() -> m_drivetrain.resetOdometry(traj1.getInitialPose())),//exampleTrajectory
            swerveControllerCommand
           // new InstantCommand(() -> m_drivetrain.deadCat())
        );

    }
    
}
