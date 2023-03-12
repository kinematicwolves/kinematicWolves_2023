package frc.robot.commands.Auton;

import java.util.List;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPoint;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsytem;


public class plsgodwork extends SequentialCommandGroup {

    private SwerveSubsytem m_drivetrain;

    public static final Rotation2d rotationOffset = Rotation2d.fromDegrees(180);

    public plsgodwork(SwerveSubsytem drivetrain)
    {
        //Assign local variables to parameters
        m_drivetrain = drivetrain;

        //Move the robot - backwards?????
        
        
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.SwerveProfile.swerveKinematics);
       
        PathPlannerTrajectory hudadkPathPlannerTrajectory = PathPlanner.loadPath("jacob", new PathConstraints(.5, .5));

        config.setReversed(true);
        Trajectory t_autobalance =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, Rotation2d.fromDegrees(0)), //0
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(3.9, 0), new Translation2d(1.00, 0)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(3.92, 0, Rotation2d.fromDegrees(90)), //180
                config);
                
                
        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand =
            new SwerveControllerCommand(
                hudadkPathPlannerTrajectory,
                m_drivetrain::getPose,
                Constants.SwerveProfile.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                m_drivetrain::setModuleStates,
                m_drivetrain);


        //     PathPlannerTrajectory uh_hudadkPathPlannerTrajectory = PathPlanner.generatePath(
        // new PathConstraints(2, 2), 
        // new PathPoint(new Translation2d(0.0, 0.0), Rotation2d.fromDegrees(0)), // position, heading
        // new PathPoint(new Translation2d(0, 1), Rotation2d.fromDegrees(0))); // position, heading


        //seems to work when you reverse the coordinate 
        //Todo: Create a second case here. 

 
        

    }
    
}
