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
	   		TABLE_NAME = "ROBOT",
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
	
	public static final class Autonomous {
		public static final int
			ROBOT_SET = 0,
			STACK_SET = 1,
			CENTER_CONTAINERS = 2;
	}
	
	public static final class Odometry {
		public static final double DISTANCE_PER_PULSE = 0.01;
		public static final double ALPHA = 41.71; // virtual movement angle for carpet
		public static final double BETA = 0.25; // arbitrary scaling factor for rotation
		public static final double[] PROCESS_VARIANCE = {0.1, 0.15, 3}; // X, Y, Z
		public static final double[] GYRO_OBSERVATION_VARIANCE = {0.15, 0.15, 10};
		public static final double[] OBSERVATION_COVARIANCE = {0.05, 0.05, 0.01};
		public static final double[] ENCODER_OBSERVATION_VARIANCE = {0.1, 0.1, 0.1};
		public static final double MAX_ROT_SPEED = 270;
		public static final double MAX_FOR_SPEED = 8;
		public static final double MAX_STRAFE_SPEED = 5;
	}
}
