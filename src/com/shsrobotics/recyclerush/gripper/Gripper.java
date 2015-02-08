package com.shsrobotics.recyclerush.gripper;

import java.util.Map;

import com.shsrobotics.library.Subsystem;
import com.shsrobotics.library.SubsystemState;
import com.shsrobotics.library.Task;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.can.CANExceptionFactory;

/**
 * TODO network tables
 * @author s-orthm
 *
 */
public class Gripper implements Subsystem {
	
	private boolean estop = false;
	private Task currentTask;
	protected SpeedController motor;
	protected DigitalInput outerLimit;
	protected DigitalInput innerLimit;
	protected DigitalInput toteStop;
	protected PowerDistributionPanel pdp;
	protected final int pdpChannel;
	
	public Gripper(SpeedController motor, DigitalInput outerLimit, DigitalInput innerLimit, DigitalInput toteStop, PowerDistributionPanel pdp, int pdpChannel) {
		this.motor = motor;
		this.outerLimit = outerLimit;
		this.innerLimit = innerLimit;
		this.toteStop = toteStop;
		this.pdp = pdp;
		this.pdpChannel = pdpChannel;
	}
	
	public boolean controlsTote() {
		return toteStop.get();
	}
	
	public void close() {
		if ( currentTask != null ) {
			System.out.println("ct != null");
			if ( estop ) {
				System.out.println("estop");
				currentTask.stop();
				return;
			}
			else if (! (currentTask instanceof OpenGripperTask )) {
				if ( !currentTask.running() ) {
					System.out.println("restarting curent task");
					currentTask.start();
				}
				else {
					System.out.println("letting current task continue");
				}
			}
			else {
				System.out.println("starting new task");
				currentTask = new CloseGripperTask(this);
				currentTask.start();
			}
		}
		else {
			System.out.println("ct == null, starting new task");
			currentTask = new CloseGripperTask(this);
			currentTask.start();
		}
	}
	
	public void open() {
		if ( currentTask != null ) {
			currentTask.stop();
		if ( estop )
			return;
		currentTask = new OpenGripperTask(this);
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

	@Override
	public void returnState(Map<String, Object> keyvalue) {
		// TODO Auto-generated method stub
		
	}

}
