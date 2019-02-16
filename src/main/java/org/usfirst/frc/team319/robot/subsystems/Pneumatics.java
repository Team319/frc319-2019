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

public boolean isHatchCollectorSolenoidExtended;

	// public boolean isCollectorSolenoidExtended;

	Compressor compressor = new Compressor(0);

	// elevator, carriage, beak, Hatch collector

	DoubleSolenoid beakSolenoid = new DoubleSolenoid(0, 1); 
	DoubleSolenoid carriageSolenoid = new DoubleSolenoid(2, 3);
	DoubleSolenoid elevatorSolenoid = new DoubleSolenoid(4, 5);
	DoubleSolenoid hatchCollectorSolenoid = new DoubleSolenoid(6, 7);
	DoubleSolenoid hatchCollectorArmSolenoid = new DoubleSolenoid(8, 9);

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

	public void elevatorExtend() {
		this.elevatorSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void elevatorRetract() {
		this.elevatorSolenoid.set(DoubleSolenoid.Value.kReverse);
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
