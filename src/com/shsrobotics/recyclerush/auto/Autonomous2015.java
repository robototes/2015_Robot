package com.shsrobotics.recyclerush.auto;

public interface Autonomous2015 {
	/**
	 * starting position, in feet.
	 */
	public double getStartingX();
	/**
	 * starting position, in feet.
	 */
	public double getStartingY();
	/**
	 * starting heading, in feet.
	 */
	public double getStartingHeading();
	
	/**
	 * Compatibility for Command
	 */
	public void start();
	public void cancel();
}
