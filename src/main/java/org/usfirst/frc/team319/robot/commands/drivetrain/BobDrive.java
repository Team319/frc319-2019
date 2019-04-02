package org.usfirst.frc.team319.robot.commands.drivetrain;

import org.usfirst.frc.team319.models.DriveMode;
import org.usfirst.frc.team319.models.DriveSignal;
import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.utils.BobDriveHelper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BobDrive extends Command {

	BobDriveHelper helper;
	private double quickTurnThreshold = 0.2;

	public BobDrive() {
		requires(Robot.drivetrain);
		helper = new BobDriveHelper();
	}

	protected void initialize() {
	}

	protected void execute() {
		double rotateValue = 0;

		Robot.limelight.setLedModeOn();

		double rotateCap = 0.5;

		if (Robot.drivetrain.mode == DriveMode.Limelight) {
			Robot.limelight.setLedModeOn();
			rotateValue = Robot.limelight.trackRotate();

			double target = 0;
			Robot.limelight.setRotationSetpoints(target);
			Robot.limelight.execute();

			if (rotateValue > rotateCap) {
				rotateValue = rotateCap;
			} else if (rotateValue < -rotateCap) {
				rotateValue = -rotateCap;
			} else {
				rotateValue = 0;
			}
		} else {
			Robot.limelight.setLedModeOff();
			rotateValue = Robot.oi.driverController.rightStick.getX();
		}

		double moveValue = -Robot.oi.driverController.leftStick.getY();
		boolean quickTurn = (moveValue < quickTurnThreshold && moveValue > -quickTurnThreshold);
		DriveSignal driveSignal = helper.cheesyDrive(moveValue, rotateValue, quickTurn, false);
		Robot.drivetrain.drive(driveSignal);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
