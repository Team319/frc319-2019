/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.robot.commands.bba.BbaSafelyGoToClimbStartPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToLowLockPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToLowLockClimbStartPose extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GoToLowLockClimbStartPose() {
    addParallel(new ElevatorGoToLowLockPosition());
    addSequential(new BbaSafelyGoToClimbStartPosition());
  }
}
