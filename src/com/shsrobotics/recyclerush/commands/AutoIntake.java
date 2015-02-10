package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.stacks.StackManager;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.Gripper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Automatically intakes totes and RCs and manages the stack
 */
public class AutoIntake extends CommandGroup {
    
	double ROLLER_ELEV_DELAY = 0.1; // seconds after elevator starts moving before rollers can begin turning
	double ROLLER_GRIP_DELAY = 0.4; // seconds after gripper starts moving before rollers can begin turning
	
    public AutoIntake() {
    	if (StackManager.getObjects() > 0) { // already have something in possession
    		if (Elevator.LEVEL != 1) {
    			addParallel(new SetElevator(1));
        		addSequential(new DelayedCommand(new RollersIn(), ROLLER_ELEV_DELAY));
    		} else {
    			addSequential(new RollersIn());
    		}
    		addSequential(new OpenGripper());
    		addSequential(new SetElevator(0));
    	} else { // nothing in possession
    		addParallel(new SetElevator(0));
    		if (Gripper.OPEN) {
    			addSequential(new RollersIn());
    		} else {
    			addParallel(new OpenGripper());
    			addSequential(new DelayedCommand(new RollersIn(), ROLLER_GRIP_DELAY));
    		}
    	}
    	addSequential(new CloseGripper());
		addSequential(new SetElevator(1));
		
		for (int i = 0; i < 5; i++) {
			addSequential(new RollersIn());
			addSequential(new OpenGripper());
    		addSequential(new SetElevator(0));
    		addSequential(new CloseGripper());
    		addSequential(new SetElevator(1));
		}
    }
    
}
