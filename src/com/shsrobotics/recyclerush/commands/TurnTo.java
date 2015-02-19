package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;
import static com.shsrobotics.recyclerush.Hardware.IDriveBase.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Align robot to field
 */
public class TurnTo extends Command {

	double setpoint;
	int count;
	
    public TurnTo(double setpoint) {
    	requires(driveBase);
    	this.setpoint = setpoint;
    }
    
    public TurnTo(double setpoint, double y) {
    	requires(driveBase);
    	this.setpoint = setpoint;
    	alignToFieldPID.setForward(y);
    }

    protected void initialize() {
    	alignToFieldPID.setSetpoint(setpoint);
    	alignToFieldPID.enable();
    	count = 0;
    }

    protected void execute() { }

    protected boolean isFinished() {
    	if (alignToFieldPID.onTarget()) count++;
        return count >= 5;
    }

    protected void end() {
    	alignToFieldPID.disable();
    }

    protected void interrupted() {
    	end();
    }
}
