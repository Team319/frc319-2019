/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.BBArm_Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BbaSafelyGoToClimbStartPosition extends CommandGroup {
  public BbaSafelyGoToClimbStartPosition() {
    addSequential(new BbaWaitForClearance());
    addSequential(new BbaGoToClimbStartPosition());
  }
}
