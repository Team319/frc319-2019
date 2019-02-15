/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.subsystems;

import org.usfirst.frc.team319.models.BobTalonSRX;
import org.usfirst.frc.team319.models.LeaderBobTalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Carriage extends Subsystem {

  private boolean isBeakOpen = true;

  private final double safePosition = 0.0;

  public LeaderBobTalonSRX passThoughLead = new LeaderBobTalonSRX(3, new BobTalonSRX(4));

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
}
