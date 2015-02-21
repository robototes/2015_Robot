package com.shsrobotics.recyclerush.subsystems;

import static com.shsrobotics.recyclerush.Hardware.IRollerIntake.*;

import com.shsrobotics.recyclerush.stacks.StackManager;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * ROLLER INTAKE
 */
public class RollerIntake extends Subsystem {

	/**
	 * Seconds for rollers to move object out of robot
	 */
	public static final double TIME_TO_OUT = 4; 
	
	// roller speed for normal operation
	static final double ROLLER_SPEED_IN = 1.0;
	static final double ROLLER_SPEED_KEEP_IN = 0.35;
	static final double ROLLER_SPEED_OUT = -0.75;
	static final double ROLLER_SPEED_OUT_ONE_OBJ = -0.35;
	// less than this ~= 0
	static final double MIN_SPEED = 0.1;
	
	boolean manual = false;
	
	/**
	 * Bring game objects into the robot
	 */
	public void in() {
		setSpeed(ROLLER_SPEED_IN);
	}
	
	/**
	 * Keep objects in robot
	 */
	public void slowIn() {
		setSpeed(ROLLER_SPEED_KEEP_IN);
	}
	
	/**
	 * Push game objects out of the robot
	 */
	public void out() {
		if (StackManager.getObjects() == 1) {
			setSpeed(ROLLER_SPEED_OUT_ONE_OBJ);
		} else {
			setSpeed(ROLLER_SPEED_OUT);
		}
	}
	
	/**
	 * Stops rollers
	 */
	public void stop() {
		setSpeed(0.0);
	}
	
	/**
	 * Returns whether a tote is in
	 * @return true if yes
	 */
	public boolean isToteIn() {
		return lightToteStop.get();
	}
	
	/**
	 * Returns whether an RC is in
	 * @return true if yes
	 */
	public boolean isRCIn() {
		return isRC.get();
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
		if (dashboard.disableRollers()) return;
		leftRoller.set(-speed);
		rightRoller.set(speed);
	}
	
	/**
	 * Set manual control
	 * @param manual true if yes
	 */
	public void setManual(boolean manual) {
		this.manual = manual;
	}
	
	/**
	 * Return control state
	 * @return
	 */
	public boolean isManual() {
		return manual;
	}

    public void initDefaultCommand() { }
    
    public enum RollerState {
    	IN, OUT, STOPPED, ERROR;
    }
}
