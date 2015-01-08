package com.shsrobotics.recyclerush.odometry;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.util.Vector;

public class EncoderOdometer implements Hardware {
	//velocity
	private Vector v = new Vector(0, 0, 0);
	
	public void reset() {
		Encoders.frontLeft.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.frontRight.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.rearLeft.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.rearRight.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		
		Encoders.frontLeft.reset();
		Encoders.frontRight.reset();
		Encoders.rearLeft.reset();
		Encoders.frontLeft.reset();
		
		v = new Vector(0, 0, 0);
	}
	
	public Vector get() {
		double d1 = Encoders.frontLeft.getDistance();
		double d2 = Encoders.frontRight.getDistance();
		double d3 = Encoders.rearLeft.getDistance();
		double d4 = Encoders.rearRight.getDistance();
		
		v.y = (d1 + d2 + d3 + d4) / 4;
		v.x = (-d1 + d2 + d3 - d4) * Math.tan(Constants.ODOMETRY_ALPHA);
		v.h = (-d1 + d2 - d3 + d4) * Constants.ODOMETRY_BETA;
		
		return v;
	}
	
}
