package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * The elevator
 */
public class Elevator extends PIDSubsystem implements Hardware.IElevator {

	static final int clicksOverRange = 17280;
	static final double levels = 4.5;
	static final double P = 1 / (clicksOverRange);
	static final double I = 0.0;
	static final double D = 0.0;
	static final double TOLERANCE = 0.01;
	
	public static int LEVEL;
	
    public Elevator() {
    	super("ELEVATOR", P, I, D);
    	
    	encoder.setDistancePerPulse(levels / clicksOverRange);
    	setAbsoluteTolerance(TOLERANCE);
    	setInputRange(0, levels);
    	setOutputRange(-1.0, 1.0);
    	
    	enable();
    }

    /**
	 * Raise to a specific level
	 * @param level the level (0 is ground, 1 is height of 1 tote.  Double value is for fractional heights for manual control)
	 */
	public void raiseTo(double level) {
		if (!getPIDController().isEnable()) {
			enable();
		}
		
		setSetpoint(level);
	}
	
	/**
	 * Stop motor
	 */
	public void stop() {
		disable();
	}
    
	/**
	 * Returns whether elevator has reached setpoint
	 * @return true if yes
	 */
	public boolean isAtSetLevel() {
		return onTarget();
	
    /**
     * resets distance
     */
    public void reset() {
    	encoder.reset();
    }
    
    protected double returnPIDInput() {
    	return encoder.getDistance();
    }
    
    protected void usePIDOutput(double output) {
        elevatorMotorA.set(output);
        elevatorMotorB.set(output);
    }
    
    public void initDefaultCommand() { }
}
