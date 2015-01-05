package com.shsrobotics.recyclerush;

import edu.wpi.first.wpilibj.Timer;

public class Odometer implements Hardware {
	
	private Location loc = new Location(0, 0, 0);
	private Timer timer = new Timer();
	
	public void reset() {
		Encoders.frontLeft.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.frontRight.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.rearLeft.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		Encoders.rearRight.setDistancePerPulse(Constants.WHEELS_DISTANCE_PER_PULSE);
		
		Encoders.frontLeft.reset();
		Encoders.frontRight.reset();
		Encoders.rearLeft.reset();
		Encoders.frontLeft.reset();
		
		loc = new Location(0, 0, 0);
		
		timer.start();
	}
	
	public Location get() {
		timer.stop();
		double t = timer.get();
		
		double d1 = Encoders.frontLeft.getDistance();
		double d2 = Encoders.frontRight.getDistance();
		double d3 = Encoders.rearLeft.getDistance();
		double d4 = Encoders.rearRight.getDistance();
		
		double dy = (d1 + d2 + d3 + d4) / 4;
		double dx = (-d1 + d2 + d3 - d4) * Math.tan(Constants.ODOMETRY_ALPHA);
		double w = (-d1 + d2 - d3 + d4) * Constants.ODOMETRY_BETA;
		
		loc.x += dx * t;
		loc.y += dy * t;
		loc.h += w * t;
		
		timer.reset();
		timer.start();
		
		return loc;
	}
	
	public void set(Location l) {
		this.loc = l;
	}
	
	public class Location {
		public double x, y, h;
		
		public Location(double x,  double y, double h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
}
