package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;
import com.shsrobotics.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets elevator to specific height
 */
public class SetElevator extends Command {

	double level;
	int count;
	
	static final double TIMEOUT_FACTOR = 1.5;
	
    public SetElevator(double elevatorLevel) {
    	this.level = elevatorLevel;
    	requires(elevator);
//    	setTimeout(TIMEOUT_FACTOR * elevator.getExpectedTime(elevatorLevel)); 
    }

    protected void initialize() {
    	count = 0;
    	elevator.setManual(false);
    }

    protected void execute() {
    	if (elevator.getPosition() > level) {
    		elevator.down();
    	} else {
    		elevator.up();
    	}
    }

    protected boolean isFinished() {
        if (Math.abs(level - elevator.getPosition()) < Elevator.TOLERANCE) {
        	count++;
        }
        return count > 0 || elevator.isManual();
    }

    protected void end() {
    	Elevator.LEVEL = level;
    	elevator.stop();
    	elevator.setManual(true);
    }

    protected void interrupted() {
    	elevator.stop();
    }
}
