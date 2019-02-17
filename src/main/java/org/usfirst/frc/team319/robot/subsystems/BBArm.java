/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.IPositionControlledSubsystem;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;
import org.usfirst.frc.team319.models.MotionParameters;
import org.usfirst.frc.team319.models.SRXGains;
import org.usfirst.frc.team319.robot.Robot;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.DoNothing;
import org.usfirst.frc.team319.robot.commands.BBArm_Commands.JostickBBA;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class BBArm extends Subsystem implements IPositionControlledSubsystem {
  private boolean isHatchCollectorArmSolenoidExtended = true;
  private boolean isHatchCollectorSolenoidExtended = false;


  public BobTalonSRX bbaFollow = new BobTalonSRX(6);
  public LeaderBobTalonSRX bbaLead = new LeaderBobTalonSRX(10, bbaFollow);
  public LeaderBobTalonSRX collectorTalon = new LeaderBobTalonSRX(9);

  private int upPositionLimit = 0;
  private int downPositionLimit = 0;

  //towards floor = positive
  private int floor = 279607;
  private int homePosition = 0;
  private int safePosition = 0;
  private int levelThreeHab = 0;
  private int levelTwoHab = 0;
  private int hatchFloorPosition = 0;

  private int targetPosition = 0;

  public static final int BBA_UP = 0;
  public static final int BBA_DOWN = 1;

  private final SRXGains upGains = new SRXGains(BBA_UP, 0.0, 0.0, 0.0, 0.0, 0);
  private final SRXGains downGains = new SRXGains(BBA_DOWN, 0.0, 0.0, 0.0, 0.0, 0);

  private MotionParameters UpMotionParameters = new MotionParameters(2600, 2000, upGains);
  private MotionParameters DownMotionParameters = new MotionParameters(2600, 2000, downGains);

  public BBArm(){

    this.bbaLead.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

		this.bbaLead.setInverted(false);
    this.bbaLead.setSensorPhase(true);
    
    this.bbaFollow.setInverted(true);
    this.bbaFollow.setSensorPhase(true);

  	this.bbaLead.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10);
	  this.bbaLead.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10);

	//	this.bbaLead.configForwardSoftLimitThreshold(downPositionLimit);
	//	this.bbaLead.configReverseSoftLimitThreshold(upPositionLimit);

	//	this.bbaLead.configForwardSoftLimitEnable(false);
   // this.bbaLead.configReverseSoftLimitEnable(false);
    
    this.bbaLead.setNeutralMode(NeutralMode.Brake);

		this.bbaLead.configMotionParameters(UpMotionParameters);
		this.bbaLead.configMotionParameters(DownMotionParameters);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JostickBBA());
  }

  public boolean isHatchCollectorSolenoidExtended(){
    return isHatchCollectorSolenoidExtended;
  }

  public void setIsHatchCollectorSolenoidExtended(boolean isHatchCollectorSolenoidExtended){
    this.isHatchCollectorSolenoidExtended = isHatchCollectorSolenoidExtended;
  }

  public boolean isHatchCollectorArmSolenoidExtended() {
    return isHatchCollectorArmSolenoidExtended;
  }

  public void setIsHatchCollectorArmSolenoidExtended(boolean isHatchCollectorArmSolenoidExtended) {
    this.isHatchCollectorArmSolenoidExtended = isHatchCollectorArmSolenoidExtended;
  }

  public boolean isBBArmSafe(double targetBBArmPosition) {
    boolean atRisk = this.getCurrentPosition() < this.getSafePosition();
    //System.out.println("is BBA at risk: " + atRisk);
    if (atRisk && targetBBArmPosition < floor && getCurrentPosition() > homePosition) {
      return false;
    } else {
      return true;
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
    } else {
      bbaLead.selectMotionParameters(DownMotionParameters);

    }
  }

  public void motionMagicControl() {
    this.manageMotion(targetPosition);
    this.bbaLead.set(ControlMode.MotionMagic, targetPosition, DemandType.ArbitraryFeedForward, 0.1);
  }
  public void percentVbus(double signal){
    this.bbaLead.set(ControlMode.PercentOutput, signal);
  }
  public void percentVbusCollector(double signal){
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

  @Override
  public boolean setTargetPosition(int targetPosition) {
    return false;
  }

  @Override
  public int getTargetPosition() {
    return 0;
  }

  @Override
  public int getCurrentPosition() {
		return this.bbaLead.getSelectedSensorPosition();
  }

  public int getSafePosition() {
    return safePosition;
  }

  @Override
  public double getCurrentVelocity() {
    return 0;
  }
  

  @Override
  public boolean isInPosition(int targetPosition) {
    return false;
  }

  @Override
	public void periodic() {
  	SmartDashboard.putNumber("BBA Rotation", this.getCurrentPosition());
	}
}
