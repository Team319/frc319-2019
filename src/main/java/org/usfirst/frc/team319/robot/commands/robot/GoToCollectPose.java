/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.robot.commands.bba.BbaGoToCargoCollect;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToSafePosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorSafelyGoToCargoCollectPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToCollectPose extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GoToCollectPose() {
    addSequential(new ElevatorGoToSafePosition(false));
    addParallel(new BbaGoToCargoCollect());
    addSequential(new ElevatorSafelyGoToCargoCollectPosition());
  }
}
