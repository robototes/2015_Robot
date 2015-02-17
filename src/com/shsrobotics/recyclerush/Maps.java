package com.shsrobotics.recyclerush;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.JoystickButton;
import com.shsrobotics.library.LowPassFilterJoystick;
import com.shsrobotics.library.joysticks.Extreme3DController;

import edu.wpi.first.wpilibj.Joystick;

public interface Maps extends GLOBAL {
	
	public static final LowPassFilterJoystick driverJoystick = new LowPassFilterJoystick(USB_0, 15, 15, 12);
	public static final Joystick secondaryJoystick = new Joystick(USB_1);
	public static final Joystick tertiaryJoystick = new Joystick(USB_2);
	
	public static final double END_MATCH_MOTION_TIME = 130; // bring systems inside the robot with X seconds remaining
   
	public class Buttons {
	   
	   public static final JoystickButton
		   	autoIntake = new JoystickButton(driverJoystick, Extreme3DController.trigger),
	  		release = new JoystickButton(driverJoystick, 5),
	  		gripperOpen = new JoystickButton(driverJoystick, 11), 
	  		gripperClose = new JoystickButton(driverJoystick, 12),
			setElevator = new JoystickButton(secondaryJoystick, 4),
	   		rcClawDrive = new JoystickButton(driverJoystick, 2),
  			rollersIn = new JoystickButton(secondaryJoystick, 7),
  			rollersOut = new JoystickButton(secondaryJoystick, 8),
  			clawUp = new JoystickButton(secondaryJoystick, 5),
  			clawDown = new JoystickButton(secondaryJoystick, 6),
	   		elevatorUp = new JoystickButton(driverJoystick, 6),
	   		elevatorDown = new JoystickButton(driverJoystick, 4),
	   		disableRollers = new JoystickButton(driverJoystick, 7),
	   		disableElevator = new JoystickButton(driverJoystick, 8),
	   		disableGripper = new JoystickButton(driverJoystick, 9),
	   		disableEndMatchElevatorMotion = new JoystickButton(driverJoystick, 10);
	   
	   public static final int elevatorPosition = 0; // axis to get elevator position
	}
	
	public class PDPPorts {
		public static final int
			ELEV_MOTOR_A = 3,
			ELEV_MOTOR_B = 13,
			GRIPPER_MOTOR = 12;
	}
   
	public static final class Dashboard {
		public static final String
	   		TABLE_NAME = "ROBOT",
	   		AUTO_MODE = "auto-mode",
			PDP_TEMP = "pdp-temp",
			PDP_VOLT = "pdp-volt",
			PDP_CURR = "pdp-curr",
			PDP_POWER = "pdp-power",
			ELEV_CURR = "elev-curr",
			GRIPPER_CURR = "gripper-curr",
			TOTE_COUNT = "toteCount",
			X_POSITION = "x-position",
			Y_POSITION = "y-position",
			HEADING = "heading";
	}
	
	public static final class Autonomous {
		public static final int
			ROBOT_SET = 0,
			TOTE_SET = 1,
			STACK_SET = 2,
			CENTER_CONTAINERS = 3;
		public static final double
			drivingSpeed = 0.85,
			robotSetDrivingTime = 3;
	}
	
	public static final class Odometry {
		public static final double DRIVE_SCALE = 9600;
		public static final double ENCODER_SCALING = 0.000194021;
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
