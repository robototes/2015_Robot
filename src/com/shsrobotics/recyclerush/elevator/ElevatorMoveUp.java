package com.shsrobotics.recyclerush.elevator;

import com.shsrobotics.library.Task;

public class ElevatorMoveUp extends Task {
	private Elevator elev;
	boolean done = false;
	public ElevatorMoveUp(Elevator e) {
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
		else if ( elev.isTop.get() ) {
			elev.winch.set(0);
			done = true;
		}
		else {
			elev.winch.set(1);
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
