/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.carriage;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team319.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PassthroughSpit extends Command {
  private double targetSpeed;

  public PassthroughSpit() {
    requires(Robot.carriage);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double spitPower = -Robot.oi.driverController.triggers.getTwist();

    if (spitPower < 0) {
      targetSpeed = -(spitPower * spitPower) * 0.75;
    } else {
      targetSpeed = spitPower * spitPower;
    }
    // targetSpeed /= 2;
    Robot.carriage.passThroughLead.set(ControlMode.PercentOutput, targetSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.carriage.passThroughLead.set(ControlMode.PercentOutput, 0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.carriage.passThroughLead.set(ControlMode.PercentOutput, 0.0);
  }
}
