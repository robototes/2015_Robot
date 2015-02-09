package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves object out of robot
 */
public class RollersOut extends Command implements Hardware {

    public RollersOut() {
        requires(rollerIntake);
        
        setTimeout(RollerIntake.TIME_TO_OUT);
    }

    protected void initialize() {
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
