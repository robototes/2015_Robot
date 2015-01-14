package com.shsrobotics.recyclerush.elavator;
import com.shsrobotics.library.Task;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/*
 * Victor winchMotor = new Victor(4);
		eleavator = new Elevator(winchMotor);
 */


/**
 * UpOne, DownOne, Setlevel(1-4), Encoder
 * @author s-moocj
 *
 */

public class Elevator {
	SpeedController winch;
	Encoder encoder;
	WinchMoveTask winchMoveTask;
	int setHeight;
	double tolerance;
	int maxHeight = 4;
	
	public Elevator (SpeedController winch, Encoder encoder, int setHeight, double tolerance){
		this.winch = winch;
		this.encoder = encoder;
		this.setHeight = setHeight; 
		this.tolerance = tolerance;
		
		
	}
	public void upOne(){
		winchMoveTask.stop();
		setHeight = setHeight + 1;
		setHeight = clamp(setHeight);
		winchMoveTask = new WinchMoveTask();
		winchMoveTask.start();
	}
	public void downOne(){
		winchMoveTask.stop();
		setHeight = setHeight - 1;
		setHeight = clamp(setHeight);
		winchMoveTask = new WinchMoveTask();
		winchMoveTask.start();
	}
	public void setLevel(int l){
		winchMoveTask.stop();
		setHeight = clamp(l);
		winchMoveTask = new WinchMoveTask();
		winchMoveTask.start();
	}
	private int clamp(int l){
		if(l > maxHeight){
			l = maxHeight;
		}else if(l < 0){
			l = 0;
		}
		return l;
	}
	
	private class WinchMoveTask extends Task{
		boolean stop = false; 
		@Override
		protected void end() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void execute() {
			// TODO Auto-generated method stub
			if(Math.abs( encoder.getDistance() - setHeight) < tolerance)
				winch.set(0);
				stop = true;
		}

		@Override
		protected void initialize() {
			// TODO Auto-generated method stub
			if(Math.abs( encoder.getDistance() - setHeight) < tolerance){
				winch.set(0);
				stop = true;
				
			}else if(encoder.getDistance() > setHeight){
				winch.set(-1);
			}else if(encoder.getDistance() < setHeight){
				winch.set(1);
			}
		}

		@Override
		protected boolean isFinished() {
			// TODO Auto-generated method stub
			return stop;
		}
		
	}
	
}
