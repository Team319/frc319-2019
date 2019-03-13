/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.robot.commands.bba.BbaSafelyGoToHomePosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToMiddleCargoPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToMiddleCargoPose extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GoToMiddleCargoPose() {
    addParallel(new ElevatorGoToMiddleCargoPosition());
    addSequential(new BbaSafelyGoToHomePosition());
  }
}
