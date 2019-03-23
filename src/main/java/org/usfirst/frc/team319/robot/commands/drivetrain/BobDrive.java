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
		System.out.println("Drive Mode: " + Robot.drivetrain.mode);
		double rotateValue = 0;

		Robot.limelight.setLedModeOn();

		double rotateCap = 0.35;

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
			double area = 0.4;
			if (Robot.limelight.getArea() >= 4) {
				area = 4;
			} else if (Robot.limelight.getArea() <= 0.4) {
				area = 0.4;
			}
			double offset = (-13 + (0.4 - 1.6 * area));
			Robot.limelight.setRotationSetpoints(offset);
			Robot.limelight.execute();
			/*
			 * // 1 - Off // 2 - Blink // 3 - On Robot.limelight.setLedModeOn(); // turns
			 * limelight LED on
			 * 
			 * // Take the average X of the past 5 values and computer //
			 * loadingStationOffset is the value away from the crosshair, if the offset is
			 * // calibrated, this should be 0. // scale is the value of intensity the scale
			 * should be rotated. rotateValue = (Robot.limelight.circularBufferX() -
			 * loadingStationOffset) * limelightScale;
			 * 
			 * SmartDashboard.putNumber("X value from limelight: ", rotateValue);
			 * 
			 * // RotateCap is the threshold and the only speed that will be rotated
			 */
			if (rotateValue > rotateCap) {
				rotateValue = rotateCap;
			} else if (rotateValue < -rotateCap) {
				rotateValue = -rotateCap;
			} else {
				rotateValue = 0;
			} //
				// rotateValue = Robot.limelight.getX() / (Robot.limelight.getFovX() / 2);

		} else {
			// Robot.limelight.setLedModeOff(); // turns limelight LED off
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
