/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.controllers.BobXboxController;
import org.usfirst.frc.team319.robot.commands.StartControllerRumble;
import org.usfirst.frc.team319.robot.commands.StopControllerRumble;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TimedRumble extends CommandGroup {
  /**
   * Add your docs here.
   */
  public TimedRumble(BobXboxController controller, double strength, double time) {
    addSequential(new StartControllerRumble(controller, strength));
    addSequential(new WaitCommand(time));
    addSequential(new StopControllerRumble(controller));

  }
}
