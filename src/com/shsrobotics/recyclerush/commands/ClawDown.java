package com.shsrobotics.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;

import static com.shsrobotics.recyclerush.Hardware.*;

/**
 *
 */
public class ClawDown extends Command {

    public ClawDown() {
    	requires(claw);
    }

    protected void initialize() {
    	claw.down();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return claw.isDown();
    }

    protected void end() {
    	claw.stop();
    }

    protected void interrupted() {
    	end();
    }
}
