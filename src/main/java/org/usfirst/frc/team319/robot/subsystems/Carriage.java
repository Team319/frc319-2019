/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.CANifier;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;
//import org.usfirst.frc.team319.models.SRXGains;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;


/**
 * Add your docs here.
 */



public class Carriage extends Subsystem {
  
  AnalogInput analogCargoSensor = new AnalogInput(0);
  DigitalInput digitalCargoSensor = new DigitalInput(0);
  CANifier canifier = new CANifier(0);

  private boolean isBeakOpen = true;

  private final double safePosition = 0.0;

  public LeaderBobTalonSRX passThroughLead = new LeaderBobTalonSRX(7, new BobTalonSRX(8));

  public Carriage() {

  }

  @Override
  public void periodic() {
  }

  private double getCurrentPosition() {
    return this.passThroughLead.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
  }

  public boolean isBeakOpen() {
    return isBeakOpen;
  }

  public void setIsBeakOpen(boolean isBeakOpen) {
    this.isBeakOpen = isBeakOpen;
  }

  public double isCarraigeSafe(int newTargetPosition) {
    return this.safePosition;
  }

  public double getAnalogCargoSensorValue(){
    return analogCargoSensor.getVoltage();
  }

  public boolean getDigitalCargoSensorValue() {
    return digitalCargoSensor.get();
  }
}
