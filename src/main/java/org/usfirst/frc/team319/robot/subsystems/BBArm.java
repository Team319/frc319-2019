/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

/**
 * Add your docs here.
 */
public class BBArm extends PositionControlledSubsystem {

  private boolean isFrontHatchSolenoidExtended = false;

  public BobTalonSRX bbaFollow = new BobTalonSRX(6);
  public BobTalonSRX bbaLead = new BobTalonSRX(10);
  public LeaderBobTalonSRX collectorTalon = new LeaderBobTalonSRX(9);

  // towards floor = negative

  private int homePosition = 0;
  private int safePosition = -4408;
  private int levelThreeHab = -4408;
  private int levelTwoHab = -7059;
  private int hatchFloorPosition = -8500;
  private int cargoCollectPosition = -6400;// 6200
  private int floorPosition = -8750;
  private int liftRobotPosition = -9001;
  private int bbaCarriageSafePosition = -4408;
  private int bbaClimbStartPosition = -4408;

  private int elevatorClearencePosition = -4500;

  private int upPositionLimit = 0;
  private int downPositionLimit = liftRobotPosition;

  private int targetPosition = 0;

  private final static int onTargetThreshold = 200;

  public static final int BBA_UP = 0;
  public static final int BBA_DOWN = 1;

  private final SRXGains upGains = new SRXGains(BBA_UP, 6.0, 0.0, 120.0, 1.0122, 0);
  private final SRXGains downGains = new SRXGains(BBA_DOWN, 6.0, 0.0, 120.0, 1.0122, 0);

  private MotionParameters UpMotionParameters = new MotionParameters(1600, 1024, upGains);// 1600, 800
  private MotionParameters DownMotionParameters = new MotionParameters(1600, 1024, downGains);// 1600, 800

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

  public boolean isFrontHatchSolenoidExtended() {
    return isFrontHatchSolenoidExtended;
  }

  public void setIsFrontHatchSolenoidExtended(boolean isFrontHatchSolenoidExtended) {
    this.isFrontHatchSolenoidExtended = isFrontHatchSolenoidExtended;
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

  public boolean hasClearance(int newTargetPosition) {
    if (newTargetPosition > this.elevatorClearencePosition) {
      return Robot.elevator.getCurrentPosition() > Robot.elevator.getBbaClearancePosition();
    }
    return true;
  }

  //
  public boolean isValidPosition(int position) {
    boolean withinBounds = position <= upPositionLimit && position >= downPositionLimit;
    return withinBounds && this.hasClearance(position);
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
    this.collectorTalon.set(ControlMode.PercentOutput, -signal);
  }

  public void incrementTargetPosition(int increment) {
    int currentTargetPosition = this.targetPosition;
    int newTargetPosition = currentTargetPosition + increment;
    if (isValidPosition(newTargetPosition) && this.hasClearance(newTargetPosition)) {
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

  public int getBbaClimbStartPosition() {
    return bbaClimbStartPosition;
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

  public int getElevatorClearencePosition() {
    return elevatorClearencePosition;
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
    SmartDashboard.putNumber("BBA Follow Rotation", this.getSecondaryPosition());
  }

  @Override
  public void forceSetTargetPosition(int targetPosition) {
    this.targetPosition = targetPosition;
  }

}
