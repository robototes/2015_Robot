package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.*;
import com.shsrobotics.recyclerush.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Brings object into robot
 */
public class RollersIn extends Command {

    public RollersIn() {
        requires(rollerIntake);
    }

    protected void initialize() {
    	rollerIntake.setManual(false);
    	rollerIntake.in();
    }

    protected void execute() { }

    protected boolean isFinished() {
        return rollerIntake.isToteIn();
    }

    protected void end() {
    	rollerIntake.stop();
    }

    protected void interrupted() {
    	end();
    }
}
