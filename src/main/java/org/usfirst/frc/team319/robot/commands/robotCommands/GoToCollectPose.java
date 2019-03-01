/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robotCommands;

import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BbaGoToCargoCollect;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BbaGoToCarriageSafePosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToCargoCollectPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToSafePossition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToCollectPose extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GoToCollectPose() {
    addSequential(new ElevatorGoToSafePossition());
    addSequential(new BbaGoToCarriageSafePosition());
    addParallel(new BbaGoToCargoCollect());
    addSequential(new ElevatorGoToCargoCollectPosition());
  }
}
