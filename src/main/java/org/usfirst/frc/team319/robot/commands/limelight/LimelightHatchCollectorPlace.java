package org.usfirst.frc.team319.robot.commands.limelight;

import org.usfirst.frc.team319.robot.commands.drivetrain.VisionDrive;
import org.usfirst.frc.team319.robot.commands.hatch_collector.HatchCollectorPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LimelightHatchCollectorPlace extends CommandGroup {

  public LimelightHatchCollectorPlace() {
    addSequential(new VisionDrive(1.5, 0.0));
    addSequential(new HatchCollectorPlace());
  }
}