package com.shsrobotics.recyclerush.odometry;

import static com.shsrobotics.recyclerush.Hardware.*;
import com.shsrobotics.recyclerush.Maps.Odometry;

import static com.shsrobotics.recyclerush.Hardware.IDriveBase.*;
import edu.wpi.first.wpilibj.Timer;

public class EncoderOdometer {
	//velocity
	private double[] v = {0, 0, 0};
	// pre-compute the tangent
	private double processed_alpha = Math.tan(Odometry.ALPHA);
	
	public EncoderOdometer() {
		reset();
	}
	
	public void reset() {
		v[0] = 0; v[1] = 0; v[2] = 0;
	}
	
	public double[] get() {
		double d1 = frontLeft.getSpeed() * Odometry.ENCODER_SCALING;
		double d2 = frontRight.getSpeed() * Odometry.ENCODER_SCALING;
		double d3 = rearLeft.getSpeed() * Odometry.ENCODER_SCALING;
		double d4 = rearRight.getSpeed() * Odometry.ENCODER_SCALING;
		
		v[0] = (d1 + d2 + d3 + d4) / 4;
		v[1] = (-d1 + d2 + d3 - d4) * processed_alpha;
		v[2] = (-d1 + d2 - d3 + d4) * Odometry.BETA;
		
		return v;
	}
	
}
