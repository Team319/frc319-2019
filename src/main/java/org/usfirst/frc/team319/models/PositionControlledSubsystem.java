/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.models;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class PositionControlledSubsystem extends Subsystem {

  public abstract boolean setTargetPosition(int targetPosition);

  public abstract void forceSetTargetPosition(int targetPosition);

  public abstract int getTargetPosition();

  public abstract int getCurrentPosition();

  public abstract double getCurrentVelocity();

  public abstract void motionMagicControl();

  public abstract boolean isInPosition(int targetPosition);
}
