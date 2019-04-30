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

public class Drivetrain extends Subsystem implements FollowsArc {

	public static int DRIVE_PROFILE = 0;
	public static int ROTATION_PROFILE = 1;

	// private SRXGains driveGains = new SRXGains(DRIVE_PROFILE, 0.015122,
	// 0.00015122, 0.15, 0.3154, 0);
	private SRXGains driveGains = new SRXGains(DRIVE_PROFILE, 0.0, 0.0, 0.0, 0.3154, 0);
	// private SRXGains rotationGains = new SRXGains(ROTATION_PROFILE, 0.015122,
	// 0.00015122, 0.15, 0.3154, 0);
	private SRXGains rotationGains = new SRXGains(ROTATION_PROFILE, 0.0, 0.0, 0.0, 0.0, 0);

	private BobTalonSRX rightFollowerWithPigeon = new BobTalonSRX(4);

	public LeaderBobTalonSRX leftLead = new LeaderBobTalonSRX(14, new BobTalonSRX(12), new BobTalonSRX(13));
	public LeaderBobTalonSRX rightLead = new LeaderBobTalonSRX(3, rightFollowerWithPigeon, new BobTalonSRX(2));

	// private PigeonIMU pigeon = new PigeonIMU(rightFollowerWithPigeon);

	public DriveMode mode = DriveMode.Normal;

	public Drivetrain() {
		leftLead.configFactoryDefault();
		rightLead.configFactoryDefault();

		setupSensors();
		setNeutralMode(NeutralMode.Coast);

		configGains(driveGains);
		configGains(rotationGains);

		leftLead.setInverted(false);
		leftLead.setSensorPhase(true);
		rightLead.setInverted(true);
		rightLead.setSensorPhase(true);

	}

	public void setupSensors() {
		/*
		 * leftLead.configPrimaryFeedbackDevice(FeedbackDevice.CTRE_MagEncoder_Relative)
		 * ; leftLead.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 0);
		 * 
		 * rightLead.configRemoteSensor0(leftLead.getDeviceID(),
		 * RemoteSensorSource.TalonSRX_SelectedSensor);
		 * rightLead.configSensorSum(FeedbackDevice.RemoteSensor0,
		 * FeedbackDevice.CTRE_MagEncoder_Relative);
		 * rightLead.configPrimaryFeedbackDevice(FeedbackDevice.SensorSum, 0.5);
		 * 
		 * rightLead.configRemoteSensor1(rightFollowerWithPigeon.getDeviceID(),
		 * RemoteSensorSource.GadgeteerPigeon_Yaw);
		 * rightLead.configSecondaryFeedbackDevice(FeedbackDevice.RemoteSensor1, (3600.0
		 * / 8192.0));
		 * 
		 * rightLead.configAuxPIDPolarity(false, 0);
		 */
	}

	public void initDefaultCommand() {
		setDefaultCommand(new BobDrive());
	}

	public void configGains(SRXGains gains) {
		this.leftLead.setGains(gains);
		this.rightLead.setGains(gains);
		rightLead.configMaxIntegralAccumulator(ROTATION_PROFILE, 3000);
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

	public void setNeutralMode(NeutralMode neutralMode) {
		this.leftLead.setNeutralMode(neutralMode);
		this.rightLead.setNeutralMode(neutralMode);
	}

	/*
	 * public double getAngle() { double[] ypr = new double[3];
	 * pigeon.getYawPitchRoll(ypr); return ypr[0]; }
	 * 
	 * public void resetPigeon() { this.pigeon.setYaw(0.0, 0); // Yaw is rotation of
	 * robot during autos }
	 */
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
		// SmartDashboard.putNumber("Velocity", this.getVelocity());
		// SmartDashboard.putNumber("Distance Right", this.getRightDriveLeadDistance());
		/*
		 * double[] leftCurrents = this.leftLead.getOutputCurrentArray();
		 * SmartDashboard.putNumber("Left Lead Current: ", leftCurrents[0]);
		 * SmartDashboard.putNumber("Left Follow 1 Current: ", leftCurrents[1]);
		 * SmartDashboard.putNumber("Left Follow 2 Current: ", leftCurrents[2]);
		 * 
		 * double[] rightCurrents = this.rightLead.getOutputCurrentArray();
		 * SmartDashboard.putNumber("right Lead Current: ", rightCurrents[0]);
		 * SmartDashboard.putNumber("right Follow 1 Current: ", rightCurrents[1]);
		 * SmartDashboard.putNumber("right Follow 2 Current: ", rightCurrents[2]);
		 */
		// SmartDashboard.putNumber("Angle", this.getAngle());
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
