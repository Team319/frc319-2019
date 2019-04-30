/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.hatch_collector;

import org.usfirst.frc.team319.models.DriveMode;
import org.usfirst.frc.team319.robot.commands.limelight.TurnLedOn;
import org.usfirst.frc.team319.robot.commands.limelight.WaitForLimelightDistance;
import org.usfirst.frc.team319.robot.commands.robot.DriverControllerTimedRumble;
import org.usfirst.frc.team319.robot.commands.robot.SetDriveMode;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HatchCollectorPlaceAtDistance extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HatchCollectorPlaceAtDistance() {
    addSequential(new SetDriveMode(DriveMode.Limelight));
    addSequential(new TurnLedOn());
    addSequential(new WaitCommand(0.5));
    addSequential(new WaitForLimelightDistance(2.0));// in feet
    addSequential(new HatchCollectorExtend());
    addSequential(new WaitCommand(0.5));
    addSequential(new SetDriveMode(DriveMode.Normal));
    addSequential(new DriverControllerTimedRumble(1.0, 0.5));

  }
}
