package com.shsrobotics.recyclerush.rcclaw;

import com.shsrobotics.library.Subsystem;
import com.shsrobotics.library.SubsystemState;
import com.shsrobotics.library.Task;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;

public class RCClaw implements Subsystem {
	
	protected SpeedController motor;
	protected AnalogPotentiometer pot;
	boolean estop = false;
	protected double setpoint;
	protected double tolerance;
	private Task currentTask;
	
	public RCClaw(SpeedController motor, AnalogPotentiometer pot) {
		this.motor = motor;
		this.pot = pot;
	}
	
	public void setTolerance(double t) {
		tolerance = t;
	}
	
	public void setTo(double angle) {
		if ( currentTask != null )
			currentTask.stop();
		if ( estop )
			return;
		currentTask = new ArmMoveTask(this);
		currentTask.start();
		setpoint = angle;
	}
	
	public void manualUp() {
		if ( currentTask != null )
			currentTask.stop();
		motor.set(1);
	}
	
	public void manualStop() {
		if ( currentTask != null )
			currentTask.stop();
		motor.set(0);
	}
	
	public void manualDown() {
		if ( currentTask != null )
			currentTask.stop();
		motor.set(-1);
	}
	
	@Override
	public void estop() {
		estop = true;
		if ( currentTask != null )
			currentTask.stop();
	}

	@Override
	public SubsystemState getState() {
		if ( estop )
			return SubsystemState.ESTOP;
		else if ( currentTask.running() )
			return SubsystemState.RUNNING;
		else
			return SubsystemState.IDLE;
	}

}
