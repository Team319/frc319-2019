/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.drivetrain_Commands;

import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BbaLiftRobotPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorFloorExtendSolenoid;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToClimbPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Climb extends CommandGroup {
  public Climb() {
    addSequential(new ElevatorFloorExtendSolenoid());
    addParallel(new BbaLiftRobotPosition());
    addSequential(new ElevatorGoToClimbPosition());
  }
}
