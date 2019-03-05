/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.LimelightCommands;

import org.usfirst.frc.team319.robot.commands.drivetrain_Commands.VisionDrive;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorClose;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorExtend;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorRetract;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class LimelightHatchCollectorPlace extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LimelightHatchCollectorPlace() {
    addSequential(new VisionDrive(3.0, 0.0));
    addSequential(new HatchCollectorExtend());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchCollectorClose());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchCollectorRetract());
  }
}