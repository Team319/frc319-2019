package org.usfirst.frc.team319.robot.commands.limelightCommands;

import org.usfirst.frc.team319.robot.commands.drivetrainCommands.VisionDrive;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LimelightHatchCollectorPlace extends CommandGroup {

  public LimelightHatchCollectorPlace() {
    addSequential(new VisionDrive(1.5, 0.0));
    addSequential(new HatchCollectorPlace());
  }
}