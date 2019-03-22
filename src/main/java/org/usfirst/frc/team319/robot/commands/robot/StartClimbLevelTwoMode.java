/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToClimbPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorLockCarriage;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class StartClimbLevelTwoMode extends CommandGroup {
  /**
   * Add your docs here.
   */
  public StartClimbLevelTwoMode() {
    addSequential(new GoToClimbLevelTwoStartPose());
    addSequential(new ElevatorLockCarriage());
    addSequential(new WaitCommand(0.5));
    addSequential(new ElevatorGoToClimbPosition(), 1.0);
    addSequential(new SetRobotMode(RobotMode.Climb));
  }
}
