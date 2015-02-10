package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The gripper on the elevator
 */
public class Gripper extends Subsystem implements Hardware.IGripper, Hardware.IDashboard {
    
	// normal motor operation speed
    static final double MOTOR_SPEED = 0.8;
    // current threshold for motor stop
    static final double CURRENT_THRESHOLD = 32;
    
    /**
     * Open the gripper
     */
    public void open() {
    	gripperMotor.set(MOTOR_SPEED);
    }
    
    /**
     * Close the gripper
     */
    public void close() {
    	gripperMotor.set(-MOTOR_SPEED);
    }
    
    /**
     * Stop moving the gripper
     */
    public void stop() {
    	gripperMotor.set(0.0);
    }
    
    /**
     * Get state of gripper
     * @return true if at inner limit
     */
    public boolean getInnerLimit() {
    	return (!innerLimit.get() || PDP.getCurrent(PDPPorts.GRIPPER_MOTOR) > CURRENT_THRESHOLD);
    }
    
    /**
     * Get state of gripper
     * @return true if at outer limit
     */
    public boolean getOuterLimit() {
    	return !outerLimit.get();
    }
    
    /**
     * Get state of system
     * @return state enum
     */
    public GripperState getState() {
    	GripperState r;
    	if (gripperMotor.get() > 0) {
    		r = GripperState.MOVING;
    	} else if (getInnerLimit()) {
    		r = GripperState.CLOSED;
    	} else if (getOuterLimit()) {
    		r = GripperState.OPEN;
    	} else {
    		r = GripperState.ERROR;
    	}
    	
    	return r;
    }
    
    public void initDefaultCommand() { }
    
    public enum GripperState {
    	OPEN, CLOSED, MOVING, ERROR;
    }
}

