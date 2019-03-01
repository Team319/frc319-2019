/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robotCommands;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorLockCarriage;

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
    addSequential(new SetRobotMode(RobotMode.Climb));
  }
}
