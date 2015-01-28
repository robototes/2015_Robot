package com.shsrobotics.recyclerush.forklift;




import com.shsrobotics.library.Task;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class Forklift {
	SpeedController winch;
	DigitalInput limitSwitchInward;
	DigitalInput limitSwitchOutward;
	Task moveTask;
	
	public Forklift(SpeedController winch, DigitalInput limitSwitchInward,DigitalInput limitSwitchOutward) {
		this.winch = winch;
		this.limitSwitchInward = limitSwitchInward;
		this.limitSwitchOutward = limitSwitchOutward;
	}
	public void outwards(){
		moveTask.stop();
		moveTask = new ForkliftOutward();
		moveTask.start();
		
	}
	
	public void inwards(){
		moveTask.stop();
		moveTask = new ForkliftInward();
		moveTask.start();
		
	}
	private class ForkliftInward extends Task{
		boolean stop = false; 
		@Override
		protected void end() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void execute() {
			// TODO Auto-generated method stub
			if(limitSwitchInward.get()){
				winch.set(0);
				stop = true;
			}
		}

		@Override
		protected void initialize() {
			// TODO Auto-generated method stub
			if(limitSwitchInward.get()){
				winch.set(0);
				stop = true;			
		}else{
			winch.set(-1);
		}
		}
		@Override
		protected boolean isFinished() {
			// TODO Auto-generated method stub
			return stop;
		}
	}
		
	private class ForkliftOutward extends Task{
		boolean stop = false; 
		@Override
		protected void end() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void execute() {
			// TODO Auto-generated method stub
			if(limitSwitchOutward.get()){
				winch.set(0);
				stop = true;
			}
		}

		@Override
		protected void initialize() {
			// TODO Auto-generated method stub
			if(limitSwitchOutward.get()){
				winch.set(0);
				stop = true;
		}else{
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