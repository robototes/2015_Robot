package com.shsrobotics.recyclerush.odometry;

import com.shsrobotics.recyclerush.Hardware;
import edu.wpi.first.wpilibj.Timer;

public class AccGyrOdometer implements Hardware.IDriveBase {
	//velocity
	private double[] v = {0, 0, 0};
	Timer timer = new Timer();
	
	public AccGyrOdometer() {
		reset();
	}
	
	public void reset() {
		gyroscope.initGyro();
		
		v[0] = 0; v[1] = 0; v[2] = 0;
		
		timer.reset();
		timer.start();
	}
	
	public double[] get() {
		timer.stop();
		double t = timer.get();
		
		v[0] += accelerometer.getX() * t;
		v[1] += accelerometer.getY() * t;
		v[2] = gyroscope.getRate();
		
		timer.reset();
		timer.start();
		
		return v;
	}
}
