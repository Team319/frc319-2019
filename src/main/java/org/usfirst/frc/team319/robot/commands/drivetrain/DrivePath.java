/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team319.lib.control.Path;
import org.usfirst.frc.team319.lib.control.PathContainer;
import org.usfirst.frc.team319.models.DriveSignal;
import org.usfirst.frc.team319.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivePath extends Command {

  private PathContainer pathContainer;
  private Path path;
  private boolean stopWhenDone;

  public DrivePath(PathContainer p, boolean stopWhenDone) {
    this.pathContainer = p;
    this.path = pathContainer.buildPath();
    this.stopWhenDone = stopWhenDone;
    requires(Robot.drivetrain);
  }

  public DrivePath(PathContainer p) {
    this(p, false);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.setWantDrivePath(this.path, this.pathContainer.isReversed());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (stopWhenDone) {
      Robot.drivetrain.drive(ControlMode.Velocity, new DriveSignal(0, 0));
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
