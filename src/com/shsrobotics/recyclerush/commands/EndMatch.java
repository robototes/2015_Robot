package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.Maps.Buttons;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Moves systems inside the transport configuration for the end of the match
 */
public class EndMatch extends CommandGroup {
    
    public  EndMatch() {
    	addParallel(new ClawUp());
    	if (!Buttons.disableEndMatchElevatorMotion.held()) {
    		addSequential(new SetElevator(1.0));
    	}
    }
}
