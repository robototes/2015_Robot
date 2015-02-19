package com.shsrobotics.recyclerush.odometry;

import com.shsrobotics.library.fieldpositioning.PID2D;
import com.shsrobotics.library.fieldpositioning.RobotPosition;

public class OdometerLocator implements RobotPosition {
	public double x, y, h;
	private long last;
	private double COLLISION_RADIUS = 21.0;
	
	public OdometerLocator(double x, double y, double h) {
		this.x = x;
		this.y = y;
		this.h = h;
		last = System.nanoTime();
	}
	
	public void reset() {
		last = System.nanoTime();
	}
	
	public void update(double x, double y, double h) {
		double dt = (System.nanoTime() - last) / 1e9;
		
		this.h = h;
		double c = Math.cos(h * Math.PI/180);
		double s = Math.sin(h * Math.PI/180);
		
		x += (c*x - s*y) * dt;
		y += (s*x + c*y) * dt;
		
		last = System.nanoTime();
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getHeading() { return h; }
	public double getCollisionRadius() { return COLLISION_RADIUS; }

	public PID2D getPID() {
		return null;
	}

}
