package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot to a specific location
 */
public class DriveTo extends Command {

	double x, y, z;
	
    public DriveTo(double x, double y, double z) {
    	requires(driveBase);
    	
    	this.x = x;
    	this.y = y;
    	this.z = z;
    }

    protected void initialize() {
    	driveBase.driveTo(x, y, z);
    }

    protected void execute() { }

    protected boolean isFinished() {
        return driveBase.isOnTarget();
    }

    protected void end() {
    	driveBase.drive(0, 0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
