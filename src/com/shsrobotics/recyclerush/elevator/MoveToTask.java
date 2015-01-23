package com.shsrobotics.recyclerush.elevator;

import com.shsrobotics.library.Task;

public class MoveToTask extends Task {
	
	Elevator elev;
	boolean done = false;
	public MoveToTask(Elevator e) {
		elev = e;
	}
	@Override
	protected void initialize() {
		
	}
	@Override
	protected void execute() {
		if ( Math.abs(elev.pos.get()-elev.setpoint) < elev.tolerance ) {
			elev.winch.set(0);
			done = true;
		}
		else if ( elev.pos.get() < elev.setpoint && !elev.isTop.get() ) {
			elev.winch.set(1);
		}
		else if ( elev.pos.get() > elev.setpoint && !elev.isBottom.get() ) {
			elev.winch.set(-1);
		}
		else {
			elev.winch.set(0);
			done = true;
		}
	}
	@Override
	protected boolean isFinished() {
		return done;
	}
	@Override
	protected void end() {
		elev.winch.set(0);
	}
}
