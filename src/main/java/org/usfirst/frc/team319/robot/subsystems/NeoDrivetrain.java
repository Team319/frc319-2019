package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team319.models.BobSparkMax;
import org.usfirst.frc.team319.models.DriveMode;
import org.usfirst.frc.team319.models.DriveSignal;
import org.usfirst.frc.team319.models.IBobSmartMotorController;
import org.usfirst.frc.team319.models.LeaderBobSmartMotorController;
import org.usfirst.frc.team319.robot.commands.drivetrain.BobDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class NeoDrivetrain extends Subsystem {

	private IBobSmartMotorController left0 = new BobSparkMax(11, MotorType.kBrushless);
	private IBobSmartMotorController left1 = new BobSparkMax(12, MotorType.kBrushless);
	private IBobSmartMotorController left2 = new BobSparkMax(13, MotorType.kBrushless);
	private LeaderBobSmartMotorController leftLead = new LeaderBobSmartMotorController(left0, left1, left2);

	private IBobSmartMotorController right0 = new BobSparkMax(3, MotorType.kBrushless);
	private IBobSmartMotorController right1 = new BobSparkMax(4, MotorType.kBrushless);
	private IBobSmartMotorController right2 = new BobSparkMax(5, MotorType.kBrushless);
	private LeaderBobSmartMotorController rightLead = new LeaderBobSmartMotorController(right0, right1, right2);

	public DriveMode mode = DriveMode.Normal;

	public NeoDrivetrain() {
		leftLead.resetToFactoryDefaults();
		rightLead.resetToFactoryDefaults();

		setupSensors();
		setNeutralMode(NeutralMode.Coast);

		leftLead.setInverted(false);
		rightLead.setInverted(true);

	}

	public void setupSensors() {

	}

	public void initDefaultCommand() {
		setDefaultCommand(new BobDrive());
	}

	public void drive(double left, double right) {
		this.leftLead.setPercentOutput(left);
		this.rightLead.setPercentOutput(right);
	}

	public void drive(DriveSignal driveSignal) {
		this.drive(driveSignal.getLeft(), driveSignal.getRight());
	}

	public double getLeftDriveLeadDistance() {
		return this.leftLead.getPosition();
	}

	public double getRightDriveLeadDistance() {
		return this.rightLead.getPosition();
	}

	public double getLeftDriveLeadVelocity() {
		return this.leftLead.getVelocity();
	}

	public double getRightDriveLeadVelocity() {
		return this.rightLead.getVelocity();
	}

	public void setDrivetrainPositionToZero() {
		this.leftLead.setPosition(0);
		this.rightLead.setPosition(0);
	}

	public double getLeftLeadVoltage() {
		return this.leftLead.getOutputVoltage();
	}

	public double getRightLeadVoltage() {
		return this.rightLead.getOutputVoltage();
	}

	public void setNeutralMode(NeutralMode neutralMode) {
		boolean isBrakeMode = neutralMode == NeutralMode.Brake ? true : false;
		this.leftLead.setBrakeMode(isBrakeMode);
		this.rightLead.setBrakeMode(isBrakeMode);
	}

	public double getRightDistance() {
		return getRightDriveLeadDistance();
	}

	public double getLeftDistance() {
		return getLeftDriveLeadDistance();
	}

	public double getVelocity() {
		return (getLeftDriveLeadVelocity() + getRightDriveLeadVelocity()) / 2;
	}

	@Override
	public void periodic() {
		// SmartDashboard.putNumber("Velocity", this.getVelocity());
		// SmartDashboard.putNumber("Distance Right", this.getRightDriveLeadDistance());
		// SmartDashboard.putNumber("Distance Left", this.getLeftDriveLeadDistance());
		// SmartDashboard.putNumber("Angle", this.getAngle());
	}

	public double getDistance() {
		return (getRightDistance() + getLeftDistance()) / 2;
	}
}
