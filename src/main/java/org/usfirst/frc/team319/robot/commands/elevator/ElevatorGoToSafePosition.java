/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.elevator;

import org.usfirst.frc.team319.models.MotionMagicPositionCommand;
import org.usfirst.frc.team319.robot.Robot;

public class ElevatorGoToSafePosition extends MotionMagicPositionCommand {
  private boolean alreadySafe = false;
  private boolean force = false;

  public ElevatorGoToSafePosition(boolean force) {
    super(Robot.elevator, Robot.elevator.getBbaClearancePosition() + 200);
    this.force = force;
  }

  @Override
  protected void initialize() {
    int elevatorPos = Robot.elevator.getCurrentPosition();
    boolean bbaSafe = Robot.bbarm.getCurrentPosition() <= Robot.bbarm.getElevatorClearencePosition();
    boolean carriageAboveSafePos = elevatorPos >= Robot.elevator.getBbaClearancePosition();
    boolean plentyOfClearance = elevatorPos >= Robot.elevator.getLotsOfClearancePosition();
    boolean targetPositionIsSafe = Robot.elevator.getTargetPosition() >= Robot.elevator.getBbaClearancePosition();
    if ((bbaSafe || (carriageAboveSafePos && targetPositionIsSafe) || plentyOfClearance) && !force) {
      alreadySafe = true;
      return;
    }
    super.initialize();
  }

  @Override
  protected boolean isFinished() {
    return alreadySafe || super.isFinished();
  }
}