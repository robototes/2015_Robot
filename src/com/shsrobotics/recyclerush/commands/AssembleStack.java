package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;

import com.shsrobotics.recyclerush.stacks.StackManager;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.Gripper;
import com.shsrobotics.recyclerush.subsystems.Gripper.GripperState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Automatically manages the stack
 */
public class AssembleStack extends CommandGroup {
	
	public double baseline;
	
    public AssembleStack() {
    	baseline = elevator.getPosition();
    	
    	if (StackManager.getObjects() > 0) { // already have something in possession
    		addSequential(new SetElevator(1 + baseline));
    		addSequential(new WaitCommand(0.2));
    		addSequential(new OpenGripper());
    	}
		addSequential(new SetElevator(0 + baseline));
		addSequential(new CloseGripper());
		addSequential(new SetElevator(1.2 + baseline));
    }

}
