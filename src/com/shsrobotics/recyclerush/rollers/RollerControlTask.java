package com.shsrobotics.recyclerush.rollers;

import com.shsrobotics.library.SubsystemState;
import com.shsrobotics.library.Task;

public class RollerControlTask extends Task {
	protected double leftSpeed, rightSpeed;
	protected Rollers rollers;
	protected final Object lock = new Object();
	
	public RollerControlTask(Rollers r) {
		rollers = r;
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		if ( rollers.getState() == SubsystemState.ESTOP ) {
			return;
		}
		synchronized(lock) {
			rollers.left.set(leftSpeed);
			rollers.right.set(rightSpeed);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return rollers.getState() == SubsystemState.ESTOP;
	}
	
	@Override
	protected void end() {
		rollers.left.set(0);
		rollers.right.set(0);
	}
	
	
	
}
