/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.robot.commands.carriage.CollectorSetSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SpitCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public SpitCargo() {
    addSequential(new CollectorSetSpeed(1.0));
    addSequential(new WaitCommand(2.0));
    addSequential(new CollectorSetSpeed(0.0));
  }
}
