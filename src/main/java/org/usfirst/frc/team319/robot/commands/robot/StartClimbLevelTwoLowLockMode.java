/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.commands.bba.BbaSafelyGoToClimbLevelTwoStartPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorBrakeMode;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToClimbPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToLockPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToLowLockPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorLockCarriage;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorPrepareForClimb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class StartClimbLevelTwoLowLockMode extends CommandGroup {
  /**
   * Add your docs here.
   */
  public StartClimbLevelTwoLowLockMode() {
    addParallel(new ElevatorPrepareForClimb());
    addSequential(new BbaSafelyGoToClimbLevelTwoStartPosition());
    addSequential(new ElevatorGoToClimbPosition(), 1.0);
    addSequential(new SetRobotMode(RobotMode.Climb));
    addSequential(new ElevatorBrakeMode());

  }
}
