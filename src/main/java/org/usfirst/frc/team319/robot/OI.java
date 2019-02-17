/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.controllers.BobXboxController;
import org.usfirst.frc.team319.robot.commands.drivetrain_Commands.DrivetrainGoToSpeed;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.ManualCollect;
import org.usfirst.frc.team319.robot.commands.autotune.AutoTuneVelocity;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BBAGoHome;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BBAGoToSpeed;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BbaGoToCargoCollect;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.BbaGoToCarriageSafePosition;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.ManualCollect;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public BobXboxController driverController;
	public BobXboxController operatorController;

	public OI() {

		//----Driver Setup----//

		driverController = new BobXboxController(0, 0.10, 0.10);

		//----Driver Buttons----//

		driverController.leftTriggerButton.configureThreshold(0.075);

	//	driverController.aButton.whenPressed(new DriveTrainDriveThreeFeet());
	//	driverController.xButton.whileHeld(new AutoTuneVelocity(Robot.bbarm, Robot.bbarm.bbaLead, 0, 750, 50));
		driverController.xButton.whileHeld(new BBAGoToSpeed());
		driverController.leftTriggerButton.whileHeld(new ManualCollect());


		//----Operator Setup----//

		operatorController = new BobXboxController(1, 0.10, 0.05);

		//----Operator Buttons----//
		
		operatorController.bButton.whenPressed(new BBAGoHome());
		operatorController.yButton.whenPressed(new BbaGoToCargoCollect());
		operatorController.xButton.whenPressed(new BbaGoToCarriageSafePosition());
		//operatorController.rightTriggerButton.whileHeld(new ManualTunnelIntake());

	}
}
