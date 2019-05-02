/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.GeneralPin;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Carriage extends Subsystem {

  // Sets the digital beam break sensor
  private DigitalInput digitalCargoSensor = new DigitalInput(0);

  // incorporates canifier
  public CANifier canifier = new CANifier(0);

  public boolean manualCollectFinished = false;
  public boolean isFingerOpen = true;
  public boolean isHatchCollectorExtended = false;
  public boolean isCollecting = false;

  private boolean isCarriageLockSolenoidExtended = true;
  private final double safePosition = 0.0;

  // sets talon IDs
  private BobTalonSRX passthroughFollow = new BobTalonSRX(8);
  public LeaderBobTalonSRX passThroughLead = new LeaderBobTalonSRX(7, passthroughFollow);

  public Carriage() {
    passThroughLead.setInverted(false);
    passthroughFollow.setInverted(false);

    passThroughLead.setNeutralMode(NeutralMode.Brake);
    passthroughFollow.setInverted(InvertType.OpposeMaster);
    passthroughFollow.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Ball Detected", this.ballDetected());
    SmartDashboard.putBoolean("Collect Finished", this.getManualCollectFinished());
  }

  @Override
  public void initDefaultCommand() {
  }

  public boolean isCarriageLockSolenoidExtended() {
    return isCarriageLockSolenoidExtended;
  }

  public void setIsCarriageLockSolenoidExtended(boolean isCarriageLockSolenoidExtended) {
    this.isCarriageLockSolenoidExtended = isCarriageLockSolenoidExtended;
  }

  public boolean isFingerOpen() {
    return isFingerOpen;
  }

  public void setIsFingerOpen(boolean isFingerOpen) {
    this.isFingerOpen = isFingerOpen;
  }

  public boolean isFrontHatchCollectorExtended() {
    return isHatchCollectorExtended;
  }

  public void setisFrontHatchCollectorExtended(boolean isFrontHatchCollectorExtended) {
    this.isHatchCollectorExtended = isFrontHatchCollectorExtended;
  }

  public double isCarraigeSafe(int newTargetPosition) {
    return this.safePosition;
  }

  public boolean getDigitalCargoSensorValue() {
    return digitalCargoSensor.get();
  }

  public boolean ballDetected() {
    return canifier.getGeneralInput(GeneralPin.LIMR);
  }

  public boolean getManualCollectFinished() {
    return manualCollectFinished;
  }

  public void percentVbusPassthrough(double speed) {
    this.passThroughLead.set(ControlMode.PercentOutput, speed);

  }

  public void setManualCollectFinished(boolean manualCollectFinished) {
    this.manualCollectFinished = manualCollectFinished;
  }

}
