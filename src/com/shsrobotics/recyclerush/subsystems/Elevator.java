package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.stacks.StackManager;

import edu.wpi.first.wpilibj.command.Subsystem;
import static com.shsrobotics.recyclerush.Hardware.ElevatorHardware.*;

/**
 * The elevator
 */
public class Elevator extends Subsystem {

	static final int clicksOverRange = -465_080;
	static final double lipHeight = 0.05;
	static final double levels = 5.0;
	public static final double TOLERANCE = 0.01;
	static final double MOTOR_SPEED = 0.5;
	static final double MOVING_SPEED_1 = 0.9,// levels/s
						MOVING_SPEED_2 = 1.0,
						MOVING_SPEED_3 = 1.0,
						MOVING_SPEED_4 = 1.0,
						MOVING_SPEED_5 = 1.0,
						MOVING_SPEED_6 = 1.0; 
	static final double MOVING_SPEED_DOWN = 0.5;
	
	
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
		double t;
		if (getPosition() > level) {
			t = distance / MOTOR_SPEED;
		} else {
			t = distance / getMotorSpeedUp();
		}
		return t;
	}
	
    /**
     * resets distance
     */
    public void reset() {
    	encoder.reset();
    }
    
    private void set(double output) {
        elevatorMotorA.set(-output);
        elevatorMotorB.set(-output);
    }
    
    private double getMotorSpeedUp() {
    	double res = MOTOR_SPEED;
    	switch(StackManager.totes) {
    		case 0:
    			res = MOTOR_SPEED;
    			break;
    		case 1:
    			res = MOVING_SPEED_1;
    			break;
    		case 2:
    			res = MOVING_SPEED_2;
    			break;
    		case 3:
    			res = MOVING_SPEED_3;
    			break;
    		case 4:
    			res = MOVING_SPEED_4;
    			break;
    		case 5:
    			res = MOVING_SPEED_5;
    			break;
    		case 6:
    			res = MOVING_SPEED_6;
    			break;
    		default:
    			res = MOTOR_SPEED;	
    	}
    	return res;
    }
    
    public void initDefaultCommand() { }
}
