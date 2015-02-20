package com.shsrobotics.recyclerush.odometry;

import static com.shsrobotics.recyclerush.Hardware.IDriveBase.*;
import edu.wpi.first.wpilibj.Timer;

public class AccGyrOdometer {
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
		
		v[0] += accelerometerX.getAcceleration() * t;
		v[1] += accelerometerY.getAcceleration() * t;
		v[2] = gyroscope.getRate();
		
		timer.reset();
		timer.start();
		
		return v;
	}
}
