package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The robot's elevator
 */
public class Elevator extends Subsystem implements Hardware.IElevator {
    
	static final int clicksPerRevolution = 48;
	static final double revolutionsPerLevel = 72;
	static final double P = 1 / (clicksPerRevolution * 4 * revolutionsPerLevel);
	static final double I = 0.0;
	static final double D = 0.0;
	static final double TOLERANCE = 0.01;
	
	public static int LEVEL;
	
	public Elevator() {
		elevatorMotorA.changeControlMode(ControlMode.Position);
		elevatorMotorB.changeControlMode(ControlMode.Position);
		elevatorMotorA.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		elevatorMotorB.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		elevatorMotorA.setPID(P, I, D);
		elevatorMotorB.setPID(P, I, D);
		elevatorMotorA.enableControl();
		elevatorMotorB.enableControl();
	}
	
	/**
	 * Raise to a specific level
	 * @param level the level (0 is ground, 1 is height of 1 tote.  Double value is for fractional heights for manual control)
	 */
	public void raiseTo(double level) {
		if (!elevatorMotorA.isControlEnabled()) {
			elevatorMotorA.enableControl();
			elevatorMotorB.enableControl();
		}
		
		elevatorMotorA.set(level);
		elevatorMotorB.set(level);
	}
	
	/**
	 * Stop motor
	 */
	public void stop() {
		elevatorMotorA.disableControl();
		elevatorMotorB.disableControl();
	}
	
	/**
	 * Returns whether elevator has reached setpoint
	 * @return true if yes
	 */
	public boolean isAtSetLevel() {
		return (elevatorMotorA.getClosedLoopError() < TOLERANCE);
	}
	
    public void initDefaultCommand() { }
}

