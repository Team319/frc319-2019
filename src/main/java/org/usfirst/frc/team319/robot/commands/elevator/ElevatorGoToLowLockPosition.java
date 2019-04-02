/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.elevator;

import org.usfirst.frc.team319.models.MotionMagicPositionCommand;
import org.usfirst.frc.team319.robot.Robot;

public class ElevatorGoToLowLockPosition extends MotionMagicPositionCommand {
  /**
   * Add your docs here.
   */
  public ElevatorGoToLowLockPosition() {
    super(Robot.elevator, Robot.elevator.getLowlockPosition());
  }
}
