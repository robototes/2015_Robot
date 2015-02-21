package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.stacks.StackManager;

import edu.wpi.first.wpilibj.command.Subsystem;
import static com.shsrobotics.recyclerush.Hardware.ElevatorHardware.*;
import static com.shsrobotics.recyclerush.Hardware.*;

/**
 * The elevator
 */
public class Elevator extends Subsystem {

	static final int clicksOverRange = -37_910 / 4;
	static final double lipHeight = 0.05;
	static final double levels = 4.0;
	public static final double TOLERANCE = 0.02;
	static final double MOTOR_SPEED = 1.0;
	static final double SPEED = 0.7; // levels/s
	
	
	boolean manual = false;
	
	public static double LEVEL;
	
    public Elevator() {
    	encoder.setDistancePerPulse(levels / clicksOverRange);
    }
    
    /**
     * Move up
     */
    public void up() {
    	if (!isAtTop()) { 
    		set(MOTOR_SPEED);
    	} else {
    		set(0.0);
    	}
    }
    
    /**
     * Move down
     */
    public void down() {
    	if (!isAtBottom()) { 
    		set(-MOTOR_SPEED);
    	} else {
    		set(0.0);
    	}
    }
    
    /**
     * Returns whether in manual control mode
     * @return true if yes
     */
    public boolean isManual() {
    	return manual;
    }
    
    /**
     * Set whether under manual control
     * @param manual true if yes
     */
    public void setManual(boolean manual) {
    	this.manual = manual;
    }

  	/**
	 * Stop motor
	 */
	public void stop() {
		set(0.0);
	}
	
	/**
	 * Get elevator position
	 * @return position, in tote units
	 */
	public double getPosition() {
		return encoder.getDistance();
	}
    
	/**
	 * Returns position
	 * @return true if at very bottom
	 */
	public boolean isAtBottom() { 
		return lowerLimit.get();
	}
		
	/**
	 * Returns position
	 * @return true if at very top
	 */
	public boolean isAtTop() { 
		return upperLimit.get();
	}
	
	/**
	 * Calculate expected time to move
	 * @param level the level to move to
	 * @return
	 */
	public double getExpectedTime(double level) {
		double distance = Math.abs(getPosition() - level);
		double t = distance / SPEED;
		return t;
	}
	
    /**
     * resets distance
     */
    public void reset() {
    	encoder.reset();
    }
    
    private void set(double output) {
    	if (dashboard.disableElevator()) return;
        elevatorMotorA.set(-output);
        elevatorMotorB.set(-output);
    }
    
    public void initDefaultCommand() { }
}
