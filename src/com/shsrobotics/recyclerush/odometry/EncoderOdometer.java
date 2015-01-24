package com.shsrobotics.recyclerush.odometry;

import com.shsrobotics.recyclerush.Hardware;

public class EncoderOdometer implements Hardware {
	//velocity
	private double[] v = {0, 0, 0};
	
	public EncoderOdometer() {
		Encoders.frontLeft.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.frontRight.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.rearLeft.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.rearRight.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		
		reset();
	}
	
	public void reset() {
		Encoders.frontLeft.reset();
		Encoders.frontRight.reset();
		Encoders.rearLeft.reset();
		Encoders.frontLeft.reset();
		
		v[0] = 0; v[1] = 0; v[2] = 0;
	}
	
	public double[] get() {
		double d1 = Encoders.frontLeft.getDistance();
		double d2 = Encoders.frontRight.getDistance();
		double d3 = Encoders.rearLeft.getDistance();
		double d4 = Encoders.rearRight.getDistance();
		
		v[0] = (d1 + d2 + d3 + d4) / 4;
		v[1] = (-d1 + d2 + d3 - d4) * Math.tan(Constants.ODOMETRY_ALPHA);
		v[2] = (-d1 + d2 - d3 + d4) * Constants.ODOMETRY_BETA;
		
		return v;
	}
	
}
