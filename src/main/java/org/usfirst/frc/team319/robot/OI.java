/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.controllers.BobXboxController;
import org.usfirst.frc.team319.models.DriveMode;
import org.usfirst.frc.team319.robot.commands.bba.BbaConfigSoftLimits;
import org.usfirst.frc.team319.robot.commands.bba.ResetBbaPosition;
import org.usfirst.frc.team319.robot.commands.carriage.CollectorSetSpeed;
import org.usfirst.frc.team319.robot.commands.carriage.PassthroughSpit;
import org.usfirst.frc.team319.robot.commands.hatchCollector.HatchCollectorCollect;
import org.usfirst.frc.team319.robot.commands.hatchCollector.HatchCollectorPlace;
import org.usfirst.frc.team319.robot.commands.robot.CollectCargo;
import org.usfirst.frc.team319.robot.commands.robot.GoToCargoShipPose;
import org.usfirst.frc.team319.robot.commands.robot.GoToCollectPose;
import org.usfirst.frc.team319.robot.commands.robot.GoToHighCargoPose;
import org.usfirst.frc.team319.robot.commands.robot.GoToLowCargoPose;
import org.usfirst.frc.team319.robot.commands.robot.GoToMiddleCargoPose;
import org.usfirst.frc.team319.robot.commands.robot.GoToSafePose;
import org.usfirst.frc.team319.robot.commands.robot.SetDriveMode;
import org.usfirst.frc.team319.robot.commands.robot.SpitCargo;
import org.usfirst.frc.team319.robot.commands.robot.StartClimbLevelTwoMode;
import org.usfirst.frc.team319.robot.commands.robot.StartClimbMode;

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

		// ----Driver Controller---- \\

		driverController.rightTriggerButton.whileHeld(new PassthroughSpit());
		driverController.leftTriggerButton.whileHeld(new PassthroughSpit());

		driverController.leftBumper.whenPressed(new HatchCollectorCollect());
		driverController.rightBumper.whenPressed(new HatchCollectorPlace());

		driverController.aButton.whenPressed(new SetDriveMode(DriveMode.Limelight));
		driverController.aButton.whenReleased(new SetDriveMode(DriveMode.Normal));

		driverController.yButton.whileHeld(new CollectorSetSpeed(1.0));
		driverController.yButton.whenReleased(new CollectorSetSpeed(0.0));

		// ----Operator Controller---- \\

		operatorController.aButton.whenPressed(new GoToLowCargoPose());
		operatorController.bButton.whenPressed(new GoToMiddleCargoPose());
		operatorController.yButton.whenPressed(new GoToHighCargoPose());
		operatorController.xButton.whenPressed(new GoToCargoShipPose());

		operatorController.startButton.whenPressed(new StartClimbMode());
		operatorController.selectButton.whenPressed(new StartClimbLevelTwoMode());

		operatorController.leftBumper.whenPressed(new SpitCargo());
		operatorController.rightBumper.whenPressed(new CollectCargo());

		operatorController.leftTriggerButton.whenPressed(new GoToSafePose());
		operatorController.rightTriggerButton.whenPressed(new GoToCollectPose());

		operatorController.Dpad.Down.whenPressed(new BbaConfigSoftLimits(false));
		operatorController.Dpad.Down.whenReleased(new ResetBbaPosition());

		/*
		 * operatorController.rightTriggerButton.whenPressed(new
		 * PassThroughCollectCargo());
		 */

	}
}
