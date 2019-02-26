package org.usfirst.frc.team319.robot.subsystems;

import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.robot.commands.pneumatics.CompressorRun;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {

	Compressor compressor = new Compressor(0);

	// elevator, carriage, Finger, Hatch collector

	Solenoid elevatorFloorSolenoid = new Solenoid(0, 6);
	DoubleSolenoid PlatypusFaceSolenoid = new DoubleSolenoid(0, 0, 1);
	DoubleSolenoid FrontHatchSolenoid = new DoubleSolenoid(0, 2, 3);
	DoubleSolenoid FingerSolenoid = new DoubleSolenoid(0, 4, 5);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new CompressorRun());
	}

	public void compressorRun() {
		compressor.setClosedLoopControl(true);
	}

	public void PlatypusFaceExtend() {
		this.PlatypusFaceSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsPlatypusFaceExtended(true);
	}

	public void PlatypusFaceRetract() {
		this.PlatypusFaceSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsPlatypusFaceExtended(false);
	}

	public void FingerOpen() {
		this.FingerSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsFingerOpen(true);
	}

	public void FingerClose() {
		this.FingerSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsFingerOpen(false);
	}

	public void carriageExtend() {
		this.FrontHatchSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void carriageRetract() {
		this.FrontHatchSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void elevatorFloorExtend() {
		this.elevatorFloorSolenoid.set(true);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(true);
	}

	public void elevatorFloorRetract() {
		this.elevatorFloorSolenoid.set(false);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(false);
	}

	public void CarriageLockExtend() {
		this.FingerSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsCarriageLockSolenoidExtended(true);
	}

	public void CarriageLockRetract() {
		this.FingerSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsCarriageLockSolenoidExtended(false);
	}
	/*
	 * public void hatchCollectorArmExtend() {
	 * this.hatchCollectorArmSolenoid.set(DoubleSolenoid.Value.kForward);
	 * Robot.bbarm.setIsHatchCollectorArmSolenoidExtended(true); }
	 * 
	 * public void hatchCollectorArmRetract() {
	 * this.hatchCollectorArmSolenoid.set(DoubleSolenoid.Value.kForward);
	 * Robot.bbarm.setIsHatchCollectorArmSolenoidExtended(false); }
	 */
}
