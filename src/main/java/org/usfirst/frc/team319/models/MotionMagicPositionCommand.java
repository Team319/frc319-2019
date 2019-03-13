/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.models;

import edu.wpi.first.wpilibj.command.Command;

public class MotionMagicPositionCommand extends Command {

  private int targetPosition = 0;
  private PositionControlledSubsystem requiredSubsystem;
  private boolean succesfullySetPosition = false;

  public MotionMagicPositionCommand(PositionControlledSubsystem subsystem, int targetPosition) {
    this.requiredSubsystem = subsystem;
    this.targetPosition = targetPosition;
    requires(requiredSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    this.succesfullySetPosition = requiredSubsystem.setTargetPosition(targetPosition);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    requiredSubsystem.motionMagicControl();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !this.succesfullySetPosition || requiredSubsystem.isInPosition(targetPosition);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
