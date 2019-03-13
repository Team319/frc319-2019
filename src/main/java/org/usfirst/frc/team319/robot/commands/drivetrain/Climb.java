/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.drivetrain;

import org.usfirst.frc.team319.robot.commands.bba.BbaGoToCarriageSafePosition;
import org.usfirst.frc.team319.robot.commands.bba.BbaLiftRobotPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToClimbPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorGoToMiddleCargoPosition;
import org.usfirst.frc.team319.robot.commands.elevator.ElevatorLockCarriage;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Climb extends CommandGroup {
  public Climb() {
    addSequential(new ElevatorGoToMiddleCargoPosition());
    addSequential(new WaitCommand(1.0));
    addSequential(new BbaGoToCarriageSafePosition());
    addSequential(new WaitCommand(0.5));
    addSequential(new ElevatorLockCarriage());
    addSequential(new WaitCommand(1.0));
    addParallel(new BbaLiftRobotPosition());
    addSequential(new ElevatorGoToClimbPosition());
  }
}
