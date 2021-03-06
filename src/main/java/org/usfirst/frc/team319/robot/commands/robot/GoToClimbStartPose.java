/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.robot.commands.bba.BbaSafelyGoToClimbStartPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToLockPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToClimbStartPose extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GoToClimbStartPose() {
    addParallel(new ElevatorGoToLockPosition());
    addSequential(new BbaSafelyGoToClimbStartPosition());
  }
}
