package org.usfirst.frc.team319.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team319.models.DriveMode;
import org.usfirst.frc.team319.models.DriveSignal;
import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.utils.BobDriveHelper;

import org.usfirst.frc.team319.robot.commands.limelight.RotateToTarget;
import org.usfirst.frc.team319.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

		Robot.limelight.setLedModeOn();

		double rotateCap = 0.5;

		//
		double distanceScale = 1;
		/*
		 * double limelightScale = 0.0319; double loadingStationOffset = 0;
		 */

		// if A button is held
		if (Robot.drivetrain.mode == DriveMode.Limelight) {
			Robot.limelight.setLedModeOn();
			rotateValue = Robot.limelight.trackRotate();
			// Linear function to calculate the offset value of our vision target.
			// offset = -13 + (0.4 - 1.6*Area)
			// Area is the area of the target rectangle with the respect to the image size.
			// the area far away of the target will be small and ~0.4
			// the area close up of the target will be ~4.0
			/*
			 * double area = 0.4; if (Robot.limelight.getArea() >= 4) { area = 4; } else if
			 * (Robot.limelight.getArea() <= 0.4) { area = 0.4; }
			 */
			// double offset = (-13 + (0.4 - 1.6 * area));
			double target = 0;
			Robot.limelight.setRotationSetpoints(target);
			Robot.limelight.execute();

			if (rotateValue > rotateCap) {
				rotateValue = rotateCap;
			} else if (rotateValue < -rotateCap) {
				rotateValue = -rotateCap;
			} else {
				rotateValue = 0;
			} //
				// rotateValue = Robot.limelight.getX() / (Robot.limelight.getFovX() / 2);

		} else {
			Robot.limelight.setLedModeOff(); // turns limelight LED off
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
