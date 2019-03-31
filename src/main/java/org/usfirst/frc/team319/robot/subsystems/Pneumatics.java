package org.usfirst.frc.team319.robot.subsystems;

import org.usfirst.frc.team319.models.RobotMode;
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

	DoubleSolenoid HatchCollectorSolenoid = new DoubleSolenoid(2, 3);
	DoubleSolenoid fingerSolenoid = new DoubleSolenoid(4, 5);
	DoubleSolenoid carriageAndElevatorLockSolenoid = new DoubleSolenoid(6, 7);

	public void initDefaultCommand() {
		setDefaultCommand(new CompressorRun());
	}

	public void compressorRun() {
		if (Robot.mode == RobotMode.Climb) {
			compressor.setClosedLoopControl(false);
			compressor.stop();
		} else {
			compressor.setClosedLoopControl(true);
		}
	}

	public void carriageAndElevatorFloorExtend() {
		this.carriageAndElevatorLockSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(true);
	}

	public void carriageAndElevatorFloorRetract() {
		this.carriageAndElevatorLockSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.elevator.setIsElevatorFloorSolenoidExtended(false);
	}

	public void fingerOpen() {
		this.fingerSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setIsFingerOpen(true);
	}

	public void fingerClose() {
		this.fingerSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setIsFingerOpen(false);
	}

	public void hatchCollectorArmExtend() {
		this.HatchCollectorSolenoid.set(DoubleSolenoid.Value.kForward);
		Robot.carriage.setisFrontHatchCollectorExtended(true);
	}

	public void hatchCollectorArmRetract() {
		this.HatchCollectorSolenoid.set(DoubleSolenoid.Value.kReverse);
		Robot.carriage.setisFrontHatchCollectorExtended(false);
	}

}
