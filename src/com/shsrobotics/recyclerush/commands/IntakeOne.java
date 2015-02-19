package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;
import com.shsrobotics.recyclerush.stacks.StackManager;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.Gripper;
import com.shsrobotics.recyclerush.subsystems.Gripper.GripperState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Automatically intakes one tote
 */
public class IntakeOne extends CommandGroup {
    
    public IntakeOne() {
    	if (StackManager.getObjects() == 0) {
    		addSequential(new OpenGripper());
    	}
    	addSequential(new RollersIn());
    }
    
}
