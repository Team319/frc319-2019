/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.hatch_collector;

import org.usfirst.frc.team319.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HatchCollectorStagedScore extends Command {
  private Timer timer = new Timer();

  public HatchCollectorStagedScore() {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.carriage);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
    SmartDashboard.putBoolean("Hatch Collector Extended", Robot.carriage.isHatchCollectorExtended);
    if (Robot.carriage.isHatchCollectorExtended) {
      Robot.pneumatics.fingerClose();
    } else {
      Robot.pneumatics.fingerOpen();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("timer has passed" + timer.hasPeriodPassed(0.2));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !Robot.carriage.isHatchCollectorExtended || timer.hasPeriodPassed(0.2);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (Robot.carriage.isHatchCollectorExtended) {
      Robot.pneumatics.hatchCollectorArmRetract();
    } else {
      Robot.pneumatics.hatchCollectorArmExtend();
    }
    timer.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
