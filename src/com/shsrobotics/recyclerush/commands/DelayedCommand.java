package com.shsrobotics.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Schedules a command to be executed in a certain amount of time
 */
public class DelayedCommand extends CommandGroup {
    
    public  DelayedCommand(Command c, double timeout) {
        addSequential(new WaitCommand(timeout));
        addSequential(c);
    }
}
