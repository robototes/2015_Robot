package com.shsrobotics.recyclerush.odometry;

public class OdometerLocator {
	public double x, y, h;
	private long last;
	
	public OdometerLocator(double x, double y, double h) {
		this.x = x;
		this.y = y;
		this.h = h;
		last = System.nanoTime();
	}
	
	public void reset() {
		last = System.nanoTime();
	}
	
	public void update(double vx, double vy, double vh) {
		double dt = (System.nanoTime() - last) / 1e9;
		
		h += vh * dt;
		double c = Math.cos(h * Math.PI/180);
		double s = Math.sin(h * Math.PI/180);
		
		x += (c*x - s*y) * dt;
		y += (s*x + c*y) * dt;
		
		last = System.nanoTime();
	}
}
