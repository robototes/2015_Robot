package com.shsrobotics.recyclerush.actions;

import com.shsrobotics.library.Task;
import com.shsrobotics.recyclerush.Hardware;

public class AutoIntake extends Task implements Hardware {

	public static final int 
		START = 0,
		ROLLERS_IN = 5,
		STOP_ROLLERS = 10,
		CLOSE_GRIPPER = 15,
		END = 100;
	
	int state;
	
	@Override
	protected void initialize() {
		state = START;
	}
	
	@Override
	protected void execute() {
		switch (state) {
			case START:
				state = ROLLERS_IN;
				break;
			case ROLLERS_IN:
				Subsystems.rollers.setInput();
				if (LimitSwitches.gripperToteStop.get()) state = STOP_ROLLERS;
				break;
			case STOP_ROLLERS:
				Subsystems.rollers.setStop();
				state = CLOSE_GRIPPER;
				break;
			case CLOSE_GRIPPER:
				Subsystems.gripper.manualClose();
				if (LimitSwitches.gripperInnerLimit.get()) state = END;
			default: 
				state = END;
				break;
		}
	}

	@Override
	protected boolean isFinished() {
		return state >= END;
	}

	@Override
	protected void end() { }
}
