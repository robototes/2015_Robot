package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens Gripper
 */
public class OpenGripper extends Command {

    public OpenGripper() {
    	requires(gripper);
    	setTimeout(5.0);
    }

    protected void initialize() {
    	gripper.open();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return gripper.getOuterLimit() || isTimedOut();
    }

    protected void end() {
    	gripper.stop();
	}

    protected void interrupted() {
    	end();
    }
}
