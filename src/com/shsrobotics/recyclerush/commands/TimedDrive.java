package com.shsrobotics.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;

import static com.shsrobotics.recyclerush.Hardware.*;

/**
 *
 */
public class TimedDrive extends Command {

	double x, y, z;
	
	/**
	 * Drive in a direction for a time
	 * @param x
	 * @param y
	 * @param z
	 * @param time in seconds
	 */
    public TimedDrive(double x, double y, double z, double time) {
    	requires(driveBase);
    	
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	setTimeout(time);
    }

    protected void initialize() { }

    protected void execute() {
    	driveBase.drive(x, y, z);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	driveBase.drive(0, 0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
