package com.shsrobotics.recyclerush.elevator;

public class EUtil {
	
	public static final double TOTE_HEIGHT_IN = 17;
	public static final double TOTE_GRAB_HEIGHT_IN = 8.5;
	public static final double TOTE_GRAB_HEIGHT_TOTE = .55;
	public static final double ELEV_MIN_HEIGHT = 0;
	public static final double ELEV_HAX_HEIGHT = 4;
	
	private EUtil() {
		
	}
	
	public static double toteToInches(double d) {
		return d*TOTE_HEIGHT_IN;
	}
	
	
	
}
