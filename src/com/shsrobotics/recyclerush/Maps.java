package com.shsrobotics.recyclerush;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.JoystickButtonStart;
import com.shsrobotics.library.JoystickCallbackButton;

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
		public static JoystickCallbackButton elevatorPermButton = new JoystickCallbackButton(secondary, 5, ()->{
			
		});
		public static JoystickCallbackButton squeezeForkPermButton = new JoystickCallbackButton(secondary, 6, ()->{
			
		});
		public static JoystickCallbackButton rcClawPermButton = new JoystickCallbackButton(secondary, 10, ()->{
			
		});
		
		public static JoystickCallbackButton elevatorUp = new JoystickCallbackButton(secondary, 7, ()->{
			
		});
		public static JoystickCallbackButton elevatorDown = new JoystickCallbackButton(secondary, 8, ()->{
			
		});
		
		public static JoystickCallbackButton gripperState = new JoystickCallbackButton(secondary, 9, ()->{
			
		});
		
		public static JoystickCallbackButton rcClawState = new JoystickCallbackButton(secondary, 11, ()->{
			
		});
	}
};