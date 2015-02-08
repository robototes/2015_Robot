/*
 * TEAM 2412
 *
 * JANUARY 2015
 *
 * FIRST ROBOTICS COMPETITION
 * RECYCLE RUSH
 * SOURCE CODE
 */

package com.shsrobotics.recyclerush;

import com.shsrobotics.library.FRCRobot;
import com.shsrobotics.library.Task;
import com.shsrobotics.library.TaskInterface;
import com.shsrobotics.library.TaskList;
import com.shsrobotics.recyclerush.drivebase.DriveBase;
import com.shsrobotics.recyclerush.dashboard.RobotDashboard;
import com.shsrobotics.recyclerush.auto.*;

public class Robot extends FRCRobot implements Hardware {
	
	TaskList makeStack = new MakeStack();  
	
	@Override
	public void robotInit() {
		super.robotInit();
	}
	
	@Override
	public void autonomousInit() {
		TaskInterface autonomous = null;
		
		switch ((int) dashboard.getNumber(Dashboard.AUTO_MODE, Enums.ROBOT_SET)) {
			case Enums.STACK_SET:
				autonomous = new StackSet();
				break;
			case Enums.ROBOT_SET:
				autonomous = new RobotSet();
				break;
			case Enums.CENTER_CONTAINERS:
				autonomous = new RobotSet();
				break;
			default:
				autonomous = new RobotSet();
				break;
		}
		
		autonomous.start();
	}

	@Override
	public void autonomousPeriodic() {
		
	}
	
	public void teleopInit() {
		
	}
	
	@Override
	public void teleopPeriodic() {
		/*
		 * DRIVE CODE
		 */
		DriveBase.drive();
		
		/*
		 * DASHBOARD CODE
		 */
		RobotDashboard.updateView();
	}
}
