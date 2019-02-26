package org.usfirst.frc.team319.robot.subsystems;

import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.robot.commands.pneumatics.CompressorRun;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {

	Compressor compressor = new Compressor(0);

	// elevator, carriage, Finger, Hatch collector

	DoubleSolenoid platypusFaceSolenoid = new DoubleSolenoid(1, 0, 1);
	DoubleSolenoid frontHatchSolenoid = new DoubleSolenoid(1, 2, 3);
	DoubleSolenoid fingerSolenoid = new DoubleSolenoid(1, 4, 5);
	DoubleSolenoid carriageAndElevatorSolenoid = new DoubleSolenoid(1, 6, 7);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new CompressorRun());
	}

	public void compressorRun() {
		compressor.setClosedLoopControl(true);
	}

	public void platypusFaceExtend() {
		this.platypusFaceSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsPlatypusFaceExtended(true);
	}

	public void platypusFaceRetract() {
		this.platypusFaceSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsPlatypusFaceExtended(false);
	}

	public void fingerOpen() {
		this.fingerSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsFingerOpen(true);
	}

	public void fingerClose() {
		this.fingerSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsFingerOpen(false);
	}

	public void frontHatchExtend() {
		this.frontHatchSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setisFrontHatchCollectorExtended(true);
	}

	public void frontHatchRetract() {
		this.frontHatchSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setisFrontHatchCollectorExtended(false);
	}

	public void carriageAndElevatorExtend() {
		this.carriageAndElevatorSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(true);
	}

	public void carriageAndElevatorRetract() {
		this.carriageAndElevatorSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(false);
	}
}
