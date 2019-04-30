package org.usfirst.frc.team319.robot.subsystems;

import org.usfirst.frc.team319.robot.commands.limelight.DriveToTargetWithDistance;
import org.usfirst.frc.team319.robot.commands.limelight.RotateToTarget;
import org.usfirst.frc.team319.utils.BobCircularBuffer;
import org.usfirst.frc.team319.utils.HelperFunctions;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight extends Subsystem {

   private BobCircularBuffer limelightbuffer;

   private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   private NetworkTableEntry tx = table.getEntry("tx");
   private NetworkTableEntry ty = table.getEntry("ty");
   private NetworkTableEntry ta = table.getEntry("ta");
   private NetworkTableEntry ledMode = table.getEntry("ledMode");
   private NetworkTableEntry stream = table.getEntry("stream");

   private static double fovX = 54.0;
   private static double fovY = 41.0;

   private double moveValue = 0.0;
   private double rotateValue = 0.0;

   private double kPD = 0.2;
   private double kID = 0.0;
   private double kDD = 0.3;
   private DriveToTargetWithDistance pidD_ = new DriveToTargetWithDistance(kPD, kID, kDD);

   private double kPR = 0.05;
   private double kIR = 0.0;
   private double kDR = 0.0;
   private RotateToTarget pidR_ = new RotateToTarget(kPR, kIR, kDR);

   public Limelight() {

      this.limelightbuffer = new BobCircularBuffer(3);

   }

   public void execute() {
      // pidD_.start();
      pidR_.start();
   }

   public void pause() {
      pidR_.cancel();
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

   public double circularBufferX() {
      return HelperFunctions.mean(limelightbuffer.toArray());
   }

   public double getXProportional() {
      return getX() / getFovX() / 2;
   }

   public double getY() {
      return ty.getDouble(0.0);
   }

   public double getArea() {
      return ta.getDouble(0.0);
   }

   public boolean setLedModeOn() {
      return this.ledMode.setNumber(3);// 3 is Limelight LED force on
   }

   public boolean setLedModeOff() {
      return this.ledMode.setNumber(1);// 1 is Limelight LED force off
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
      SmartDashboard.putNumber("Limelight Distance", distance);
      // System.out.println("Area" + area);
      // System.out.println("Distance in feet:" + distance);
      return distance;
   }

   public boolean setStreamType() {
      return stream.setNumber(2);
   }

   /*
    * public double getAngle() { double longshort = getTLong() / getTShort();
    * System.out.println(longshort); double partone = 0.014; double squareroot =
    * 5893 - Math.sqrt(18686429 - 7300000 * longshort); double angle = partone *
    * squareroot; return angle; }
    */
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
      limelightbuffer.addLast(getX());

   }
}
