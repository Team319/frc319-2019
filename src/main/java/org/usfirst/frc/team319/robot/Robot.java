/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.robot.commands.robot.RevertClimbMode;
import org.usfirst.frc.team319.robot.commands.robot.SetRobotMode;
import org.usfirst.frc.team319.robot.subsystems.BBArm;
import org.usfirst.frc.team319.robot.subsystems.Carriage;
import org.usfirst.frc.team319.robot.subsystems.Drivetrain;
import org.usfirst.frc.team319.robot.subsystems.Elevator;
import org.usfirst.frc.team319.robot.subsystems.Limelight;
import org.usfirst.frc.team319.robot.subsystems.Pneumatics;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends TimedRobot {

	Command autonomousCommand;
	public static final BBArm bbarm = new BBArm();
	public static final Elevator elevator = new Elevator();
	public static final Carriage carriage = new Carriage();
	public static final Limelight limelight = new Limelight();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Drivetrain drivetrain = new Drivetrain();

	public static OI oi;
	public static RobotMode mode = RobotMode.Normal;

	// SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		oi = new OI();
		limelight.setStreamType();// sets secondary as the main camera feed.
		Robot.drivetrain.setDrivetrainPositionToZero();
		SmartDashboard.putData("Climb Mode", new SetRobotMode(RobotMode.Climb));
		SmartDashboard.putData("Normal Mode", new SetRobotMode(RobotMode.Normal));
		SmartDashboard.putData("EXIT CLIMB MODE", new RevertClimbMode());
		SmartDashboard.putNumber("LimeightX", limelight.circularBufferX());

		// CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		limelight.setStreamType();// sets secondary as the main camera feed.
		limelight.setLedModeOff();
		Robot.elevator.forceSetTargetPosition(Robot.elevator.getCurrentPosition());
		Robot.bbarm.forceSetTargetPosition(Robot.bbarm.getCurrentPosition());
	}

	@Override
	public void autonomousInit() {
		limelight.setStreamType();// sets secondary as the main camera feed.
		/*
		 * autonomousCommand = new DrivetrainDoNothing();
		 * 
		 * // schedule the autonomous command (example) if (autonomousCommand != null) {
		 * autonomousCommand.start(); }
		 */
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		limelight.setStreamType();// sets secondary as the main camera feed.

	}

	@Override
	public void teleopInit() {

		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		limelight.setStreamType();// sets secondary as the main camera feed.
		Robot.elevator.forceSetTargetPosition(Robot.elevator.getCurrentPosition());
		Robot.bbarm.forceSetTargetPosition(Robot.bbarm.getCurrentPosition());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		limelight.setStreamType();// sets secondary as the main camera feed.

	}

	@Override
	public void testPeriodic() {
	}

}
