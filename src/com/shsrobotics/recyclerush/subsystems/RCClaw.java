package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The robot's RC claw
 */
public class RCClaw extends Subsystem implements Hardware.IRCClaw {
    
	final double SPEED = 1.0;
	
	/**
	 * Move the claw up
	 */
	public void up() {
		clawMotor.set(SPEED);
	}
	
	/**
	 * Move the claw down
	 */
	public void down() {
		clawMotor.set(-SPEED);
	}
	
	/**
	 * Stop claw motion
	 */
	public void stop() {
		clawMotor.set(0.0);
	}
	
    public void initDefaultCommand() { }
}

