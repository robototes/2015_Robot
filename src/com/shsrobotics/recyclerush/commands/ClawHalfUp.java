package com.shsrobotics.recyclerush.commands;

import static com.shsrobotics.recyclerush.Hardware.claw;

import com.shsrobotics.recyclerush.subsystems.RCClaw;

import edu.wpi.first.wpilibj.command.Command;

public class ClawHalfUp extends Command {

	public ClawHalfUp() {
    	requires(claw);
    	setTimeout(RCClaw.HALF_UP_TIME);
    }
	
	@Override
	protected void initialize() {
		claw.up();
	}

	@Override
	protected void execute() { }

	@Override
	protected boolean isFinished() {
		return isTimedOut() || claw.isUp();
	}

	@Override
	protected void end() {
		claw.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
