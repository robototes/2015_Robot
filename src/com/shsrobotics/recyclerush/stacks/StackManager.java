package com.shsrobotics.recyclerush.stacks;

public class StackManager {
	public static boolean hasRC = false;
	public static int totes = 0;
	
	public static int getObjects() {
		return totes + (hasRC ? 1 : 0);
	}
}
