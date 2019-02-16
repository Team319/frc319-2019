package org.usfirst.frc.team319.robot;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.team254.lib.trajectory.WaypointSequence.Waypoint;
import com.team319.trajectory.AbstractBobPathCreator;
import com.team319.trajectory.BobPath;
import com.team319.trajectory.SrxTranslatorConfig;

public class BobPathCreator extends AbstractBobPathCreator {

	//private static double robotWidthInFeet = 24.5 / 12.0;
	
	private static double robotLengthInFeet = 30.0 / 12.0;

	private static Waypoint startingPoint = new Waypoint(robotLengthInFeet / 2.0, 45.5 / 12.0, 0, 0, 0);

	private SrxTranslatorConfig config = new SrxTranslatorConfig();

	public static void main(String[] args) {
		new BobPathCreator().generatePaths();
	}

	private BobPathCreator() {
		config.max_acc = 8.0; // Maximum acceleration in FPS
		config.max_vel = 10.0; // Maximum velocity in FPS
		config.wheel_dia_inches = 5.0;
		config.scale_factor = 1.0; // Used to adjust for a gear ratio and or distance tuning
		config.encoder_ticks_per_rev = 4096; // Count of ticks on your encoder
		config.robotLength = 30.0; // Robot length in inches, used for drawing the robot
		config.robotWidth = 24.5; // Robot width in inches, used for drawing the robot
	}

	@Override
	protected List<BobPath> getArcs() {
		List<BobPath> paths = new ArrayList<>();
		paths.addAll(getConfigArcs());
		paths.addAll(generateTeamArcs());
		return paths;
	}

	/**
	 * Use this method to generate team paths. You can create more methods like this
	 * one to organize your path, just make sure to add the method call to the
	 * returned list in getArcs()
	 * 
	 * @return the list of team paths to generate
	 */
	private List<BobPath> generateTeamArcs() {
		BobPath DriveForwardThreeFeet = new BobPath(config, "DriveForwardThreeFeet", true);
		DriveForwardThreeFeet.addWaypoint(startingPoint);
		DriveForwardThreeFeet.addWaypointRelative(3, 0, 0);

		return asList(DriveForwardThreeFeet); // return asList(path1, path2, path3, ...);
	}

	/**
	 * Generate the configuration arcs, distance, turning, and speed DistanceScaling
	 * - This path will run 3 feet forward and stop. To tune this adjust the scaling
	 * factor until the robot stops at exactly 3 feet. TurnScaling - This path will
	 * run 3 feet forward and 3 feet to the left, this will end at 90 degrees. This
	 * path can be used when tuning your heading loop for arc mode. SpeedTesting -
	 * This path will drive 3 feet forward and 3 feet to the left at 3 FPS, then
	 * drive another 3 feed forward and 3 feet to the left. This path will end with
	 * the robot 6 feet to the left of it's starting position facing the oppostite
	 * direction.
	 */
	private List<BobPath> getConfigArcs() {
		
		BobPath distanceScaling = new BobPath(config, "DistanceScaling", true);
		distanceScaling.addWaypoint(2, 13.5, 0, 0, 0);
		distanceScaling.addWaypointRelative(3, 0, 0, 0, 3);

		BobPath turnScaling = new BobPath(config, "TurnScaling");
		turnScaling.addWaypoint(new Waypoint(2, 13.5, 0, 0, 0));
		turnScaling.addWaypointRelative(3, 3, 89.99, 0, 3);

		BobPath speedTesting = new BobPath(config, "SpeedTesting");
		speedTesting.addWaypoint(new Waypoint(2, 13.5, 0, 0, 0));
		speedTesting.addWaypointRelative(3, 3, 89.99, 1, 3);
		speedTesting.addWaypointRelative(-3, 3, 89.99, 0, 1);

		return asList(distanceScaling, turnScaling, speedTesting);
	}
}