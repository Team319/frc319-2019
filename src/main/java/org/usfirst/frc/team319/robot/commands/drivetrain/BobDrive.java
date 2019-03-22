package org.usfirst.frc.team319.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double rotateValue = 0;

		double rotateCap = 0.45;
		double limelightScale = 0.319;
		double loadingStationOffset = 0;

		if (Robot.drivetrain.mode == DriveMode.Limelight) {
			Robot.limelight.setLedMode(3); // turns limelight LED on

			rotateValue = (Robot.limelight.circularBufferX() - loadingStationOffset) * limelightScale;

			if (rotateValue > rotateCap) {
				rotateValue = rotateCap;
			} else if (rotateValue < -rotateCap) {
				rotateValue = -rotateCap;
			} else {
				rotateValue = 0;
			}

			// rotateValue = Robot.limelight.getX() / (Robot.limelight.getFovX() / 2);

		} else {
			Robot.limelight.setLedMode(1); // turns limelight LED off
			rotateValue = Robot.oi.driverController.rightStick.getX();
		}

		double moveValue = -Robot.oi.driverController.leftStick.getY();
		boolean quickTurn = (moveValue < quickTurnThreshold && moveValue > -quickTurnThreshold);
		DriveSignal driveSignal = helper.cheesyDrive(moveValue, rotateValue, quickTurn, false);
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
