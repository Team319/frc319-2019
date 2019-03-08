package org.usfirst.frc.team319.robot.commands.limelightCommands;

import org.usfirst.frc.team319.robot.commands.drivetrainCommands.VisionDrive;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorClose;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorExtend;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorRetract;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class LimelightHatchCollectorPlace extends CommandGroup {

  public LimelightHatchCollectorPlace() {
    addSequential(new VisionDrive(1.5, 0.0));
    addSequential(new HatchCollectorExtend());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchCollectorClose());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchCollectorRetract());
  }
}