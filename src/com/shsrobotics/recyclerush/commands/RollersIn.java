package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Brings object into robot
 */
public class RollersIn extends Command implements Hardware {

    public RollersIn() {
        requires(rollerIntake);
    }

    protected void initialize() {
    	rollerIntake.in();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return rollerIntake.isObjectIn();
    }

    protected void end() {
    	rollerIntake.stop();
    }

    protected void interrupted() {
    	end();
    }
}
