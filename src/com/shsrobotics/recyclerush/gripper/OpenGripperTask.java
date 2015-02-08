package com.shsrobotics.recyclerush.gripper;

import com.shsrobotics.library.Task;

public class OpenGripperTask extends Task {
	
	boolean done = false;
	Gripper g;
	
	public OpenGripperTask(Gripper grip) {
		g = grip;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if ( g.outerLimit.get() ) {
			done = true;
			g.motor.set(0);
		}
		else if ( !g.innerLimit.get() ){
			g.motor.set(-.5);
		}
	}

	@Override
	protected boolean isFinished() {
		boolean t = g.pdp.getCurrent(g.pdpChannel) > GripperConstants.CAPTURE_CURRENT_DRAW;
		
		return done || t;
	}

	@Override
	protected void end() {
		
	}

}
