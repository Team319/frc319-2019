/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.autonomous_paths.RocketPaths;

import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PlatypusFaceExtend;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToLowHatchPosition;

import com.team319.follower.FollowArc;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.arcs.LeftOfHabLeftRocketLeftToRightFacePt1Arc;

public class LHabLRocketLToRFace extends CommandGroup {

  public LHabLRocketLToRFace() {
    addParallel(new ElevatorGoToLowHatchPosition());
    addSequential(new FollowArc(Robot.drivetrain, new LeftOfHabLeftRocketLeftToRightFacePt1Arc()));
    addSequential(new PlatypusFaceExtend());
  }
}
