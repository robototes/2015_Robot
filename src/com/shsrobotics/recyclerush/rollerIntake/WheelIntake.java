package com.shsrobotics.recyclerush.rollerIntake;

import edu.wpi.first.wpilibj.SpeedController;


public class WheelIntake {
	SpeedController winch;
	
	
	public void Wheel(SpeedController winch){
		this.winch = winch;
		
	}
	
	public void Inwards() {
		winch.set(1);
		
	}
	public void Outwards(){
		winch.set(-1);
		
	}
	public void Stop(){
		winch.set(0);
	}
}
