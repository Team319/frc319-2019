/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team319.robot.commands.robot;

import org.usfirst.frc.team319.robot.commands.carriage.CollectorSetSpeed;
import org.usfirst.frc.team319.robot.commands.carriage.PassthroughSetSpeed;
import org.usfirst.frc.team319.robot.commands.carriage.WaitForCargo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CollectCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CollectCargo() {
    addSequential(new GoToCollectPose());
    addParallel(new PassthroughSetSpeed(0.4));
    addParallel(new CollectorSetSpeed(-1.0));
    addSequential(new WaitForCargo());
    addParallel(new PassthroughSetSpeed(0.0));
    addParallel(new CollectorSetSpeed(0.0));
    // addSequential(new GoToHomePose());
  }
}
