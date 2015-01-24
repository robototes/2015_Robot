package com.shsrobotics.recyclerush.rcclaw;

import com.shsrobotics.library.Task;

public class ArmMoveTask extends Task {
	
	boolean done = false;
	RCClaw claw;
	
	public ArmMoveTask(RCClaw claw) {
		this.claw = claw;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if ( Math.abs(claw.setpoint - claw.pot.get() ) < claw.tolerance ) {
			claw.motor.set(0);
			done = true;
		}
		if ( claw.setpoint < claw.pot.get() ) {
			claw.motor.set(1);
		}
		else if ( claw.setpoint > claw.pot.get() )  {
			claw.motor.set(-1);
		}
		else {
			claw.motor.set(0);
			done = true;
		}
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

}
