/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ElevatorPrepareForClimb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorPrepareForClimb() {
    addSequential(new ElevatorGoToLowLockPosition());
    addSequential(new ElevatorLockCarriage());
    addSequential(new WaitCommand(0.3));
    addSequential(new ElevatorGoToLockPosition());
  }
}
