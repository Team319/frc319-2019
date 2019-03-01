/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot;

import org.usfirst.frc.team319.controllers.BobXboxController;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PassthroughSpit;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PlatypusFaceExtend;
import org.usfirst.frc.team319.robot.commands.Carriage_Commands.PlatypusFaceRetract;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToCargoShipPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToHighCargoPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToLowCargoPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorGoToMiddleCargoPosition;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorLockCarriage;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.ElevatorUnlockCarriage;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorCollect;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorPlace;
import org.usfirst.frc.team319.robot.commands.robotCommands.CollectCargo;
import org.usfirst.frc.team319.robot.commands.robotCommands.GoToSafePose;
// import org.usfirst.frc.team319.robot.commands.autonomous_paths.DriveTrainDriveThreeFeet;
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

		// ----Operator Controller---- \\

		operatorController.aButton.whenPressed(new ElevatorGoToLowCargoPosition());
		operatorController.bButton.whenPressed(new ElevatorGoToMiddleCargoPosition());
		operatorController.yButton.whenPressed(new ElevatorGoToHighCargoPosition());
		operatorController.xButton.whenPressed(new ElevatorGoToCargoShipPosition());

		operatorController.startButton.whenPressed(new StartClimbMode());

		operatorController.leftBumper.whenPressed(new ElevatorLockCarriage());
		operatorController.rightBumper.whenPressed(new ElevatorUnlockCarriage());

		operatorController.leftTriggerButton.whenPressed(new GoToSafePose());
		operatorController.rightTriggerButton.whenPressed(new CollectCargo());
	}
}
