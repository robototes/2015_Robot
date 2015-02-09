package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets elevator to specific height
 */
public class SetElevator extends Command implements Hardware {

	double level;
	
    public SetElevator(double elevatorLevel) {
    	this.level = elevatorLevel;
    	requires(elevator);
    }

    protected void initialize() {
    	elevator.raiseTo(level);
    }

    protected void execute() { }

    protected boolean isFinished() {
        return elevator.isAtSetLevel();
    }

    protected void end() {
    	Elevator.LEVEL = (int) level;
    }

    protected void interrupted() {
    	elevator.stop();
    }
}
