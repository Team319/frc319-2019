/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.controllers.BobXboxController;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PassthroughSpitBack;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PassthroughSpitFront;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BbaGoToCarriageSafePosition;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.CollectCargoCommandGroup;
import org.usfirst.frc.team319.robot.commands.Finger_Commands.FingerCollect;
import org.usfirst.frc.team319.robot.commands.Finger_Commands.FingerPlace;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToHighCargoPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToLowCargoPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToMiddleCargoPosition;
import org.usfirst.frc.team319.robot.commands.drivetrain_Commands.Climb;

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

<<<<<<< dc39c70e838f880dc9f9e4073de3ad9572532c33
		driverController.leftTriggerButton.configureThreshold(0.075);
		driverController.aButton.whenPressed(new DriveTrainDriveThreeFeet());

		operatorController.leftTriggerButton.whileHeld(new ManualCollect());
		operatorController.bButton.whenPressed(new BBAGoHome());
		operatorController.yButton.whenPressed(new BbaGoToCargoCollect());
		operatorController.xButton.whenPressed(new BbaGoToCarriageSafePosition());
		operatorController.aButton.whenPressed(new CollectCargoCommandGroup());
		operatorController.leftTriggerButton.whileHeld(new ManualCollect());
=======
		driverController.rightTriggerButton.whileHeld(new PassthroughSpitFront());
		driverController.leftTriggerButton.whileHeld(new PassthroughSpitBack());
		driverController.rightBumper.whenPressed(new FingerPlace());
		driverController.leftBumper.whenPressed(new FingerCollect());

		// ----Operator Setup----//
		operatorController = new BobXboxController(1, 0.10, 0.10);
		// ----Operator Buttons----//

		operatorController.Dpad.Down.whenPressed(new Climb());
		operatorController.aButton.whenPressed(new ElevatorGoToLowCargoPosition());
		operatorController.bButton.whenPressed(new ElevatorGoToMiddleCargoPosition());
		operatorController.yButton.whenPressed(new ElevatorGoToHighCargoPosition());
		operatorController.leftTriggerButton.whenPressed(new BbaGoToCarriageSafePosition());
		operatorController.rightTriggerButton.whenPressed(new CollectCargoCommandGroup());
<<<<<<< 8ecd4edac79e5e74e5231e60dbf790be9075ad91

		// operatorController.rightTriggerButton.whileHeld(new ManualTunnelIntake());

		// ----Operator Buttons----//
		/*
		 * operatorController.bButton.whenPressed(new BBAGoHome());
		 * operatorController.yButton.whenPressed(new BbaGoToCargoCollect());
		 * operatorController.xButton.whenPressed(new BbaGoToCarriageSafePosition());
		 * operatorController.aButton.whenPressed(new CollectCargoCommandGroup());
		 * operatorController.rightTriggerButton.whileHeld(new ManualTunnelIntake());
		 */

>>>>>>> Contol mapping and Nessasary command addition
=======
>>>>>>> Addition of nessisary commands, incuding collect pose and tunnel eject commands
	}
}
