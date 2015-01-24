package com.shsrobotics.recyclerush.rollers;

import com.shsrobotics.library.Subsystem;
import com.shsrobotics.library.SubsystemState;
import com.shsrobotics.library.Task;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

public class Rollers implements Subsystem {
	
	private boolean estop = false;
	protected SpeedController left,right;
	protected DigitalInput stop;
	protected AnalogPotentiometer potLeft, potRight;
	Task adaptionTask;
	RollerControlTask speedTask = new RollerControlTask(this);
	
	public Rollers(SpeedController left, SpeedController right, DigitalInput totestop, AnalogPotentiometer pleft, AnalogPotentiometer pright) {
		this.left = left;
		this.right = right;
		stop = totestop;
		potLeft = pleft;
		potRight = pright;
	}
	
	/**
	 * experimental.
	 */
	public void startAdaption() {
		if ( estop )
			return;
		
	}
	
	/**
	 * experimental variance.
	 * @param r
	 * @param l
	 */
	private void setSpeed(double r, double l) {
		synchronized (speedTask.lock) {
			speedTask.leftSpeed = l;
			speedTask.rightSpeed = r;
		}
	}
	
	/**
	 * experimental
	 */
	public void stopAdaption() {
		if ( adaptionTask != null )
			adaptionTask.stop();
	}
	
	public void setOutput() {
		if ( estop )
			return;
		setSpeed(1,-1);
	}
	
	/**
	 * Sets rollers to roll totes into robot.
	 */
	public void setInput() {
		if ( estop )
			return;
		setSpeed(-1,1);
	}
	
	public void setStop() {
		setSpeed(0,0);
		if ( adaptionTask != null )
			adaptionTask.stop();
	}
	
	@Override
	public void estop() {
		estop = true;
		stopAdaption();
		setStop();
	}

	@Override
	public SubsystemState getState() {
		if ( estop ) {
			return SubsystemState.ESTOP;
		}
		else if ( adaptionTask != null && adaptionTask.running() || (speedTask.leftSpeed != 0 || speedTask.rightSpeed != 0) && speedTask.running() )
			return SubsystemState.RUNNING;
		else
			return SubsystemState.IDLE;
	}

}
