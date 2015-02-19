package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;
import com.shsrobotics.recyclerush.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Brings object into robot
 */
public class RollersIn extends Command {

	int count;
	
    public RollersIn() {
        requires(rollerIntake);
    }

    protected void initialize() {
    	rollerIntake.setManual(false);
    	rollerIntake.in();
    	count = 0;
    }

    protected void execute() { }

    protected boolean isFinished() {
        if (rollerIntake.isToteIn() || rollerIntake.isRCIn()) count++;
        return count >= 3;
    }

    protected void end() {
    	rollerIntake.stop();
    }

    protected void interrupted() {
    	end();
    }
}
