package com.shsrobotics.recyclerush.gripper;

import com.shsrobotics.library.Task;

public class CloseGripperTask extends Task {
	
	boolean done = false;
	Gripper g;
	
	public CloseGripperTask(Gripper grip) {
		g = grip;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if ( g.innerLimit.get() ) {
			done = true;
			g.motor.set(0);
		}
		else if ( !g.limiter.get() ){
			g.motor.set(1);
		}
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

	@Override
	protected void end() {
		
	}

}
