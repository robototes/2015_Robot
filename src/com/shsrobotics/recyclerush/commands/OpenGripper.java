package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens Gripper
 */
public class OpenGripper extends Command implements Hardware {

    public OpenGripper() {
    	requires(gripper);
    }

    protected void initialize() {
    	gripper.open();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return gripper.getOuterLimit();
    }

    protected void end() {
    	gripper.stop();
    	gripper.OPEN = true;
	}

    protected void interrupted() {
    	end();
    }
}
