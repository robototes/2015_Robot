package com.shsrobotics.recyclerush;

public class StackManager {
	
	private static int toteCount = 0;
	private static boolean rc = false;
	
	
	private StackManager() {
		
	}
	
	public static synchronized void toteUnder() {
		toteCount++;
	}
	
	public static synchronized void rcOnStack() {
		rc = true;
	}
	
	public static synchronized void dropStack() {
		toteCount = 0;
	}
	
	public static int getCount() {
		return toteCount;
	}
	
	public static boolean hasRC() {
		return rc;
	}
	
}
