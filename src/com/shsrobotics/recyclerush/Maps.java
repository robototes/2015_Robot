package com.shsrobotics.recyclerush;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.JoystickButton;
import com.shsrobotics.library.LowPassFilterJoystick;
import com.shsrobotics.library.joysticks.Extreme3DController;

import edu.wpi.first.wpilibj.Joystick;

public interface Maps extends GLOBAL {
	
	public static final LowPassFilterJoystick driverJoystick = new LowPassFilterJoystick(USB_0, 20, 20, 24);
	public static final Joystick secondaryJoystick = new Joystick(USB_1);
	
	public static final double END_MATCH_MOTION_TIME = 5; // bring systems inside the robot with X seconds remaining
   	public static final double SOFTWARE_VERSION = 1.0;
   
	public class Buttons {
	   
	   public static final JoystickButton
		   	autoIntake = new JoystickButton(driverJoystick, 9),
		   	intakeOne = new JoystickButton(driverJoystick, Extreme3DController.trigger),
		   	liftForStack = new JoystickButton(driverJoystick, Extreme3DController.side),
	  		release = new JoystickButton(driverJoystick, 5),
	  		gripper = new JoystickButton(secondaryJoystick, 3), 
			setElevator = new JoystickButton(secondaryJoystick, 4),
	   		rcClawDrive = new JoystickButton(driverJoystick, 8),
  			rollersIn = new JoystickButton(secondaryJoystick, 7),
  			rollersOut = new JoystickButton(secondaryJoystick, 8),
  			clawUp = new JoystickButton(secondaryJoystick, 5),
  			clawDown = new JoystickButton(secondaryJoystick, 6),
	   		elevatorUp = new JoystickButton(driverJoystick, 6),
	   		elevatorDown = new JoystickButton(driverJoystick, 4),
	   		alignToField = new JoystickButton(driverJoystick, 7),
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
			HAS_RC = "hasRC",
			X_POSITION = "x-position",
			Y_POSITION = "y-position",
			HEADING = "heading",
			DISABLE_GRIPPER = "disable-gripper",
			DISABLE_ROLLERS = "disable-rollers",
			DISABLE_ELEVATOR = "disable-elevator",
			DISABLE_CLAW = "disable-claw",
			ELEVATOR_POSITION = "elev-pos",
			SOFTWARE_VERSION = "version";
	}
	
	public static final class Autonomous {
		public static final int
			ROBOT_SET = 0,
			TOTE_SET = 1,
			STACK_SET = 2,
			CENTER_CONTAINERS = 3;
		public static final double
			drivingSpeed = 0.75,
			robotSetDrivingTime = 2.9,
			toteSetDrivingTime = 2.2,
			RCsetRevTime = 0.25,
			RCsetForTime = 0.25,
			RCsetTurnTime = 1.5,
			clawDriveDelay = 1.3,
			oneToteTime = 0.2,
			curveCorrection = 0.28,
			toteSetForwardWhileTurn = 0.6;
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
