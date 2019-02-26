/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.controllers.BobXboxController;
//import org.usfirst.frc.team319.robot.commands.drivetrain_Commands.DrivetrainGoToSpeed;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.ManualCollect;
//import org.usfirst.frc.team319.robot.commands.autotune.AutoTuneVelocity;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BBAGoHome;
//import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BBAGoToSpeed;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BbaGoToCargoCollect;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BbaGoToCarriageSafePosition;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.CollectCargoCommandGroup;
//import org.usfirst.frc.team319.robot.commands.BBArm_Commands.ManualCollect;
//import org.usfirst.frc.team319.robot.commands.Carriage_Commands.ManualTunnelIntake;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToHomePosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToMiddleCargoPosition;
import org.usfirst.frc.team319.robot.commands.autonomous_paths.DriveTrainDriveThreeFeet;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public BobXboxController driverController;
	public BobXboxController operatorController;

	public OI() {
		driverController = new BobXboxController(0, 0.10, 0.10);
		operatorController = new BobXboxController(1, 0.10, 0.1);

		driverController.leftTriggerButton.configureThreshold(0.075);

		driverController.aButton.whenPressed(new DriveTrainDriveThreeFeet());

		operatorController.leftTriggerButton.whileHeld(new ManualCollect());
		operatorController.bButton.whenPressed(new BBAGoHome());
		operatorController.yButton.whenPressed(new BbaGoToCargoCollect());
		operatorController.xButton.whenPressed(new BbaGoToCarriageSafePosition());
		operatorController.aButton.whenPressed(new CollectCargoCommandGroup());
	}
}
