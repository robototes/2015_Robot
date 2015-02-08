package com.shsrobotics.recyclerush.auto.robotset;

import com.shsrobotics.library.Task;
import com.shsrobotics.library.TaskList;
import com.shsrobotics.recyclerush.drive.DriveBase;

public class RobotSet extends TaskList {
	
	DriveBase drive;
	
	public RobotSet(DriveBase drive) {
		this.drive = drive;
	}
	
	@Override
	protected void runTasks() {
		runSequential();
		
		try {
			begin(new Task() {
				long startTime;
				boolean done = false;
				@Override
				protected boolean isFinished() {
					return done;
				}
				
				@Override
				protected void initialize() {
					startTime = System.currentTimeMillis();
				}
				
				@Override
				protected void execute() {
					if ( System.currentTimeMillis() - startTime >= 3000 ) {
						drive.driveForward(0);
						done = true;
					}
					else {
						drive.driveForward(.6);
					}
				}
				
				@Override
				protected void end() {
					drive.driveForward(0);
				}
			});
		} catch (InterruptedException e) {
			
		}
		
	}

}
