package com.shsrobotics.recyclerush.elevator;

import com.shsrobotics.library.Task;

public class ElevatorMoveDown extends Task {
	private Elevator elev;
	boolean done = false;
	public ElevatorMoveDown(Elevator e) {
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
		else if ( elev.isBottom.get() ) {
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
