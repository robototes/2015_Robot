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
	
	public class PDPPorts {
		public static final int
			ELEV_MOTOR_A = 2,
			ELEV_MOTOR_B = 3,
			GRIPPER_MOTOR = 15;
	}
   
	public static final class Dashboard {
		public static final String
	   		TABLE_NAME = "ROBOT",
	   		AUTO_MODE = "auto-mode",
			PDP_TEMP = "pdp-temp",
			PDP_VOLT = "pdp-volt",
			PDP_CURR = "pdp-curr",
			PDP_POWER = "pdp-power",
			ELEV_A_CURR = "elevA-curr",
			ELEV_B_CURR = "elevB-curr",
			GRIPPER_CURR = "gripper-curr",
			TOTE_COUNT = "toteCount";
	}
	
	public static final class Autonomous {
		public static final int
			ROBOT_SET = 0,
			TOTE_SET = 1,
			STACK_SET = 2,
			CENTER_CONTAINERS = 3;
		public static final double
			drivingSpeed = 0.6,
			robotSetDrivingTime = 3;
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
