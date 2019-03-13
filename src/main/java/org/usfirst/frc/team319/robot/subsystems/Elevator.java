/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;
import org.usfirst.frc.team319.models.MotionParameters;
import org.usfirst.frc.team319.models.PositionControlledSubsystem;
import org.usfirst.frc.team319.models.RobotMode;
import org.usfirst.frc.team319.models.SRXGains;
import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.robot.commands.elevator.JoystickElevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PositionControlledSubsystem {
  public boolean isElevatorFloorSolenoidExtended = false;

  private boolean isHoldingPosition = false;
  private int onTargetThreshold = 300;
  private int targetPosition = 0;

  private int homePosition = 0;

  // ---- Hatch Positions ---- //
  private int hatchCollectPosition = 0;
  private int hatchHighPosition = 33300;
  private int hatchMiddlePosition = 17000;
  private int hatchLowPosition = 0; // this value is correct

  // ---- Cargo Positions ---- //
  private int cargoCollectPosition = -5000;
  private int cargoHighPosition = 33300;
  private int cargoMiddlePosition = 17000;
  private int cargoLowPosition = 0; // this value is correct
  private int cargoCargoShipPosition = 10000;
  // cargoShipCargoPosition should be around the same as middleHatchPosition

  // ---- Travel Limits Positions ---- //
  private int maxVerticalLimit = 37400;
  private int bbaClearancePosition = 7500;
  private int minVerticalLimit = cargoCollectPosition;
  private int climbLimit = 1300;
  private int lockPosition = 17800;
  private int climbPosition = 13000;
  private int touchFloorPosition = 1500;
  // ---- Gains, Pid Values, Talon Setup ---- //

  private int climbAcceleration = 10000;
  private int normalAcceleration = 10000;

  private int climbVelocity = 5000;
  private int normalVelocity = 5000;

  public final static int ELEVATOR_UP = 0;
  public final static int ELEVATOR_DOWN = 1;

  private final SRXGains elevatorUpGains = new SRXGains(ELEVATOR_UP, 0.5, 0.004, 24.0, .2046, 500);
  private final SRXGains elevatorDownGains = new SRXGains(ELEVATOR_DOWN, 0.5, 0.004, 24.0, .2046, 500);

  private MotionParameters UpMotionParameters = new MotionParameters(normalAcceleration, normalVelocity,
      elevatorUpGains);// 10000, 4500
  private MotionParameters DownMotionParameters = new MotionParameters(normalAcceleration, normalVelocity,
      elevatorDownGains);// 10000, 4500

  private MotionParameters ClimbUpMotionParameters = new MotionParameters(climbAcceleration, climbVelocity,
      elevatorUpGains);
  private MotionParameters ClimbDownMotionParameters = new MotionParameters(climbAcceleration, climbVelocity,
      elevatorDownGains);

  public BobTalonSRX elevatorFollow1 = new BobTalonSRX(1);
  public BobTalonSRX elevatorFollow2 = new BobTalonSRX(2);
  public BobTalonSRX elevatorFollow14 = new BobTalonSRX(14);
  public LeaderBobTalonSRX elevatorLead = new LeaderBobTalonSRX(15, elevatorFollow1, elevatorFollow2, elevatorFollow14);

  public Elevator() {
    setupSensors();
    setupMotionParameters();

    this.elevatorLead.configClosedloopRamp(0.125);

    this.elevatorLead.configVoltageCompSaturation(11.5);
    this.elevatorLead.enableVoltageCompensation(true);

    this.elevatorLead.configPeakOutputReverse(-1.0);
  }

  public void setupMotionParameters() {
    if (Robot.mode == RobotMode.Climb) {
      this.elevatorLead.configMotionParameters(ClimbUpMotionParameters);
      this.elevatorLead.configMotionParameters(ClimbDownMotionParameters);
    } else {
      this.elevatorLead.configMotionParameters(UpMotionParameters);
      this.elevatorLead.configMotionParameters(DownMotionParameters);
    }

    this.elevatorLead.configMaxIntegralAccumulator(ELEVATOR_UP, 3000);
    this.elevatorLead.configMaxIntegralAccumulator(ELEVATOR_DOWN, 3000);
  }

  public void setupSensors() {
    this.elevatorLead.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    this.elevatorLead.configForwardSoftLimitEnable(true);
    this.elevatorLead.configForwardSoftLimitThreshold(maxVerticalLimit);

    this.elevatorLead.configReverseSoftLimitEnable(true);
    this.elevatorLead.configReverseSoftLimitThreshold(minVerticalLimit);

    this.elevatorLead.setInverted(false);
    this.elevatorFollow14.setInverted(false);
    this.elevatorFollow1.setInverted(true);
    this.elevatorFollow2.setInverted(true);

    this.elevatorLead.setSensorPhase(true);

    this.elevatorLead.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
    this.elevatorLead.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickElevator());

  }

  public boolean isElevatorFloorSolenoidExtended() {
    return isElevatorFloorSolenoidExtended;
  }

  public void setIsElevatorFloorSolenoidExtended(boolean isElevatorFloorSolenoidExtended) {
    this.isElevatorFloorSolenoidExtended = isElevatorFloorSolenoidExtended;
  }

  public boolean isValidPosition(int position) {
    boolean withinBounds = false;
    if (Robot.mode != RobotMode.Climb) {
      withinBounds = position <= maxVerticalLimit && position >= minVerticalLimit;
    } else {
      withinBounds = position <= maxVerticalLimit && position >= climbLimit;
    }
    return withinBounds && this.hasClearance(position);
  }

  public boolean isHoldingPosition() {
    return this.isHoldingPosition;
  }

  public int getBbaClearancePosition() {
    return bbaClearancePosition;
  }

  // ----Get Misc Positions----//
  public int getHomePosition() {
    return this.homePosition;
  }

  public int getMinVerticalLimit() {
    return this.minVerticalLimit;
  }

  public int getClimbLimit() {
    return this.climbLimit;
  }

  // ----Get Hatch Positions----//
  public int getHatchCollectPosition() {
    return this.hatchCollectPosition;
  }

  public int getHatchHighPosition() {
    return this.hatchHighPosition;
  }

  public int getHatchMiddlePosition() {
    return this.hatchMiddlePosition;
  }

  public int getHatchLowPosition() {
    return this.hatchLowPosition;
  }

  // ----Get Cargo Positions----//
  public int getCargoCollectPosition() {
    return this.cargoCollectPosition;
  }

  public int getCargoHighPosition() {
    return this.cargoHighPosition;
  }

  public int getCargoMiddlePosition() {
    return this.cargoMiddlePosition;
  }

  public int getCargoLowPosition() {
    return this.cargoLowPosition;
  }

  public int getCargoShipPosition() {
    return this.cargoCargoShipPosition;
  }

  // ----Get Travel Limits----//

  public int getMaxVerticalLimit() {
    return this.maxVerticalLimit;
  }

  public int getLockPosition() {
    return lockPosition;
  }

  public int getTouchFloorPosition() {
    return touchFloorPosition;
  }

  public int getClimbPosition() {
    return climbPosition;
  }

  public void percentVbus(double signal) {
    this.elevatorLead.set(ControlMode.PercentOutput, signal);
  }

  public void manageMotion(double targetPosition) {
    double currentPosition = getCurrentPosition();
    if (currentPosition < targetPosition) {
      elevatorLead.selectMotionParameters(UpMotionParameters);
    } else {
      elevatorLead.selectMotionParameters(DownMotionParameters);
    }
  }

  public void motionMagicControl() {
    this.manageMotion(targetPosition);
    this.elevatorLead.set(ControlMode.MotionMagic, targetPosition);
  }

  public void incrementTargetPosition(int increment) {
    int currentTargetPostion = this.targetPosition;
    int newTargetPosition = currentTargetPostion + increment;
    if (isValidPosition(newTargetPosition)) {
      this.targetPosition = newTargetPosition;
    }
  }

  public boolean hasClearance(int newTargetPosition) {
    if (newTargetPosition < this.homePosition) {
      return Robot.bbarm.getCurrentPosition() < Robot.bbarm.getElevatorClearencePosition();
    }
    return true;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Position", this.getCurrentPosition());
    SmartDashboard.putNumber("Elevator Velocity", this.getCurrentVelocity());
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
    return this.elevatorLead.getSelectedSensorPosition();
  }

  @Override
  public double getCurrentVelocity() {
    double currentVelocity = this.elevatorLead.getSelectedSensorVelocity();
    return currentVelocity;
  }

  @Override
  public boolean isInPosition(int targetPosition) {
    int currentPosition = this.getCurrentPosition();
    int positionError = Math.abs(currentPosition - targetPosition);
    if (positionError < onTargetThreshold) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void forceSetTargetPosition(int targetPosition) {
    this.targetPosition = targetPosition;
  }
}
