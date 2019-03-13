
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
public class LimelightDrive extends Command {

	BobDriveHelper helper;
	public double rotateSetPoint = 0;
	double moveValue = -Robot.oi.driverController.leftStick.getY();
	double rotateValue = Robot.limelight.getX();

	public LimelightDrive(double rotateSetPoint) {
		requires(Robot.drivetrain);
		helper = new BobDriveHelper();

		this.rotateSetPoint = rotateSetPoint;

	}

	protected void initialize() {
		Robot.limelight.setRotationSetpoints(rotateSetPoint);
		Robot.limelight.execute();
	}

	protected void execute() {

		SmartDashboard.putNumber("Rotate Value", rotateValue);

		DriveSignal driveSignal = helper.cheesyDrive(moveValue, rotateValue, true, false);
		Robot.drivetrain.drive(ControlMode.PercentOutput, driveSignal);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}