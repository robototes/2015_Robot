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
import com.shsrobotics.recyclerush.elavator.Elevator;

import edu.wpi.first.wpilibj.Victor;

public class Robot extends FRCRobot implements Hardware {
	
	public Elevator elevator;
	
	
	@Override
	public void robotInit() {
		
	}
	
	@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {
		
	}
	
	public void teleopInit() {
	}
	
	@Override
	public void teleopPeriodic() {
		double y = joystick.getY();
		
		if(y > .5){
			claw_Solenoid.both.set(EXTENDED);
		}else{
			claw_Solenoid.both.set(RETRACTED);
		}
	}
}
