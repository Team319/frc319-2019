/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.elevatorCommands;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickElevator extends Command {

  private int bbaPositionIncrement = 100;
  private int elevatorPositionIncrement = 200;
  private double elevatorBbaClimbCorrelation = 12;

  public JoystickElevator() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.mode == RobotMode.Climb) {
      bbaPositionIncrement = 50;
      double signal = Robot.oi.operatorController.rightStick.getY();
      double bbaIncrement = -signal * bbaPositionIncrement;
      Robot.bbarm.incrementTargetPosition((int) (bbaIncrement));
      Robot.bbarm.motionMagicControl();
      Robot.elevator.incrementTargetPosition((int) (elevatorBbaClimbCorrelation * bbaIncrement));
      Robot.elevator.motionMagicControl();
    } else {
      double signal = -Robot.oi.operatorController.leftStick.getY();
      Robot.elevator.incrementTargetPosition((int) (signal * elevatorPositionIncrement));
      Robot.elevator.motionMagicControl();
    }

    /*
     * if (Robot.mode == RobotMode.Climb) {
     * Robot.bbarm.incrementTargetPosition((int) (signal * bbaPositionIncrement));
     * Robot.bbarm.motionMagicControl(); }
     */
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
