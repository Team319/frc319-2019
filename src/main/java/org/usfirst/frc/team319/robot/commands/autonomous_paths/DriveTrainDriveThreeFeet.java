/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.autonomous_paths;

import org.usfirst.frc.team319.robot.Robot;

import com.team319.follower.FollowArc;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.arcs.DistanceScalingArc;

public class DriveTrainDriveThreeFeet extends CommandGroup {

  public DriveTrainDriveThreeFeet() {
    addSequential(new FollowArc(Robot.drivetrain, new DistanceScalingArc()));
  }
}
