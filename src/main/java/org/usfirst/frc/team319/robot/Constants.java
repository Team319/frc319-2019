package org.usfirst.frc.team319.robot;

public class Constants {

    public static class Drivetrain {
        public static final int kLeftDriveMasterId = 11;
        public static final int kLeftDriveSlaveId = 12;
        public static final int kRightDriveMasterId = 13;
        public static final int kRightDriveSlaveId = 14;

        public static final int kLeftDriveEncoderA = 0;
        public static final int kLeftDriveEncoderB = 1;
        public static final int kRightDriveEncoderA = 2;
        public static final int kRightDriveEncoderB = 3;

        public static final double kDriveEncoderPPR = 1000.0;

        public static final double kMinLookAhead = 12.0; // inches
        public static final double kMinLookAheadSpeed = 12.0; // inches per second
        public static final double kMaxLookAhead = 48.0; // inches
        public static final double kMaxLookAheadSpeed = 120.0; // inches per second
        public static final double kDeltaLookAhead = kMaxLookAhead - kMinLookAhead;
        public static final double kDeltaLookAheadSpeed = kMaxLookAheadSpeed - kMinLookAheadSpeed;

        public static final double kInertiaSteeringGain = 0.0; // angular velocity command is multiplied by this gain *
        // our speed
        // in inches per sec
        public static final double kPathFollowingMaxAccel = 80.0; // inches per second ^ 2
        public static final double kPathFollowingMaxVel = 120.0; // inches per second
        public static final double kPathFollowingProfileKp = 0.3 / 12.0; // % throttle per inch of error
        public static final double kPathFollowingProfileKi = 0.0;
        public static final double kPathFollowingProfileKv = 0.01 / 12.0; // % throttle per inch/s of error
        public static final double kPathFollowingProfileKffv = 0.003889; // % throttle per inch/s
        public static final double kPathFollowingProfileKffa = 0.001415; // % throttle per inch/s^2
        public static final double kPathFollowingProfileKs = 0.1801 / 12.0; // % throttle
        public static final double kPathFollowingGoalPosTolerance = 3.0;
        public static final double kPathFollowingGoalVelTolerance = 12.0;
        public static final double kPathStopSteeringDistance = 12.0;
        public static final double kDriveVoltageRampRate = 0.0;
        public static final int kDriveCurrentThrottledLimit = 30; // amps
        public static final int kDriveCurrentUnThrottledLimit = 80; // amps

        public static final double kFollowTargetSamplingDistance = 1.0;
        public static final double kFollowTargetLookahead = 30.0;
        public static final double kFollowTargetTolerance = 0.1;
        public static final double kFollowTargetSteeringScale = 0.05 * 24;
        public static final double kFollowTargetMaxThrottle = 0.8;
        public static final double kFollowTargetExtraWaypointDistance = 30.0;
        public static final double kPathKX = 4.0; // units/s per unit of error
        public static final double kPathLookaheadTime = 0.4; // seconds to look ahead along the path for steering
        public static final double kPathMinLookaheadDistance = 24.0; // inches
    }
}