package org.usfirst.frc.team319.robot.commands.limelight;

import org.usfirst.frc.team319.robot.commands.drivetrain.VisionDrive;
import org.usfirst.frc.team319.robot.commands.hatchCollector.HatchCollectorCollect;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LimelightHatchCollectorCollect extends CommandGroup {

  public LimelightHatchCollectorCollect() {
    addSequential(new VisionDrive(1.5, 0.0));
    addSequential(new HatchCollectorCollect());
  }
}