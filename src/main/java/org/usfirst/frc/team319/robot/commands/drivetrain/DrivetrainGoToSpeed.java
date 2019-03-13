package org.usfirst.frc.team319.robot.commands.drivetrain;

import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainGoToSpeed extends Command {

	public DrivetrainGoToSpeed() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		Robot.drivetrain.leftLead.selectProfileSlot(Drivetrain.DRIVE_PROFILE, 0);
		Robot.drivetrain.rightLead.selectProfileSlot(Drivetrain.DRIVE_PROFILE, 0);
	}

	protected void execute() {
		Robot.drivetrain.drive(ControlMode.Velocity, 3244, 3244);
		System.out.println("Left Error: " + Robot.drivetrain.getLeftClosedLoopError() + "Right Error: "
				+ Robot.drivetrain.getRightClosedLoopError());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
