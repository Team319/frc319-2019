package org.usfirst.frc.team319.models;

import java.util.ArrayList;
import java.util.List;

/**
 * BobLeaderSmartMotorController
 */
public class LeaderBobSmartMotorController implements IBobSmartMotorController {
    private final IBobSmartMotorController leader;
	private final List<IBobSmartMotorController> followerList;

    public LeaderBobSmartMotorController(IBobSmartMotorController leader, IBobSmartMotorController... followers) {
        followerList = new ArrayList<IBobSmartMotorController>();

        this.leader = leader;

        for(IBobSmartMotorController follower : followers){
            follower.follow(leader);
            followerList.add(follower);
        }
    }

    @Override
    public void setPercentOutput(double percentOutput) {
        leader.setPercentOutput(percentOutput);
    }

    @Override
    public double getPosition() {
        return leader.getPosition();
    }

    @Override
    public double getVelocity() {
        return leader.getVelocity();
    }

    @Override
    public void resetToFactoryDefaults() {
        leader.resetToFactoryDefaults();
    }

    @Override
    public void follow(IBobSmartMotorController leader) {
        leader.follow(leader);
    }

    @Override
    public void setInverted(boolean inverted) {
        leader.setInverted(inverted);
		for (IBobSmartMotorController follower : followerList) {
			follower.setInverted(inverted);
		}
    }

    @Override
    public void setPosition(double position) {
        leader.setPosition(position);
    }

    @Override
    public double getOutputVoltage() {
        return leader.getOutputVoltage();
    }

    @Override
    public void setBrakeMode(boolean brakeModeEnabled) {
        leader.setBrakeMode(brakeModeEnabled);
        for (IBobSmartMotorController follower : followerList) {
            follower.setBrakeMode(brakeModeEnabled);
        }
    }

    
}