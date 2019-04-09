/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.hatch_collector;

import org.usfirst.frc.team319.robot.commands.drivetrain.VisionDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HatchCollectorLimelightCollect extends CommandGroup {

  public HatchCollectorLimelightCollect() {
    addSequential(new VisionDrive(1.0, 0));
    addParallel(new HatchCollectorExtend());
    addSequential(new WaitCommand(0.3));
    addSequential(new HatchCollectorOpen());
    addSequential(new WaitCommand(0.3));
    addSequential(new HatchCollectorRetract());
  }

}
