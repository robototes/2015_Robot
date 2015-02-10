package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * ROLLER INTAKE
 */
public class RollerIntake extends Subsystem implements Hardware.IRollerIntake {

	/**
	 * Seconds for rollers to move object out of robot
	 */
	public static final double TIME_TO_OUT = 1.5; 
	
	// roller speed for normal operation
	static final double ROLLER_SPEED = 0.95;
	// less than this ~= 0
	static final double MIN_SPEED = 0.1;
	
	/**
	 * Bring game objects into the robot
	 */
	public void in() {
		setSpeed(ROLLER_SPEED);
	}
	
	/**
	 * Push game objects out of the robot
	 */
	public void out() {
		setSpeed(-ROLLER_SPEED);
	}
	
	/**
	 * Stops rollers
	 */
	public void stop() {
		setSpeed(0.0);
	}
	
	/**
	 * Returns whether an object is in
	 * @return true if yes
	 */
	public boolean isObjectIn() {
		return !toteStop.get();
	}
	
	/**
	 * Get state of system
	 * @return state enum
	 */
	public RollerState getState() {
		double value = rightRoller.get();
		RollerState r = RollerState.STOPPED; 
		if (value > MIN_SPEED) {
			r = RollerState.IN;
		} else if (value < -MIN_SPEED) {
			r = RollerState.OUT;
		}
		return r;
	}
	
	/**
	 * Set motor speeds
	 * @param speed
	 */
	private void setSpeed(double speed) {
		leftRoller.set(-speed);
		rightRoller.set(speed);
	}

    public void initDefaultCommand() { }
    
    public enum RollerState {
    	IN, OUT, STOPPED, ERROR;
    }
}

