package com.shsrobotics.recyclerush.odometry;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.Maps.Odometry;

public class EncoderOdometer implements Hardware.IDriveBase {
	//velocity
	private double[] v = {0, 0, 0};
	
	public EncoderOdometer() {
		reset();
	}
	
	public void reset() {
		v[0] = 0; v[1] = 0; v[2] = 0;
	}
	
	public double[] get() {
		double d1 = frontLeft.getPosition() * Odometry.DISTANCE_PER_PULSE;
		double d2 = frontRight.getPosition() * Odometry.DISTANCE_PER_PULSE;
		double d3 = rearLeft.getPosition() * Odometry.DISTANCE_PER_PULSE;
		double d4 = rearRight.getPosition() * Odometry.DISTANCE_PER_PULSE;
		
		v[0] = (d1 + d2 + d3 + d4) / 4;
		v[1] = (-d1 + d2 + d3 - d4) * Math.tan(Odometry.ALPHA);
		v[2] = (-d1 + d2 - d3 + d4) * Odometry.BETA;
		
		return v;
	}
	
}
