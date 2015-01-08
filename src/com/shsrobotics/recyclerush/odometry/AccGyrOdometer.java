package com.shsrobotics.recyclerush.odometry;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.util.Vector;
import edu.wpi.first.wpilibj.Timer;

public class AccGyrOdometer implements Hardware {
	//velocity
	private Vector v = new Vector(0, 0, 0);
	Timer timer = new Timer();
	
	public void reset() {
		gyroscope.initGyro();
		
		v = new Vector(0, 0, 0);
		
		timer.reset();
		timer.start();
	}
	
	public Vector get() {
		timer.stop();
		double t = timer.get();
		
		v.x += accelerometer.getX() * t;
		v.x += accelerometer.getY() * t;
		v.h = gyroscope.getRate();
		
		timer.reset();
		timer.start();
		
		return v;
	}
}
