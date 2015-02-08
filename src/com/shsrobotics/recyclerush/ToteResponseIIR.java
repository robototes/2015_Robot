package com.shsrobotics.recyclerush;

import com.shsrobotics.library.IIR;

public class ToteResponseIIR extends IIR {
	
	public static final double smoothNoTotes = 0;
	public static final double smoothOneTotes = 0;
	public static final double smoothTwoTotes = 0;
	public static final double smoothThreeTotes = 0;
	public static final double smoothFourTotes = 0;
	
	public ToteResponseIIR(int joystick) {
		super(joystick);
	}
	
	public void updateSmoothing() {
		int c = StackManager.getCount();
		switch(c) {
			case 0:
				this.setSmoothing(new Smoothing(smoothNoTotes, smoothNoTotes, smoothNoTotes));
				break;
			case 1:
				this.setSmoothing(new Smoothing(smoothOneTotes, smoothOneTotes, smoothOneTotes));
				break;
			case 2:
				this.setSmoothing(new Smoothing(smoothTwoTotes, smoothTwoTotes, smoothTwoTotes));
				break;
			case 3:
				this.setSmoothing(new Smoothing(smoothThreeTotes, smoothThreeTotes, smoothThreeTotes));
				break;
			case 4:
				this.setSmoothing(new Smoothing(smoothFourTotes, smoothFourTotes, smoothFourTotes));
				break;
			default:
				this.setSmoothing(new Smoothing(smoothOneTotes, smoothOneTotes, smoothOneTotes));
		}
		
	}
	
}
