package com.shsrobotics.recyclerush.elevator;

import com.shsrobotics.library.Task;

public class ElevatorResetTask extends Task {
	Elevator elev;
	private boolean done = false;
	public ElevatorResetTask(Elevator e) {
		elev = e;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if ( elev.isBottom.get() ) {
			elev.winch.set(0);
			elev.pos.reset();
			done = true;
		}
		else {
			elev.winch.set(-1);
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
