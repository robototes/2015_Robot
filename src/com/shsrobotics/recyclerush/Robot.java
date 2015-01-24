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
import com.shsrobotics.library.fieldpositioning.PID2D;
import com.shsrobotics.library.fieldpositioning.RobotPosition;
import com.shsrobotics.library.util.functions.UpdateFunction;
import com.shsrobotics.recyclerush.elevator.Elevator;
import com.shsrobotics.recyclerush.gripper.Gripper;
import com.shsrobotics.recyclerush.rcclaw.RCClaw;
import com.shsrobotics.recyclerush.rollers.Rollers;

public class Robot extends FRCRobot implements Hardware, RobotPosition {
	
	PID2D drivePID;
	Elevator elevator;
	Gripper gripper;
	Rollers rollers;
	RCClaw rcClaw;
	
	@Override
	public void robotInit() {
		drivePID = new PID2D(null,this);
		// init subsystems
	}
	
	@Override
	public void autonomousInit() {
		
	}
	
	@Override
	public void autonomousPeriodic() {
		UpdateFunction.setActive();
	}
	
	public void teleopInit() {
		
	}
	
	@Override
	public void teleopPeriodic() {
		UpdateFunction.setActive();
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		return 0;
	}
	
	@Override
	public double getCollisionRadius() {
		return 36;
	}
	
	@Override
	public PID2D getPID() {
		return drivePID;
	}

	@Override
	public double getHeading() {
		// TODO Auto-generated method stub
		return 0;
	}
}
