package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class KeepIn extends Command {

    public KeepIn() {
    	requires(rollerIntake);
    }

    protected void initialize() {
    	rollerIntake.slowIn();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	rollerIntake.stop();
    }

    protected void interrupted() {
    	end();
    }
}
