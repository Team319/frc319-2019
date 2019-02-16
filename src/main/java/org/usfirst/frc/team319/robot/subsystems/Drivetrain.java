package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.team319.follower.FollowsArc;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.DriveSignal;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;
import org.usfirst.frc.team319.models.SRXGains;
import org.usfirst.frc.team319.robot.commands.drivetrain_Commands.BobDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
/**
 *
 */

public class Drivetrain extends Subsystem implements FollowsArc {

	public static int DRIVE_PROFILE = 0;
	public static int ROTATION_PROFILE = 1;

	private SRXGains driveGains = new SRXGains(DRIVE_PROFILE, 0.015122, 0.00015122, 0.15, 0.3154, 0);
	//private SRXGains rotationGains = new SRXGains(ROTATION_PROFILE, 0.015122, 0.00015122, 0.15, 0.3154, 0);
	private SRXGains rotationGains = new SRXGains(ROTATION_PROFILE, 0.0, 0.0, 0.0, 0.0, 0);


	private BobTalonSRX rightFollowerWithPigeon = new BobTalonSRX(4);

	public LeaderBobTalonSRX leftLead = new LeaderBobTalonSRX(11, new BobTalonSRX(12), new BobTalonSRX(13));
	public LeaderBobTalonSRX rightLead = new LeaderBobTalonSRX(3, rightFollowerWithPigeon, new BobTalonSRX(5));

	private PigeonIMU pigeon = new PigeonIMU(rightFollowerWithPigeon);

	public Drivetrain() {

		setupSensors();
		setNeutralMode(NeutralMode.Coast);
		
		configGains(driveGains);
		configGains(rotationGains);

		leftLead.setInverted(true);
		leftLead.setSensorPhase(false);
		rightLead.setInverted(false);
		rightLead.setSensorPhase(false);

		/*
		leftLead.configPrimaryFeedbackDevice(FeedbackDevice.CTRE_MagEncoder_Relative);
		leftLead.setSensorPhase(false);

		
		rightLead.configPrimaryFeedbackDevice(FeedbackDevice.CTRE_MagEncoder_Relative);

		leftLead.enableCurrentLimit(false);
		leftLead.configContinuousCurrentLimit(60);
		rightLead.enableCurrentLimit(false);
		rightLead.configContinuousCurrentLimit(60);

		leftLead.configOpenloopRamp(0.125);
		rightLead.configOpenloopRamp(0.125);

		


		// configure distance sensor
		// Remote 0 will be the other side's Talon
		rightLead.configRemoteSensor0(leftLead.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor);
		rightLead.configSensorSum(FeedbackDevice.RemoteSensor0, FeedbackDevice.CTRE_MagEncoder_Relative);
		rightLead.configPrimaryFeedbackDevice(FeedbackDevice.SensorSum, 0.5);

		//I is limited to a certain amount
		rightLead.configMaxIntegralAccumulator(ROTATION_PROFILE, 3000);

		// configure angle sensor
		// Remote 1 will be a pigeon

		rightLead.configRemoteSensor1(leftLead.getDeviceID(), RemoteSensorSource.GadgeteerPigeon_Yaw);
		rightLead.configSecondaryFeedbackDevice(FeedbackDevice.RemoteSensor1, (0.0 / 0.0)); // Coefficient for
																							// Pigeon to
		leftLead.setSensorPhase(false);
		rightLead.setInverted(false);
		rightLead.setSensorPhase(false);

	
		// Add a coefficient for Pigeon to convert to 360
		rightLead.configRemoteSensor1(rightFollowerWithPigeon.getDeviceID(), RemoteSensorSource.GadgeteerPigeon_Yaw);
		rightLead.configSecondaryFeedbackDevice(FeedbackDevice.RemoteSensor0, (3600.0 / 8192.0)); 

		leftLead.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 0);
		rightLead.configAuxPIDPolarity(false, 0);
		*/
	}

	public void setupSensors(){
		leftLead.configPrimaryFeedbackDevice(FeedbackDevice.CTRE_MagEncoder_Relative);
		leftLead.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 0);

		rightLead.configRemoteSensor0(leftLead.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor);
		rightLead.configRemoteSensor1(rightFollowerWithPigeon.getDeviceID(), RemoteSensorSource.GadgeteerPigeon_Yaw);

		rightLead.configSensorSum(FeedbackDevice.RemoteSensor0, FeedbackDevice.CTRE_MagEncoder_Relative);
		rightLead.configPrimaryFeedbackDevice(FeedbackDevice.SensorSum, 0.5);

		rightLead.configPrimaryFeedbackDevice(FeedbackDevice.RemoteSensor0, (3600.0 / 8192.0));

	}

	public void initDefaultCommand() {
		setDefaultCommand(new BobDrive());
	}

	public void configGains(SRXGains gains) {
		this.leftLead.setGains(gains);
		this.rightLead.setGains(gains);
	}

	public void drive(ControlMode controlMode, double left, double right) {
		this.leftLead.set(controlMode, left);
		this.rightLead.set(controlMode, right);
	}

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

	public TalonSRX getLeftLeadTalon() {
		return this.getLeftLeadTalon();
	}

	public TalonSRX getRightLeadTalon() {
		return this.rightLead;
	}

	public void setNeutralMode(NeutralMode neutralMode) {
		this.leftLead.setNeutralMode(neutralMode);
		this.rightLead.setNeutralMode(neutralMode);
	}

	public double getAngle() {
		double[] ypr = new double[3];
		pigeon.getYawPitchRoll(ypr);
		return ypr[0];
	}
  

	public void resetPigeon() {
		this.pigeon.setYaw(0.0, 0);
		// Yaw is rotation of robot during autos
	}

	public double getRightDistance() {
		return rightLead.getPrimarySensorPosition();
	}

	public double getLeftDistance() {
		return leftLead.getPrimarySensorPosition();
	}

	public double getVelocity() {
		// double Velocity = rightLead.getPrimarySensorVelocity();
		// System.out.println("Velocity: " + Velocity);
		return (rightLead.getPrimarySensorVelocity() + leftLead.getPrimarySensorVelocity()) / 2;
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Right Distance", getRightDistance());
		SmartDashboard.putNumber("Left Distance", getLeftDistance());
		SmartDashboard.putNumber("Velocity:", getVelocity());
		SmartDashboard.putNumber("Distance", getDistance());
	}

	@Override
	public double getDistance() {
		return (leftLead.getPrimarySensorPosition() + rightLead.getPrimarySensorPosition()) / 2;
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
