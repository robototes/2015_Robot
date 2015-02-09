package com.shsrobotics.recyclerush.commands;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Cancels the auto-intake
 */
public class CancelAutoIntake extends Command implements Hardware {

    public CancelAutoIntake() {
        requires(rollerIntake);
        requires(gripper);
        requires(elevator);
        
        setInterruptible(false);
        
    }

    protected void initialize() { }

    protected void execute() { }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	rollerIntake.stop();
    	gripper.stop();
    	elevator.stop();
    }

    protected void interrupted() { }
}
