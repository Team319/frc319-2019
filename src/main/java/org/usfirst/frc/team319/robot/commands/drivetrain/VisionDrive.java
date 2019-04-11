
package org.usfirst.frc.team319.robot.commands.drivetrain;

import org.usfirst.frc.team319.models.DriveSignal;
import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.utils.BobDriveHelper;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionDrive extends Command {

  BobDriveHelper helper;
  public double driveSetPoint = 0;
  public double rotateSetPoint = 0;
  double moveValue = Robot.limelight.trackDrive();
  double rotateValue = Robot.limelight.trackRotate();

  public VisionDrive(double driveSetPoint, double rotateSetPoint) {
    requires(Robot.drivetrain);
    helper = new BobDriveHelper();
    this.driveSetPoint = driveSetPoint;
    this.rotateSetPoint = rotateSetPoint;

  }

  protected void initialize() {
    Robot.limelight.setSetpoints(driveSetPoint, rotateSetPoint);
    Robot.limelight.execute();
    Robot.limelight.setLedModeOn();
  }

  protected void execute() {

    SmartDashboard.putNumber("Rotate Value", rotateValue);
    SmartDashboard.putNumber("Move Value", moveValue);

    DriveSignal driveSignal = helper.cheesyDrive(moveValue, rotateValue, true, false);
    Robot.drivetrain.drive(ControlMode.PercentOutput, driveSignal);
  }

  protected boolean isFinished() {
    return Robot.limelight.getDistance() <= driveSetPoint + 0.5;
  }

  protected void end() {
    Robot.limelight.setLedModeOff();

  }

  protected void interrupted() {
    Robot.limelight.setLedModeOff();

  }
}