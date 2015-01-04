package com.shsrobotics.recyclerush;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.IIR;

public interface Maps extends GLOBAL {
	
	public static final IIR driverJoystick = new IIR(USB_0, new IIR.Smoothing(20, 20, 20));
	
	public static final class Constants {
	}
	
	public static final class Dashboard {
		public static final String
			AUTO_MODE = "automode";
	}
	
	public static final class Enums {
		public static final int
			ROBOT_SET = 0,
			CONTAINER_STACK_SET = 1;
	}
	
	public static final class Buttons {
		
	}
}
