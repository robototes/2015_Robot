package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Closes Gripper
 */
public class CloseGripper extends Command {

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
	}

    protected void interrupted() {
    	end();
    }
}
