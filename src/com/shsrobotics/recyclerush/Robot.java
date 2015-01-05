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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.shsrobotics.library.FRCRobot;

public class Robot extends FRCRobot implements Hardware {
	@Override
	public void robotInit() {
		super.robotInit();
	}
	
	@Override
	public void autonomousInit() {
		switch ((int) SmartDashboard.getNumber(Dashboard.AUTO_MODE, Enums.ROBOT_SET)) {
			case Enums.CONTAINER_STACK_SET:
				break;
			case Enums.ROBOT_SET:
				break;
			default:
				break;
		}
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
