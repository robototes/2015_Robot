package com.shsrobotics.recyclerush.gripper;

import com.shsrobotics.library.Subsystem;
import com.shsrobotics.library.SubsystemState;
import com.shsrobotics.library.Task;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

public class Gripper implements Subsystem {
	
	private boolean estop = false;
	private Task currentTask;
	protected SpeedController motor;
	protected DigitalInput limiter;
	protected DigitalInput innerLimit;
	
	public Gripper(SpeedController motor, DigitalInput limit, DigitalInput innerLimit) {
		this.motor = motor;
		limiter = limit;
		this.innerLimit = innerLimit;
	}
	
	public void close() {
		if ( currentTask != null )
			currentTask.stop();
		if ( estop )
			return;
		currentTask = new OpenGripperTask(this);
		currentTask.start();
	}
	
	public void open() {
		if ( currentTask != null ) {
			currentTask.stop();
		if ( estop )
			return;
		currentTask = new CloseGripperTask(this);
		currentTask.start();
		}
	}
	
	public void manualOpen() {
		if ( currentTask != null )
			currentTask.stop();
		motor.set(-1);
	}
	
	public void manualStop() {
		if ( currentTask != null )
			currentTask.stop();
		motor.set(0);
	}
	
	public void manualClose() {
		if ( currentTask != null )
			currentTask.stop();
		motor.set(1);
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
