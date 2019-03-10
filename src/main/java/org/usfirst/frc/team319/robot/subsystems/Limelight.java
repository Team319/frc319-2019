package org.usfirst.frc.team319.robot.subsystems;

import org.usfirst.frc.team319.robot.commands.limelightCommands.DriveToTargetWithDistance;
import org.usfirst.frc.team319.robot.commands.limelightCommands.RotateToTarget;
import org.usfirst.frc.team319.utils.BobCircularBuffer;
import org.usfirst.frc.team319.utils.HelperFunctions;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Limelight extends Subsystem {

   private BobCircularBuffer limelightbuffer;

   NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   NetworkTableEntry tx = table.getEntry("tx");
   NetworkTableEntry ty = table.getEntry("ty");
   NetworkTableEntry ta = table.getEntry("ta");
   NetworkTableEntry thor = table.getEntry("thor");
   NetworkTableEntry tvert = table.getEntry("tvert");
   NetworkTableEntry tv = table.getEntry("tv");
   NetworkTableEntry ts = table.getEntry("ts");
   NetworkTableEntry tshort = table.getEntry("tshort");
   NetworkTableEntry tlong = table.getEntry("tlong");

   private static double fovX = 54.0;
   private static double fovY = 41.0;

   double moveValue = 0.0;
   double rotateValue = 0.0;

   double kPD = 0.2;
   double kID = 0.0;
   double kDD = 0.3;
   DriveToTargetWithDistance pidD_ = new DriveToTargetWithDistance(kPD, kID, kDD);

   double kPR = 0.07;
   double kIR = 0.0;
   double kDR = 0.07;
   RotateToTarget pidR_ = new RotateToTarget(kPR, kIR, kDR);

   public Limelight() {

      this.limelightbuffer = new BobCircularBuffer(5);

   }

   public void execute() {
      pidD_.start();
      pidR_.start();
   }

   public void setSetpoints(double drive_setpoint, double rotate_setpoint) {
      pidD_.setSetpoint(drive_setpoint);
      pidR_.setSetpoint(rotate_setpoint);
   }

   public void setRotationSetpoints(double rotate_setpoint) {
      pidR_.setSetpoint(rotate_setpoint);
   }

   public double getX() {
      return tx.getDouble(0.0);
   }

   public double getY() {
      return ty.getDouble(0.0);
   }

   public double getArea() {
      return ta.getDouble(0.0);
   }

   public double getThor() {
      return thor.getDouble(0.0);
   }

   public double getVert() {
      return tvert.getDouble(0.0);
   }

   public double getTv() {
      return tv.getDouble(0.0);
   }

   public double getTs() {
      return ts.getDouble(0.0);
   }

   public double getTLong() {
      return tlong.getDouble(0.0);
   }

   public double getTShort() {
      return tshort.getDouble(0.0);
   }

   public double getFovX() {
      return fovX;
   }

   public double getFovY() {
      return fovY;
   }

   public double getDistance() {
      double area = this.getArea();
      double distance = Math.pow((area / 17.854), (1 / -2.272));
      return distance;
   }

   public double getAngle() {
      double longshort = getTLong() / getTShort();
      System.out.println(longshort);
      double partone = 0.014;
      double squareroot = 5893 - Math.sqrt(18686429 - 7300000 * longshort);
      double angle = partone * squareroot;
      return angle;
   }

   public void trackPIDD(double output) {
      moveValue = -output;
   }

   public void trackPIDR(double output) {
      rotateValue = output;
   }

   public void stopRobot() {
      moveValue = 0.0;
   }

   public double trackDrive() {
      return moveValue;
   }

   public double trackRotate() {
      return rotateValue;
   }

   public double getDistanceBasedOnArea() {
      return HelperFunctions.mean(limelightbuffer.toArray());

   }

   @Override
   protected void initDefaultCommand() {
   }

   @Override
   public void periodic() {
      limelightbuffer.addLast(getDistance());
   }
}
