/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.limelight;

import org.usfirst.frc.team319.models.DriveMode;
import org.usfirst.frc.team319.robot.commands.robot.SetDriveMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLimelightMode extends CommandGroup {
  /**
   * Add your docs here.
   */
  public StartLimelightMode() {
    addSequential(new SetDriveMode(DriveMode.Limelight));
    addSequential(new TurnLedOn());
  }
}
