package frc.robot.commands;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsytem;
import frc.robot.subsystems.Trigger;

public class AutonTest extends SequentialCommandGroup {

        private SwerveSubsytem m_drivetrain;
        private PathPlannerTrajectory m_trajectory1;

        public AutonTest(SwerveSubsytem drivetrain
                        ) {
                m_drivetrain = drivetrain;

                var m_translationController = new PIDController(Constants.AutoConstants.kPXController, 0, 0);
                var m_strafeController = new PIDController(Constants.AutoConstants.kPYController, 0, 0);
                var m_thetaController = new ProfiledPIDController(Constants.AutoConstants.kPThetaController, 0, 0,
                                Constants.AutoConstants.kThetaControllerConstraints);
                m_thetaController.enableContinuousInput(-Math.PI, Math.PI);

                m_trajectory1 = PathPlanner.loadPath("1_Ball_Auto_M1", Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                                Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared);

                SwerveControllerCommand m_trajectoryCommand1 = new SwerveControllerCommand(
                                m_trajectory1,
                                m_drivetrain::getPose,
                                Constants.SwerveProfile.swerveKinematics,
                                m_translationController,
                                m_strafeController,
                                m_thetaController,
                                m_drivetrain::setModuleStates,
                                m_drivetrain);
                        
                addCommands(
                                new InstantCommand(() -> m_drivetrain
                                                .resetOdometry(new Pose2d(7.656998485989203, 1.8302369377199519,
                                                                Rotation2d.fromDegrees(-90)))),
                                // new WaitCommand(5),
                                new StopSwerve(m_drivetrain),
                                new WaitCommand(1.0),
                                new StopSwerve(m_drivetrain)
                );
        }
}
