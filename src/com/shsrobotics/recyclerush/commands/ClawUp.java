package com.shsrobotics.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;

import static com.shsrobotics.recyclerush.Hardware.*;

/**
 *
 */
public class ClawUp extends Command {

    public ClawUp() {
    	requires(claw);
    }

    protected void initialize() {
    	claw.up();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return claw.isUp();
    }

    protected void end() {
    	claw.stop();
    }

    protected void interrupted() {
    	end();
    }
}
