package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes Gripper
 */
public class CloseGripper extends Command implements Hardware {

    public CloseGripper() {
    	requires(gripper);
    }

    protected void initialize() {
    	gripper.close();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return gripper.getInnerLimit();
    }

    protected void end() {
    	gripper.stop();
    	gripper.OPEN = false;
	}

    protected void interrupted() {
    	end();
    }
}
