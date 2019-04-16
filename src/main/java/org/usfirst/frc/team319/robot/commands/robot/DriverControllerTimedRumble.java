/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.robot.commands.StartDriverControllerRumble;
import org.usfirst.frc.team319.robot.commands.StopDriverControllerRumble;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriverControllerTimedRumble extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DriverControllerTimedRumble(double strength, double time) {
    addSequential(new StartDriverControllerRumble(strength));
    addSequential(new WaitCommand(time));
    addSequential(new StopDriverControllerRumble());
  }
}
