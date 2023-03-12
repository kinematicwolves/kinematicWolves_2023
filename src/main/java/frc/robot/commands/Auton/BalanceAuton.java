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
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.SwerveSubsytem;


public class BalanceAuton extends SequentialCommandGroup {

    private SwerveSubsytem m_drivetrain;
    private ArmSubsystem m_arm;


    public static final Rotation2d rotationOffset = Rotation2d.fromDegrees(180);

    public BalanceAuton(SwerveSubsytem drivetrain,ArmSubsystem arm)
    {
        //Assign local variables to parameters
        m_drivetrain = drivetrain;
        m_arm = arm;

arm.runInnerArm(0);
        
        //Move the robot - backwards?????
        
        /*
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);

        // An example trajectory to follow.  All units in meters.
       
        // This will load the file "Example Path.path" and generate it with a max velocity of 4 m/s and a max acceleration of 3 m/s^2
        PathPlannerTrajectory examplePath = PathPlanner.loadPath("Test Path", new PathConstraints(4, 3));

        config.setReversed(true);
        Trajectory exampleTrajectory =
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
                exampleTrajectory,
                m_drivetrain::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                m_drivetrain::setModuleStates,
                m_drivetrain);*/


            PathPlannerTrajectory traj1 = PathPlanner.generatePath(
        new PathConstraints(4, 3), 
        new PathPoint(new Translation2d(0.0, 0.0), Rotation2d.fromDegrees(0)), // position, heading
        new PathPoint(new Translation2d(0, 3), Rotation2d.fromDegrees(0))); // position, heading

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
            
        //   new InstantCommand(() -> m_drivetrain.resetOdometry(traj1.getInitialPose())),//exampleTrajectory
            //swerveControllerCommand,
           //` new InstantCommand(() -> m_drivetrain.deadCat())
            //Todo add a second command to finish the rotation
        );

    }
    
}
