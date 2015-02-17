package com.shsrobotics.recyclerush.subsystems;

import static com.shsrobotics.recyclerush.Hardware.IRCClaw.*;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The robot's RC claw
 */
public class RCClaw extends Subsystem {
    
	final double SPEED = 0.8;
	
	/**
	 * Move the claw up
	 */
	public void up() {
		if (!isUp()) { 
			clawMotor.set(SPEED);
		} else {
			clawMotor.set(0.0);
		}
	}
	
	/**
	 * Move the claw down
	 */
	public void down() {
		if (!isDown()) { 
			clawMotor.set(-SPEED);
		} else {
			clawMotor.set(0.0);
		}
	}
	
	/**
	 * Stop claw motion
	 */
	public void stop() {
		clawMotor.set(0.0);
	}
	
	/**
	 * Returns position
	 * @return true if all the way up
	 */
	public boolean isUp() {
		return upperLimit.get();
	}
	
	/**
	 * Returns position
	 * @return true if all the way down
	 */
	public boolean isDown() {
		return lowerLimit.get();
	}
	
    public void initDefaultCommand() { }
}

