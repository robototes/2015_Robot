package com.shsrobotics.recyclerush;

import com.shsrobotics.library.GLOBAL;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public interface Maps extends GLOBAL {
	
	Joystick primary = new Joystick(1);
	Joystick secondary = new Joystick(2);
	
	public static final class Constants {
		
	}
	
	public static final class Dashboard {
		
	}
	
	public static final class Buttons {
		public JoystickButton allowStacking = new JoystickButton(primary, 1);
	}
};