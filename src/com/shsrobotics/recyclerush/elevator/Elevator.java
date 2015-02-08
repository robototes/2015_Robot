package com.shsrobotics.recyclerush.elevator;

import java.util.Map;

import com.shsrobotics.library.Subsystem;
import com.shsrobotics.library.SubsystemState;
import com.shsrobotics.library.Task;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * The encoder should be configured to output inches.
 * 
 * The speed controllers are configured so that sc.set(1) goes up and sc.set(-1) goes down.  
 * Make sure this is reflected in hardware or stuff will break when it tries to go past the top or bottom.  
 * 
 * @author s-orthm
 * 
 * TODO networktables
 *
 */
public class Elevator implements Subsystem {
	
	
	protected SpeedController winch;
	protected Encoder pos;
	protected DigitalInput isTop, isBottom;
	protected double setpoint;
	Task currentTask;
	private boolean estop = false;
	
	/**
	 * Tolerance in inches
	 * 
	 * (or totes? think about it, the encoder can be made to return tote distances)
	 */
	protected double tolerance = .04;
	
	public Elevator(SpeedController motor, Encoder enc, DigitalInput top, DigitalInput bottom) {
		winch = motor;
		pos = enc;
		isTop = top;
		isBottom = bottom;
	}
	
	public void calibrate() {
		if ( currentTask != null )
			currentTask.stop();
		if ( estop )
			return;
		currentTask = new ElevatorResetTask(this);
		currentTask.start();
	}
	
	/**
	 * Floors the current position and attemps to move up one from that.  
	 */
	public void moveUp() {
		if ( currentTask != null )
			currentTask.stop();
		if ( Math.floor(setpoint) + 1 > EUtil.ELEV_HAX_HEIGHT || estop )
			return;
		setpoint = Math.floor(setpoint)+1;
		currentTask = new ElevatorMoveUp(this);
		currentTask.start();
	}
	
	public void moveDown() {
		if ( currentTask != null )
			currentTask.stop();
		if ( Math.ceil(setpoint) - 1 < EUtil.ELEV_MIN_HEIGHT || estop )
			return;
		setpoint = Math.floor(setpoint)-1;
		currentTask = new ElevatorMoveUp(this);
		currentTask.start();
	}
	
	public void moveTo(double level) {
		if ( currentTask != null )
			currentTask.stop();
		if ( level > EUtil.ELEV_HAX_HEIGHT || estop )
			return;
		setpoint = level;
		currentTask = new MoveToTask(this);
		currentTask.start();
	}
	
	
	
	public final void estop() {
		if ( currentTask != null )
			currentTask.stop();
		estop = true;
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

	@Override
	public void returnState(Map<String, Object> keyvalue) {
		// TODO Auto-generated method stub
		
	}
	
}
