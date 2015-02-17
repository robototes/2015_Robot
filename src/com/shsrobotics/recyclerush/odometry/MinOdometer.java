package com.shsrobotics.recyclerush.odometry;

import static com.shsrobotics.recyclerush.Hardware.IDriveBase.*;

import com.shsrobotics.recyclerush.Maps.Odometry;

public class MinOdometer {
	// velocity
	private double[] x = { 0, 0, 0 };
	// pre-compute the tangent
	private double processed_alpha = Math.tan(Odometry.ALPHA * Math.PI / 180);

	public MinOdometer() {
		gyroscope.initGyro();
		reset();
	}

	public void reset() {
		frontLeft.setPosition(0);
		frontRight.setPosition(0);
		rearLeft.setPosition(0);
		rearRight.setPosition(0);
		gyroscope.reset();
		x[0] = 0;
		x[1] = 0;
		x[2] = 0;
	}

	public double[] get() {
		double d1 = frontLeft.getPosition() * Odometry.ENCODER_SCALING;
		double d2 = frontRight.getPosition() * Odometry.ENCODER_SCALING;
		double d3 = rearLeft.getPosition() * Odometry.ENCODER_SCALING;
		double d4 = rearRight.getPosition() * Odometry.ENCODER_SCALING;

		x[1] = (d1 + d2 + d3 + d4) / 4;
		x[0] = (-d1 + d2 + d3 - d4) * processed_alpha;
		x[2] = gyroscope.getAngle();

		return x;
	}
}
