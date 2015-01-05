package com.shsrobotics.recyclerush;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.IIR;

public interface Maps extends GLOBAL {
	
	public static final IIR driverJoystick = new IIR(USB_0, new IIR.Smoothing(20, 20, 20));
	
	public static final class Constants {
		public static final double WHEELS_DISTANCE_PER_PULSE = 0.01;
		public static final double ODOMETRY_ALPHA = 41.71; // virtual movement angle for carpet
		public static final double ODOMETRY_BETA = 0.25; // arbitrary scaling factor for rotation
	}
	
	public static final class Dashboard {
		public static final String
			AUTO_MODE = "auto-mode",
			FL_TALON_TEMP = "fl-talon-temp",
			RL_TALON_TEMP = "rl-talon-temp",
			FR_TALON_TEMP = "fr-talon-temp",
			RR_TALON_TEMP = "rr-talon-temp",
			FL_TALON_CURR = "fl-talon-curr",
			RL_TALON_CURR = "rl-talon-curr",
			FR_TALON_CURR = "fr-talon-curr",
			RR_TALON_CURR = "rr-talon-curr",
			PDP_TEMP = "pdp-temp",
			PDP_VOLT = "pdp-volt";
	}
	
	public static final class Enums {
		public static final int
			ROBOT_SET = 0,
			CONTAINER_STACK_SET = 1;
	}
	
	public static final class Buttons {
		
	}
}
