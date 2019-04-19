package org.usfirst.frc.team319.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team319.models.DriveMode;
import org.usfirst.frc.team319.models.DriveSignal;
import org.usfirst.frc.team319.robot.commands.drivetrain.BobDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	public static int DRIVE_PROFILE = 0;
	public static int ROTATION_PROFILE = 1;

	public CANSparkMax leftLead = new CANSparkMax(11, MotorType.kBrushless);
	public CANSparkMax rightLead = new CANSparkMax(3, MotorType.kBrushless);

	public CANSparkMax leftFollow1 = new CANSparkMax(12, MotorType.kBrushless);
	public CANSparkMax leftFollow2 = new CANSparkMax(13, MotorType.kBrushless);

	public CANSparkMax rightFollow1 = new CANSparkMax(4, MotorType.kBrushless);
	public CANSparkMax rightFollow2 = new CANSparkMax(5, MotorType.kBrushless);

	public DriveMode mode = DriveMode.Normal;

	public Drivetrain() {

		setupFollowers();

		leftLead.setInverted(false);
		rightLead.setInverted(true);

	}

	private void setupFollowers() {
		leftFollow1.follow(leftLead);
		leftFollow2.follow(leftLead);
		rightFollow1.follow(rightLead);
		rightFollow2.follow(rightLead);

	}

	public void initDefaultCommand() {
		setDefaultCommand(new BobDrive());
	}

	public void drive(double left, double right) {
		this.leftLead.set(left);
		this.rightLead.set(right);
	}

	public void drive(DriveSignal driveSignal) {
		this.drive(driveSignal.getLeft(), driveSignal.getRight());
	}

	@Override
	public void periodic() {
	}

}
