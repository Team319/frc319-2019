/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.GeneralPin;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */

public class Carriage extends Subsystem {

  DigitalInput digitalCargoSensor = new DigitalInput(0);
  CANifier canifier = new CANifier(0);

  private boolean manualCollectFinished = false;
  private boolean isFingerOpen = true;

  private boolean isCarriageLockSolenoidExtended = true;
  // private boolean isPlatypusFaceSolenoidExtended = false;
  private final double safePosition = 0.0;

  private BobTalonSRX passthroughFollow = new BobTalonSRX(8);
  public LeaderBobTalonSRX passThroughLead = new LeaderBobTalonSRX(7, passthroughFollow);

  public Carriage() {
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

  /*
   * public boolean IsPlatypusFaceSolenoidExtended() { return
   * IsPlatypusFaceSolenoidExtended(); }
   * 
   * public void setIsPlatypusFaceExtended(boolean IsPlatypusFaceSolenoidExtended)
   * { this.isPlatypusFaceSolenoidExtended = IsPlatypusFaceSolenoidExtended; }
   * 
   * 
   * 
   * public boolean isPlatypusFaceSolenoidExtended() { return
   * isPlatypusFaceSolenoidExtended; }
   */
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

  public void setManualCollectFinished(boolean manualCollectFinished) {
    this.manualCollectFinished = manualCollectFinished;
  }

}
