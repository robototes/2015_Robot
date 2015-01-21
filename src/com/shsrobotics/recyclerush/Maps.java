package com.shsrobotics.recyclerush;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;

public interface Maps extends GLOBAL {
	
	Joystick primary = new Joystick(1);
	Joystick secondary = new Joystick(2);
	
	public static final class Constants {
		
	}
	
	public static final class Dashboard {
		
	}
	
	public static final class Buttons {
		// buttons are arbitrary right now.
		public static JoystickButton elevatorPermButton = new JoystickButton(secondary, 5);
		public static JoystickButton squeezeForkPermButton = new JoystickButton(secondary, 6);
		public static JoystickButton rcClawPermButton = new JoystickButton(secondary, 10);
		
		public static JoystickButton elevatorUp = new JoystickButton(secondary, 7);
		public static JoystickButton elevatorDown = new JoystickButton(secondary, 8);
		
		public static JoystickButton gripperState = new JoystickButton(secondary, 9);
		
		public static JoystickButton rcClawState = new JoystickButton(secondary, 11);
	}
}
