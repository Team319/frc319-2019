/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.carriage;

import org.usfirst.frc.team319.robot.OI;

//import com.ctre.phoenix.CANifier.GeneralPin;

import org.usfirst.frc.team319.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WaitForCargo extends Command {
  private int detectionCounter = 0;
  private int detectionThreshold = 2;
  private double maxOperatorAdjust = 200;

  public WaitForCargo() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.bbarm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.detectionCounter = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double operatorAdjust = -Robot.oi.operatorController.rightStick.getY() * maxOperatorAdjust;
    int adjustedTarget = Robot.bbarm.getCargoCollectPosition() + (int) operatorAdjust;
    SmartDashboard.putNumber("BBA Target", adjustedTarget);
    Robot.bbarm.setTargetPosition(adjustedTarget);
    Robot.bbarm.motionMagicControl();

    if (Robot.carriage.ballDetected()) {
      detectionCounter++;
    } else {
      detectionCounter = 0;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return detectionCounter >= detectionThreshold;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.bbarm.percentVbusCollector(0);
    Robot.carriage.percentVbusPassthrough(0);

  }
}
