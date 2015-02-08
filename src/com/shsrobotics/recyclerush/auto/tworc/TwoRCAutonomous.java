package com.shsrobotics.recyclerush.auto.tworc;

import com.shsrobotics.library.TaskList;
import com.shsrobotics.library.fieldpositioning.RobotPosition;
import com.shsrobotics.recyclerush.rcclaw.RCClaw;

public class TwoRCAutonomous extends TaskList {
	
	RobotPosition robot;
	RCClaw claw;
	
	public TwoRCAutonomous() {
		this.runSequential();
	}
	
	/**
	 * 0	reposition
	 * 1	raise arm (how long, how high? until triggered?)
	 * 2	move as turn, maby straif at same time so tote in auto zone
	 * 3	drop arm
	 * 4	reposition for next rc.
	 * 5	raise arm
	 * 6	move forward. make sure rc in zone.
	 * 7	drop arm? check rc set rules
	 */
	@Override
	protected void runTasks() {
		runSequential();
		
	}

}
