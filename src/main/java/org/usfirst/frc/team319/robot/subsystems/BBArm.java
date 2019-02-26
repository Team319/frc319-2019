/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;
import org.usfirst.frc.team319.models.MotionParameters;
import org.usfirst.frc.team319.models.PositionControlledSubsystem;
import org.usfirst.frc.team319.models.SRXGains;
import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.JoystickBBA;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class BBArm extends PositionControlledSubsystem {
  private boolean isHatchCollectorArmSolenoidExtended = true;
  private boolean isHatchCollectorSolenoidExtended = false;

  public BobTalonSRX bbaFollow = new BobTalonSRX(6);
  public BobTalonSRX bbaLead = new BobTalonSRX(10);
  public LeaderBobTalonSRX collectorTalon = new LeaderBobTalonSRX(9);

  // towards floor = negative

  private int homePosition = -500;
  private int safePosition = 0;
  private int levelThreeHab = 0;
  private int levelTwoHab = 0;
  private int hatchFloorPosition = 0;
  private int cargoCollectPosition = -6500;
  private int floorPosition = -8750;
  private int liftRobotPosition = -9001;
  private int bbaCarriageSafePosition = -4000;

  private int upPositionLimit = 0;
  private int downPositionLimit = liftRobotPosition;

  private int targetPosition = 0;

  private final static int onTargetThreshold = 100;

  public static final int BBA_UP = 0;
  public static final int BBA_DOWN = 1;

  private final SRXGains upGains = new SRXGains(BBA_UP, 6.0, 0.0, 120.0, 1.0122, 0);
  private final SRXGains downGains = new SRXGains(BBA_DOWN, 6.0, 0.0, 120.0, 1.0122, 0);

  private MotionParameters UpMotionParameters = new MotionParameters(1600, 800, upGains);
  private MotionParameters DownMotionParameters = new MotionParameters(1600, 800, downGains);

  /*
   * int elevatorPosition = Robot.elevator.getCurrentPosition(); double
   * elevatorSafePosition = Robot.elevator.getSafePosition();
   */
  public BBArm() {

    configSoftLimits();

    this.bbaLead.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    this.bbaFollow.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    this.bbaLead.setInverted(false);
    this.bbaLead.setSensorPhase(false);

    this.bbaFollow.setInverted(true);
    this.bbaFollow.setSensorPhase(false);

    this.bbaLead.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
    this.bbaLead.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);
    this.bbaFollow.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
    this.bbaFollow.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);

    this.bbaLead.setNeutralMode(NeutralMode.Brake);
    this.bbaFollow.setNeutralMode(NeutralMode.Brake);

    this.bbaLead.configMotionParameters(UpMotionParameters);
    this.bbaLead.configMotionParameters(DownMotionParameters);
    this.bbaFollow.configMotionParameters(UpMotionParameters);
    this.bbaFollow.configMotionParameters(DownMotionParameters);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickBBA());
  }

  public boolean isHatchCollectorSolenoidExtended() {
    return isHatchCollectorSolenoidExtended;
  }

  public void setIsHatchCollectorSolenoidExtended(boolean isHatchCollectorSolenoidExtended) {
    this.isHatchCollectorSolenoidExtended = isHatchCollectorSolenoidExtended;
  }

  public boolean isHatchCollectorArmSolenoidExtended() {
    return isHatchCollectorArmSolenoidExtended;
  }

  public void setIsHatchCollectorArmSolenoidExtended(boolean isHatchCollectorArmSolenoidExtended) {
    this.isHatchCollectorArmSolenoidExtended = isHatchCollectorArmSolenoidExtended;
  }

  public void configSoftLimits() {
    // ------------Lead Limits------------//
    this.bbaLead.configForwardSoftLimitThreshold(upPositionLimit);
    this.bbaLead.configReverseSoftLimitThreshold(downPositionLimit);

    this.bbaLead.configForwardSoftLimitEnable(true);
    this.bbaLead.configReverseSoftLimitEnable(true);

    // ------------Follow Limits------------//
    this.bbaFollow.configForwardSoftLimitThreshold(upPositionLimit);
    this.bbaFollow.configReverseSoftLimitThreshold(downPositionLimit);

    this.bbaFollow.configForwardSoftLimitEnable(true);
    this.bbaFollow.configReverseSoftLimitEnable(true);
  }

  public boolean isBBArmSafe(double targetBBArmPosition) {

    double elevatorSafePosition = Robot.elevator.getSafePosition();
    int elevatorPosition = Robot.elevator.getCurrentPosition();

    boolean elevatorInterfering = elevatorPosition < elevatorSafePosition;

    boolean bbaSafePosition = this.getCurrentPosition() < this.getSafePosition();

    boolean maxLimitCheck = targetBBArmPosition < downPositionLimit;

    if (!elevatorInterfering) {
      return true;
    } else if (bbaSafePosition && maxLimitCheck) {
      return true;
    } else {
      return false;
    }
  }

  //
  public boolean isValidPosition(int position) {
    boolean withinBounds = position <= upPositionLimit && position >= downPositionLimit;
    return withinBounds;
  }

  public void manageMotion(double targetPosition) {
    double currentPosition = getCurrentPosition();
    if (currentPosition < targetPosition) {
      bbaLead.selectMotionParameters(UpMotionParameters);
      bbaFollow.selectMotionParameters(UpMotionParameters);
    } else {
      bbaLead.selectMotionParameters(DownMotionParameters);
      bbaFollow.selectMotionParameters(DownMotionParameters);

    }
  }

  public void motionMagicControl() {
    this.manageMotion(targetPosition);
    this.bbaLead.set(ControlMode.MotionMagic, targetPosition);
    this.bbaFollow.set(ControlMode.MotionMagic, targetPosition);
  }

  public void percentVbus(double signal) {
    this.bbaLead.set(ControlMode.PercentOutput, signal);
  }

  public void setSpeed(double speed) {
    this.bbaLead.set(ControlMode.Velocity, speed);
  }

  public void setCollectorSpeed(double speed) {
    this.collectorTalon.set(ControlMode.PercentOutput, speed);
  }

  public void percentVbusCollector(double signal) {
    this.collectorTalon.set(ControlMode.PercentOutput, signal);
  }

  public void incrementTargetPosition(int increment) {
    int currentTargetPosition = this.targetPosition;
    int newTargetPosition = currentTargetPosition + increment;
    if (isValidPosition(newTargetPosition) && isBBArmSafe(newTargetPosition)) {
      this.targetPosition = newTargetPosition;
    }
  }

  public double getLevelThreeHab() {
    return levelThreeHab;
  }

  public double getLevelTwoHab() {
    return levelTwoHab;
  }

  public double getHatchFloorPosition() {
    return hatchFloorPosition;
  }

  public int getCargoCollectPosition() {
    return cargoCollectPosition;
  }

  public int getLiftRobotPosition() {
    return liftRobotPosition;
  }

  public int getHomePosition() {
    return homePosition;
  }

  @Override
  public boolean setTargetPosition(int targetPosition) {
    if (isValidPosition(targetPosition)) {
      this.targetPosition = targetPosition;
      return true;
    }
    return false;
  }

  @Override
  public int getTargetPosition() {
    return this.targetPosition;
  }

  @Override
  public int getCurrentPosition() {
    return this.bbaLead.getSelectedSensorPosition();
  }

  public int getSecondaryPosition() {
    return this.bbaFollow.getSelectedSensorPosition();
  }

  public int getSafePosition() {
    return safePosition;
  }

  public int getFloorPosition() {
    return floorPosition;
  }

  public int getBbaCarriageSafePosition() {
    return bbaCarriageSafePosition;
  }

  @Override
  public double getCurrentVelocity() {
    return this.bbaLead.getSelectedSensorVelocity();
  }

  public double getFollowCurrentVelocity() {
    return this.bbaFollow.getSelectedSensorVelocity();
  }

  @Override
  public boolean isInPosition(int targetPosition) {
    int currentPosition = this.getCurrentPosition();
    int positionError = Math.abs(currentPosition - targetPosition);
    return positionError < onTargetThreshold;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("BBA Rotation", this.getCurrentPosition());
    SmartDashboard.putNumber("BBA Secondary Position", this.getSecondaryPosition());
    SmartDashboard.putNumber("BBA Lead Velocity", this.getCurrentVelocity());
    SmartDashboard.putNumber("BBA Follow Velocity", this.getFollowCurrentVelocity());
    SmartDashboard.putNumber("BBA Target Position", this.getTargetPosition());
  }

}
