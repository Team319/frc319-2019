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

	// elevator, carriage, beak, Hatch collector

	Solenoid elevatorFloorSolenoid = new Solenoid(0, 0);

	DoubleSolenoid beakSolenoid = new DoubleSolenoid(1, 0, 1);
	DoubleSolenoid carriageSolenoid = new DoubleSolenoid(1, 2, 3);
	DoubleSolenoid hatchCollectorSolenoid = new DoubleSolenoid(1, 4, 5);
	DoubleSolenoid hatchCollectorArmSolenoid = new DoubleSolenoid(1, 6, 7);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new CompressorRun());
	}

	public void compressorRun() {
		compressor.setClosedLoopControl(true);
	}

	public void beakOpen() {
		this.beakSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsBeakOpen(true);
	}

	public void beakClose() {
		this.beakSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsBeakOpen(false);
	}

	public void carriageExtend() {
		this.carriageSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void carriageRetract() {
		this.carriageSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void elevatorFloorExtend() {
		this.elevatorFloorSolenoid.set(true);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(true);
	}

	public void hatchCollectorExtend() {
		this.hatchCollectorSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.bbarm.setIsHatchCollectorSolenoidExtended(true);
	}

	public void hatchCollectorRetract() {
		this.hatchCollectorSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.bbarm.setIsHatchCollectorSolenoidExtended(false);
	}

	public void hatchCollectorArmExtend() {
		this.hatchCollectorArmSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.bbarm.setIsHatchCollectorArmSolenoidExtended(true);
	}

	public void hatchCollectorArmRetract() {
		this.hatchCollectorArmSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.bbarm.setIsHatchCollectorArmSolenoidExtended(false);
	}
}
