/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorUnlockCarriage;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RevertClimbMode extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RevertClimbMode() {
    addSequential(new SetRobotMode(RobotMode.Normal));
    addSequential(new ElevatorUnlockCarriage());
  }
}
