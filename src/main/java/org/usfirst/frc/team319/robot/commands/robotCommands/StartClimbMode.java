/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robotCommands;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.commands.elevatorCommands.ElevatorGoToTouchFloorPosition;
import org.usfirst.frc.team319.robot.commands.elevatorCommands.ElevatorLockCarriage;

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
