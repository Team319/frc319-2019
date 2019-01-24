/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.controllers.BobXboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public BobXboxController driverController;
	public BobXboxController operatorController;

	public OI() {

		driverController = new BobXboxController(0, 0.10, 0.08);

		driverController.leftTriggerButton.configureThreshold(0.075);
/*
		driverController.rightBumper.whenPressed(new ());
		driverController.leftBumper.whenPressed(new ());
		driverController.rightTriggerButton.whenPressed(new ());
		driverController.leftTriggerButton.whileHeld(new ());
		driverController.bButton.whenPressed(new ());
		driverController.startButton.whenPressed(new ());
		driverController.leftStickButton.whenPressed(new ());

	

		operatorController = new BobXboxController(1, 0.1, 0.125);
		
		operatorController.leftTriggerButton.whenPressed(new ());
		operatorController.rightTriggerButton.whenPressed(new ());
		operatorController.leftBumper.whenPressed(new ());
		operatorController.rightBumper.whenPressed(new ());
		operatorController.aButton.whenPressed(new ());
		operatorController.bButton.whenPressed(new ());
		operatorController.xButton.whenPressed(new (0.0));
		operatorController.yButton.whenPressed(new ());
		operatorController.startButton.whenPressed(new ());
		*/
		
		
		//operatorController.Dpad.Up.whenPressed(new ());
		

		
		
		// ---------------test buttons-------------------//

		
		//this.driverController.xButton.whenPressed(new FollowArc(new ()));


	}
}
