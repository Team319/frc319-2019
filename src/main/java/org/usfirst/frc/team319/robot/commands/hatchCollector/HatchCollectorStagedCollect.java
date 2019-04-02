/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.hatchCollector;

import org.usfirst.frc.team319.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class HatchCollectorStagedCollect extends Command {
  Timer timer = new Timer();

  public HatchCollectorStagedCollect() {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.carriage);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
    if (Robot.carriage.isHatchCollectorExtended) {
      Robot.pneumatics.fingerOpen();
    } else {
      Robot.pneumatics.fingerClose();
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
