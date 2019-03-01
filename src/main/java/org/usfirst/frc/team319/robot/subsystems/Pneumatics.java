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

	DoubleSolenoid platypusFaceSolenoid = new DoubleSolenoid(0, 1);
	DoubleSolenoid frontHatchSolenoid = new DoubleSolenoid(2, 3);
	DoubleSolenoid fingerSolenoid = new DoubleSolenoid(4, 5);
	DoubleSolenoid carriageAndElevatorLockSolenoid = new DoubleSolenoid(6, 7);

	public void initDefaultCommand() {
		setDefaultCommand(new CompressorRun());
	}

	public void compressorRun() {
		compressor.setClosedLoopControl(true);
	}

	public void PlatypusFaceExtend() {
		this.platypusFaceSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsPlatypusFaceExtended(true);
	}

	public void PlatypusFaceRetract() {
		this.platypusFaceSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsPlatypusFaceExtended(false);
	}

	public void FingerOpen() {
		this.fingerSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsFingerOpen(true);
	}

	public void FingerClose() {
		this.fingerSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsFingerOpen(false);
	}

	public void carriageAndElevatorFloorExtend() {
		this.carriageAndElevatorLockSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(true);
	}

	public void carriageAndElevatorFloorRetract() {
		this.carriageAndElevatorLockSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(false);
	}

	public void hatchCollectorArmExtend() {
		this.frontHatchSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.bbarm.setIsFrontHatchSolenoidExtended(true);
	}

	public void hatchCollectorArmRetract() {
		this.frontHatchSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.bbarm.setIsFrontHatchSolenoidExtended(false);
	}

}
