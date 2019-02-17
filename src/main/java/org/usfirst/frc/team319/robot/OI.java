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

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public BobXboxController driverController;
	public BobXboxController operatorController;

	public OI() {

		driverController = new BobXboxController(0, 0.30, 0.05);

		driverController.leftTriggerButton.configureThreshold(0.075);

		driverController.xButton.whenPressed(new AutoTuneVelocity(Robot.bbarm, Robot.bbarm.bbaLead, 10, 1.0, 5));

		driverController.leftTriggerButton.whileHeld(new ManualCollect());


		//----Operator Setup----//

		operatorController = new BobXboxController(1, 0.30, 0.05);


		//----Operator Buttons----//

	}
}
