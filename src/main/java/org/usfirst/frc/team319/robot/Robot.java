/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.robot.commands.autonomous_paths.ExampleAutoCommandGroup;
import org.usfirst.frc.team319.robot.subsystems.Drivetrain;
import org.usfirst.frc.team319.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	SendableChooser<String> autoChooser;

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static OI oi;

	// SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {

		oi = new OI();
		Robot.drivetrain.setDrivetrainPositionToZero();

		autoChooser = new SendableChooser<String>();
		autoChooser.addDefault("Example Auto", "Example Auto");
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
	//	SmartDashboard.putData("CrossTheLine", new FollowArc(new ()));


	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Driver Left Trigger", Robot.oi.driverController.triggers.getLeft());
		SmartDashboard.putNumber("Operator Left Stick Y", Robot.oi.operatorController.leftStick.getY());

	}

	@Override
	public void autonomousInit() {

		// SmartDashboard.putData("Auto mode", m_chooser);
		String selectedAuto = (String) autoChooser.getSelected();
		System.out.println(selectedAuto);
		switch (selectedAuto) {
		case "ExampleAuto":
			autonomousCommand = new ExampleAutoCommandGroup();
			break;
		}

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();


	}

	@Override
	public void testPeriodic() {
	}
}
