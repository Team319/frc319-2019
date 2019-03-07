package org.usfirst.frc.team319.robot.commands.LimelightCommands;

import org.usfirst.frc.team319.robot.commands.drivetrain_Commands.VisionDrive;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorExtend;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorOpen;
import org.usfirst.frc.team319.robot.commands.hatchCollectorCommands.HatchCollectorRetract;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class LimelightHatchCollectorCollect extends CommandGroup {

  public LimelightHatchCollectorCollect() {
    addSequential(new VisionDrive(1.5, 0.0));
    addSequential(new HatchCollectorExtend());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchCollectorOpen());
    addSequential(new WaitCommand(0.5));
    addSequential(new HatchCollectorRetract());
  }
}