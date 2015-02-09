package com.shsrobotics.recyclerush;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.LowPassFilterJoystick;
import com.shsrobotics.library.LowPassFilterJoystick.Smoothing;
import com.shsrobotics.library.joysticks.Extreme3DController;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public interface Maps extends GLOBAL {
	public static final LowPassFilterJoystick driverJoystick = new LowPassFilterJoystick(USB_0, new Smoothing(20, 20, 20));
   
	public class Buttons {
		public static final Button
	   		autoIntake = new JoystickButton(driverJoystick, Extreme3DController.trigger),
	   		release = new JoystickButton(driverJoystick, Extreme3DController.side),
	   		gripperOpen = new JoystickButton(driverJoystick, 7),
	   		gripperClose = new JoystickButton(driverJoystick, 8);
	   
	   public static final com.shsrobotics.library.JoystickButton
  			rollersIn = new com.shsrobotics.library.JoystickButton(driverJoystick, 3),
  			rollersOut = new com.shsrobotics.library.JoystickButton(driverJoystick, 5),
  			setElevatorDiscrete = new com.shsrobotics.library.JoystickButton(driverJoystick, 11),
	   		setElevatorContinuous = new com.shsrobotics.library.JoystickButton(driverJoystick, 12);
	}
   
	public static final class Dashboard {
		public static final String
	   		TABLE_NAME = "ROBOT";
	}
}
