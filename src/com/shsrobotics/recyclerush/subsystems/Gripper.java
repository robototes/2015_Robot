package com.shsrobotics.recyclerush.subsystems;

import static com.shsrobotics.recyclerush.Hardware.IGripper.*;
import static com.shsrobotics.recyclerush.Hardware.IDashboard.*;
import static com.shsrobotics.recyclerush.Hardware.*;

import com.shsrobotics.recyclerush.Maps.PDPPorts;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The gripper on the elevator
 */
public class Gripper extends Subsystem {
    
	// normal motor operation speed
	static final double MOTOR_SPEED_CLOSE = 0.8;
    static final double MOTOR_SPEED_OPEN = 1.0;
    // current threshold for motor stop
    static final double CURRENT_THRESHOLD_TOTE = 32;
    static final double CURRENT_THRESHOLD_RC = 24;
    
    double lastCurrent = 0;
    
    /**
     * Open the gripper
     */
    public void open() {
    	if (dashboard.disableGripper()) return;
    	gripperMotor.set(MOTOR_SPEED_OPEN);
    }
    
    /**
     * Close the gripper
     */
    public void close() {
    	if (dashboard.disableGripper()) return;
    	gripperMotor.set(-MOTOR_SPEED_CLOSE);
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
    	return (innerLimit.get() || pdp.getCurrent(PDPPorts.GRIPPER_MOTOR) > (rollerIntake.isRCIn() ? CURRENT_THRESHOLD_RC : CURRENT_THRESHOLD_TOTE));
    }
    
    /**
     * Get state of gripper
     * @return true if at outer limit
     */
    public boolean getOuterLimit() {
    	return outerLimit.get();
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

