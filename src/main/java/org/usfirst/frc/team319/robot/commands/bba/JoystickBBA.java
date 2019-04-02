/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.bba;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickBBA extends Command {

  private int positionIncrement = 100;

  public JoystickBBA() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.bbarm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.mode == RobotMode.Climb) {
      double driveSignal = -Robot.oi.driverController.leftStick.getY();
      Robot.bbarm.collectorTalon.set(ControlMode.PercentOutput, driveSignal);
    }

    double signal = Robot.oi.operatorController.rightStick.getY();
    int increment = (int) (-signal * positionIncrement);

    if (Robot.oi.operatorController.Dpad.Down.get()) {
      Robot.bbarm.forceIncrementTargetPosition(increment);
    } else {
      Robot.bbarm.incrementTargetPosition(increment);
    }
    Robot.bbarm.motionMagicControl();
    // Robot.bbarm.percentVbus(signal);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
  }
}
