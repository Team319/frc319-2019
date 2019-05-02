package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team319.follower.FollowsArc;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.DriveMode;
import org.usfirst.frc.team319.models.DriveSignal;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;
import org.usfirst.frc.team319.models.SRXGains;
import org.usfirst.frc.team319.robot.commands.drivetrain.BobDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

//This class is related to controlling the Driverain
public class Drivetrain extends Subsystem implements FollowsArc {

	// PID settings
	public static int DRIVE_PROFILE = 0;
	public static int ROTATION_PROFILE = 1;

	private SRXGains driveGains = new SRXGains(DRIVE_PROFILE, 0.0, 0.0, 0.0, 0.3154, 0);
	private SRXGains rotationGains = new SRXGains(ROTATION_PROFILE, 0.0, 0.0, 0.0, 0.0, 0);

	// Sets Talon IDs
	public LeaderBobTalonSRX leftLead = new LeaderBobTalonSRX(14, new BobTalonSRX(12), new BobTalonSRX(13));
	public LeaderBobTalonSRX rightLead = new LeaderBobTalonSRX(3, new BobTalonSRX(4), new BobTalonSRX(2));

	// Is the drivetrain in climb mode?

	public DriveMode mode = DriveMode.Normal;

	public Drivetrain() {
		leftLead.configFactoryDefault();
		rightLead.configFactoryDefault();

		setNeutralMode(NeutralMode.Coast);

		configGains(driveGains);
		configGains(rotationGains);

		leftLead.setInverted(false);
		leftLead.setSensorPhase(true);
		rightLead.setInverted(true);
		rightLead.setSensorPhase(true);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new BobDrive());
	}

	// Configure the gains
	public void configGains(SRXGains gains) {
		this.leftLead.setGains(gains);
		this.rightLead.setGains(gains);
		rightLead.configMaxIntegralAccumulator(ROTATION_PROFILE, 3000);
	}

	// THis is used in our drive code
	public void drive(ControlMode controlMode, double left, double right) {
		this.leftLead.set(controlMode, left);
		this.rightLead.set(controlMode, right);
	}

	// Also used for driving, sets control mode and
	public void drive(ControlMode controlMode, DriveSignal driveSignal) {
		this.drive(controlMode, driveSignal.getLeft(), driveSignal.getRight());
	}

	public double getLeftDriveLeadDistance() {
		return this.leftLead.getSelectedSensorPosition();
	}

	public double getRightDriveLeadDistance() {
		return this.rightLead.getSelectedSensorPosition();
	}

	public double getLeftDriveLeadVelocity() {
		return this.leftLead.getSelectedSensorVelocity();
	}

	public double getRightDriveLeadVelocity() {
		return this.rightLead.getSelectedSensorVelocity();
	}

	public void setDrivetrainPositionToZero() {
		this.leftLead.setSelectedSensorPosition(0);
		this.rightLead.setSelectedSensorPosition(0);
	}

	public double getLeftLeadVoltage() {
		return this.leftLead.getMotorOutputVoltage();
	}

	public double getRightLeadVoltage() {
		return this.rightLead.getMotorOutputVoltage();
	}

	public double getLeftClosedLoopError() {
		return this.leftLead.getClosedLoopError();
	}

	public double getRightClosedLoopError() {
		return this.rightLead.getClosedLoopError();
	}

	public void setNeutralMode(NeutralMode neutralMode) {
		this.leftLead.setNeutralMode(neutralMode);
		this.rightLead.setNeutralMode(neutralMode);
	}

	public double getRightDistance() {
		return rightLead.getPrimarySensorPosition();
	}

	public double getLeftDistance() {
		return leftLead.getPrimarySensorPosition();
	}

	public double getVelocity() {
		return rightLead.getPrimarySensorVelocity();
	}

	@Override
	public void periodic() {

	}

	@Override
	public double getDistance() {
		return rightLead.getPrimarySensorPosition();
	}

	@Override
	public TalonSRX getLeft() {
		return leftLead;
	}

	@Override
	public TalonSRX getRight() {
		return rightLead;
	}

	@Override
	public Subsystem getRequiredSubsystem() {
		return this;
	}
}
