package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The gripper on the elevator
 */
public class Gripper extends Subsystem implements Hardware.IGripper {
    
	// normal motor operation speed
    static final double MOTOR_SPEED = 0.8;
    
    public static boolean OPEN = false;
    
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
    	return !innerLimit.get();
    }
    
    /**
     * Get state of gripper
     * @return true if at outer limit
     */
    public boolean getOuterLimit() {
    	return !outerLimit.get();
    }
    
    public void initDefaultCommand() { }
}

