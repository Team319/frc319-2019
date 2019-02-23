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
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import org.usfirst.frc.team319.models.BobTalonSRX;
//import org.usfirst.frc.team319.models.IPositionControlledSubsystem;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;
import org.usfirst.frc.team319.models.MotionParameters;
import org.usfirst.frc.team319.models.PositionControlledSubsystem;
import org.usfirst.frc.team319.models.SRXGains;
import org.usfirst.frc.team319.robot.commands.Elevator_Commands.JoystickElevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Elevator extends PositionControlledSubsystem {
  private boolean isElevatorFloorSolenoidExtended = false;

  private boolean isHoldingPosition = false;
  private int onTargetThreshold = 100;

  private int homePosition = 0;

  // ---- Hatch Positions ---- //

  private int hatchCollectPosition = 0;
  private int highHatchPosition = 0;
  private int middleHatchPosition = 0;
  private int lowHatchPosition = 0;

  // ---- Cargo Positions ---- //
  private int cargoCollectPosition = 0;
  private int highCargoPosition = 0;
  private int middleCargoPosition = 20000;
  private int lowCargoRocketPosition = 0;
  private int cargoShipCargoPosition = 0;
  // cargoShipCargoPosition should be around the same as middleHatchPosition

  // ---- Travel Limits Positions ---- //
  private int topOfFirstStagePosition = 0;
  private int maxUpTravelPosition = 40000;
  private int maxDownTravelPosition = homePosition;

  private int targetPosition = 0;

  // ---- Gains, Pid Values, Talon Setup ---- //

  public final static int ELEVATOR_UP = 0;
  public final static int ELEVATOR_DOWN = 1;

  private final SRXGains elevatorUpGains = new SRXGains(ELEVATOR_UP, 0.3, 0.0, 0.0, .2046, 0);
  private final SRXGains elevatorDownGains = new SRXGains(ELEVATOR_DOWN, 0.3, 0.0, 0.0, .2046, 0);

  private MotionParameters UpMotionParameters = new MotionParameters(8000, 4000, elevatorUpGains);
  private MotionParameters DownMotionParameters = new MotionParameters(4000, 3000, elevatorDownGains);

  public BobTalonSRX elevatorFollow1 = new BobTalonSRX(1);
  public BobTalonSRX elevatorFollow2 = new BobTalonSRX(2);
  public BobTalonSRX elevatorFollow14 = new BobTalonSRX(14);
  public LeaderBobTalonSRX elevatorLead = new LeaderBobTalonSRX(15, elevatorFollow1, elevatorFollow2, elevatorFollow14);

  public Elevator() {
    setupSensors();
    setupMotionParameters();

    this.elevatorLead.configClosedloopRamp(0.25);

    this.elevatorLead.configVoltageCompSaturation(11.5);
    this.elevatorLead.enableVoltageCompensation(true);

    this.elevatorLead.configPeakOutputReverse(-1.0);
  }

  public void setupMotionParameters() {
    this.elevatorLead.configMotionParameters(UpMotionParameters);
    this.elevatorLead.configMotionParameters(DownMotionParameters);
  }

  public void setupSensors() {
    this.elevatorLead.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    this.elevatorLead.configForwardSoftLimitEnable(true);
    this.elevatorLead.configForwardSoftLimitThreshold(maxUpTravelPosition);

    this.elevatorLead.configReverseSoftLimitEnable(true);
    this.elevatorLead.configReverseSoftLimitThreshold(homePosition);

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
    // Set the default command for a subsystem here.
    setDefaultCommand(new JoystickElevator());
  }

  public boolean isCarriageSafe(double targetElevatorPosition) {
    boolean atRisk = this.getCurrentPosition() < this.getSafePosition();
    if (atRisk && targetElevatorPosition < maxDownTravelPosition && getCurrentPosition() > topOfFirstStagePosition) {
      return false;
    } else {
      return true;
    }
  }

  public boolean isElevatorFloorSolenoidExtended() {
    return isElevatorFloorSolenoidExtended;
  }

  public void setIsElevatorFloorSolenoidExtended(boolean isElevatorFloorSolenoidExtended) {
    this.isElevatorFloorSolenoidExtended = isElevatorFloorSolenoidExtended;
  }

  public boolean isValidPosition(int position) {
    boolean withinBounds = position <= maxUpTravelPosition && position >= maxDownTravelPosition;
    return withinBounds;
  }

  public boolean isHoldingPosition() {
    return this.isHoldingPosition;
  }

  // ----Get Misc Positions----//
  public int getHomePosition() {
    return this.homePosition;
  }

  public int getMaxDownTravelPosition() {
    return this.maxDownTravelPosition;
  }

  // ----Get Hatch Positions----//
  public int getHatchCollectPosition() {
    return this.hatchCollectPosition;
  }

  public int getHighHatchPosition() {
    return this.highHatchPosition;
  }

  public int getMiddleHatchPosition() {
    return this.middleHatchPosition;
  }

  public int getLowHatchPosition() {
    return this.lowHatchPosition;
  }

  // ----Get Cargo Positions----//
  public int getCargoCollectPosition() {
    return this.cargoCollectPosition;
  }

  public int getHighCargoPosition() {
    return this.highCargoPosition;
  }

  public int getMiddleCargoPosition() {
    return this.middleCargoPosition;
  }

  public int getLowCargoPosition() {
    return this.lowCargoRocketPosition;
  }

  public int getCargoShipPosition() {
    return this.cargoShipCargoPosition;
  }

  // ----Get Travel Limits----//
  public int getTopOfFirstStagePosition() {
    return this.topOfFirstStagePosition;
  }

  public int getMaxUpTravelPosition() {
    return this.maxUpTravelPosition;
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

  public double getSafePosition() {
    return 0;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Position", this.getCurrentPosition());
    SmartDashboard.putNumber("Elevator Velocity", this.getCurrentVelocity());
    SmartDashboard.putNumber("Elevator Target Position", this.getTargetPosition());
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
}
