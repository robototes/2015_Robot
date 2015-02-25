package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;

import com.shsrobotics.recyclerush.stacks.StackManager;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.Gripper;
import com.shsrobotics.recyclerush.subsystems.Gripper.GripperState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Automatically intakes totes and RCs and manages the stack
 */
public class AutoIntake extends CommandGroup {
    
	double ROLLER_ELEV_DELAY = 0.1; // seconds after elevator starts moving before rollers can begin turning
	double ROLLER_GRIP_DELAY = 0.4; // seconds after gripper starts moving before rollers can begin turning
	
    public AutoIntake() {
    	if (StackManager.getObjects() > 0) { // already have something in possession
    		if (Math.abs(Elevator.LEVEL - 1.2) > Elevator.TOLERANCE) { // not at right height
    			addParallel(new SetElevator(1.2));
        		addSequential(new DelayedCommand(new RollersIn(), ROLLER_ELEV_DELAY));
    		} else {
    			addSequential(new RollersIn());
    		}
    		addSequential(new SetElevator(1));
    		addSequential(new WaitCommand(0.2));
    		addSequential(new OpenGripper());
    		addSequential(new SetElevator(0));
    	} else { // nothing in possession
    		addParallel(new SetElevator(0));
    		if (gripper.getState() == GripperState.OPEN) {
    			addSequential(new RollersIn());
    		} else {
    			addParallel(new OpenGripper());
    			addSequential(new DelayedCommand(new RollersIn(), ROLLER_GRIP_DELAY));
    		}
    	}
    	addSequential(new CloseGripper());
		addSequential(new SetElevator(1.2));
		
		for (int i = 0; i < 5; i++) {
			addSequential(new RollersIn());
			addSequential(new SetElevator(1));
			addSequential(new WaitCommand(0.2));
			addSequential(new OpenGripper());
    		addSequential(new SetElevator(0));
    		addSequential(new CloseGripper());
    		addSequential(new SetElevator(1.2));
		}
    }
    
    public void end() {
    	new SetElevator(0.16).start();
    }
    
    public void interrupted() {
    	end();
    }
    
}
