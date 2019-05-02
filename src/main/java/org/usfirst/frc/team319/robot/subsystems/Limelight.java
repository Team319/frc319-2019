package org.usfirst.frc.team319.robot.subsystems;

import org.usfirst.frc.team319.robot.commands.limelight.RotateToTarget;
import org.usfirst.frc.team319.utils.BobCircularBuffer;
import org.usfirst.frc.team319.utils.HelperFunctions;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//limelight class, everything limelight will be found here.
public class Limelight extends Subsystem {

   private BobCircularBuffer limelightbuffer;

   // these Network tables get the data values from the limelight.
   private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   private NetworkTableEntry tx = table.getEntry("tx");// get the x values
   private NetworkTableEntry ty = table.getEntry("ty");// get Y values (up/down)
   private NetworkTableEntry ta = table.getEntry("ta");// get the area (distance)
   private NetworkTableEntry ledMode = table.getEntry("ledMode");
   private NetworkTableEntry stream = table.getEntry("stream");

   private static double fovX = 54.0;
   private static double fovY = 41.0;

   private double moveValue = 0.0;
   private double rotateValue = 0.0;

   // set PID values
   private double kPR = 0.05;
   private double kIR = 0.0;
   private double kDR = 0.0;
   private RotateToTarget pidR_ = new RotateToTarget(kPR, kIR, kDR);

   public Limelight() {

      this.limelightbuffer = new BobCircularBuffer(3);

   }

   // start using PID values
   public void execute() {
      pidR_.start();
   }

   // stop PID values
   public void pause() {
      pidR_.cancel();
   }

   // set the next location the robot needs to go to based on limelight values.
   public void setSetpoints(double drive_setpoint, double rotate_setpoint) {
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

   // turn area into distance in feet
   public double getDistance() {
      double area = this.getArea();
      double distance = Math.pow((area / 17.854), (1 / -2.272));
      SmartDashboard.putNumber("Limelight Distance", distance);
      return distance;
   }

   public boolean setStreamType() {
      return stream.setNumber(2);
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
      limelightbuffer.addLast(getX());

   }
}
