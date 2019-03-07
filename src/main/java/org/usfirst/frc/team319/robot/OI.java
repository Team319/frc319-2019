/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.controllers.BobXboxController;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.CollectorSetSpeed;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PassthroughSpit;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PlatypusFaceExtend;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PlatypusFaceRetract;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorCollect;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorPlace;
import org.usfirst.frc.team319.robot.commands.robotCommands.CollectCargo;
import org.usfirst.frc.team319.robot.commands.robotCommands.GoToCargoShipPose;
import org.usfirst.frc.team319.robot.commands.robotCommands.GoToCollectPose;
import org.usfirst.frc.team319.robot.commands.robotCommands.GoToHighCargoPose;
import org.usfirst.frc.team319.robot.commands.robotCommands.GoToLowCargoPose;
import org.usfirst.frc.team319.robot.commands.robotCommands.GoToMiddleCargoPose;
import org.usfirst.frc.team319.robot.commands.robotCommands.GoToSafePose;
import org.usfirst.frc.team319.robot.commands.robotCommands.SpitCargo;
import org.usfirst.frc.team319.robot.commands.robotCommands.StartClimbMode;

public class OI {
	public BobXboxController driverController;
	public BobXboxController operatorController;

	public OI() {
		driverController = new BobXboxController(0, 0.10, 0.10);
		operatorController = new BobXboxController(1, 0.10, 0.1);

		// ----Driver Controller---- \

		driverController.rightTriggerButton.whileHeld(new PassthroughSpit());
		driverController.leftTriggerButton.whileHeld(new PassthroughSpit());

		driverController.leftBumper.whenPressed(new HatchCollectorCollect());
		driverController.rightBumper.whenPressed(new HatchCollectorPlace());

		driverController.aButton.whenPressed(new PlatypusFaceExtend());
		driverController.bButton.whenPressed(new PlatypusFaceRetract());

		driverController.yButton.whileHeld(new CollectorSetSpeed(1.0));
		driverController.yButton.whenReleased(new CollectorSetSpeed(0.0));

		// ----Operator Controller---- \\
		/*
		 * operatorController.aButton.whenPressed(new ElevatorGoToLowCargoPosition());
		 * operatorController.bButton.whenPressed(new
		 * ElevatorGoToMiddleCargoPosition());
		 * operatorController.yButton.whenPressed(new ElevatorGoToHighCargoPosition());
		 * operatorController.xButton.whenPressed(new ElevatorGoToCargoShipPosition());
		 */

		operatorController.aButton.whenPressed(new GoToLowCargoPose());
		operatorController.bButton.whenPressed(new GoToMiddleCargoPose());
		operatorController.yButton.whenPressed(new GoToHighCargoPose());
		operatorController.xButton.whenPressed(new GoToCargoShipPose());

		operatorController.startButton.whenPressed(new StartClimbMode());

		operatorController.leftBumper.whenPressed(new SpitCargo());
		operatorController.rightBumper.whenPressed(new CollectCargo());

		operatorController.leftTriggerButton.whenPressed(new GoToSafePose());
		operatorController.rightTriggerButton.whenPressed(new GoToCollectPose());
		// operatorController.rightTriggerButton.whenPressed(new
		// PassThroughCollectCargo());

	}
}
