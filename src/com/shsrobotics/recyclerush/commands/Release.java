package com.shsrobotics.recyclerush.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Releases stack in possesion
 */
public class Release extends CommandGroup {
    
    public  Release() {
        addSequential(new SetElevator(0));
        addSequential(new OpenGripper());
    }
}
