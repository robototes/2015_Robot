package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Releases stack in possesion
 */
public class Release extends CommandGroup {
    
    public  Release() {
        addSequential(new SetElevator(0.16 + Elevator.LEVEL)); // release at level of scoring platform
        addParallel(new RollersOut());
        addSequential(new OpenGripper());
    }
}
