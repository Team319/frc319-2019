/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.BBArm_Commands;

import org.usfirst.frc.team319.robot.commands.Carriage_Commands.CollectorCollect;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToCargoCollectPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToSafePossition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CollectCargoCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CollectCargoCommandGroup() {
    addSequential(new ElevatorGoToSafePossition());
    addSequential(new BbaGoToCargoCollect());
    addSequential(new ElevatorGoToCargoCollectPosition());
    addSequential(new CollectorCollect(0.5));
  }
}
