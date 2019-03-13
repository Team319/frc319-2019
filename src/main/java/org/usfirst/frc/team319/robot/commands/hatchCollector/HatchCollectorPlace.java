/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.hatchCollector;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HatchCollectorPlace extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchCollectorPlace() {
    addSequential(new HatchCollectorExtend());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchCollectorClose());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchCollectorRetract());
  }
}