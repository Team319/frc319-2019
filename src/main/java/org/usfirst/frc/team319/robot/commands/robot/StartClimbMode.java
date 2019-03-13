/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToTouchFloorPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorLockCarriage;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class StartClimbMode extends CommandGroup {
  /**
   * Add your docs here.
   */
  public StartClimbMode() {
    addSequential(new GoToClimbStartPose());
    addSequential(new ElevatorLockCarriage());
    addSequential(new WaitCommand(0.5));
    addSequential(new ElevatorGoToTouchFloorPosition());
    addSequential(new SetRobotMode(RobotMode.Climb));
  }
}
