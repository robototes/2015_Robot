package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;

import com.shsrobotics.recyclerush.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves object out of robot
 */
public class RollersOut extends Command {

    public RollersOut() {
        requires(rollerIntake);
        
        setTimeout(RollerIntake.TIME_TO_OUT);
    }

    protected void initialize() {
    	rollerIntake.setManual(false);
    	rollerIntake.out();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	rollerIntake.stop();
    }

    protected void interrupted() {
    	end();
    }
}
