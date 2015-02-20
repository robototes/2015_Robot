package com.shsrobotics.recyclerush;

import com.shsrobotics.library.IntermediarySpeedController;
import com.shsrobotics.library.fieldpositioning.PID2D;
import com.shsrobotics.recyclerush.odometry.MinOdometer;
import com.shsrobotics.recyclerush.odometry.OdometerLocator;
import com.shsrobotics.recyclerush.subsystems.DriveBase;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.Gripper;
import com.shsrobotics.recyclerush.subsystems.RCClaw;
import com.shsrobotics.recyclerush.subsystems.RobotDashboard;
import com.shsrobotics.recyclerush.subsystems.RollerIntake;
import com.shsrobotics.recyclerush.subsystems.TurnPID;

import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Hardware implements Maps {
	
	public static void initialize() {
		ElevatorHardware.init();
		IDriveBase.init();
		IRollerIntake.init();
		IDashboard.init();
		IRCClaw.init();
		IGripper.init();
		
		elevator = new Elevator();
		driveBase = new DriveBase();
		rollerIntake = new RollerIntake();
		gripper = new Gripper();
		elevator = new Elevator();
		dashboard = new RobotDashboard();
		claw = new RCClaw();
	}
	
	public static class IDriveBase {
		public static void init() {
			frontLeft = new CANTalon(CAN_2);
			frontRight = new CANTalon(CAN_5);
			rearLeft = new CANTalon(CAN_4);
			rearRight = new CANTalon(CAN_3);
			iFrontLeft = new IntermediarySpeedController(frontLeft, Odometry.DRIVE_SCALE);
			iFrontRight = new IntermediarySpeedController(frontRight, Odometry.DRIVE_SCALE);
			iRearLeft = new IntermediarySpeedController(rearLeft, Odometry.DRIVE_SCALE);
			iRearRight = new IntermediarySpeedController(rearRight, Odometry.DRIVE_SCALE);
			robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
			accelerometerX = new AnalogAccelerometer(ANALOG_1); 
			accelerometerY = new AnalogAccelerometer(ANALOG_2);
			gyroscope = new Gyro(ANALOG_0); 
			odometer = new MinOdometer();
			robotPosition = new OdometerLocator(0, 0, 0);
//			fieldPID = new PID2D(driveBase, robotPosition);
			alignToFieldPID = new TurnPID();
		}
		public static CANTalon 
			frontLeft,
			frontRight,
			rearLeft,
			rearRight;
		public static SpeedController 
			iFrontLeft,
			iFrontRight,
			iRearLeft,
			iRearRight;
		public static RobotDrive robotDrive;
		public static AnalogAccelerometer accelerometerX; 
		public static AnalogAccelerometer accelerometerY;
		public static Gyro gyroscope;
		public static MinOdometer odometer;
		public static OdometerLocator robotPosition;
		public static PID2D fieldPID;
		public static TurnPID alignToFieldPID;
	}
	
	public static class IRollerIntake {
		public static void init() {
			leftRoller = new Talon(PWM_3);
			rightRoller = new Talon(PWM_0);
			lightToteStop = new DigitalInput(DIGITAL_IO_2);
			isRC = new DigitalInput(DIGITAL_IO_7);
		}
		public static SpeedController leftRoller;
		public static SpeedController rightRoller;
		public static DigitalInput lightToteStop;
		public static DigitalInput isRC;
	}
	
	public static class IGripper {
		public static void init() {
			gripperMotor = new Talon(PWM_4);
			innerLimit = new DigitalInput(DIGITAL_IO_0);
			outerLimit = new DigitalInput(DIGITAL_IO_1);
		
		}
		
		public static SpeedController gripperMotor;
		public static DigitalInput innerLimit;
		public static DigitalInput outerLimit;
	}
	
	public static class ElevatorHardware {
		public static void init() {
			elevatorMotorA = new Talon(PWM_1);
			elevatorMotorB = new Talon(PWM_5);
			encoder = new Encoder(DIGITAL_IO_5, DIGITAL_IO_6);
			lowerLimit = new DigitalInput(DIGITAL_IO_3);
			upperLimit = new DigitalInput(DIGITAL_IO_4);
		}
		public static SpeedController elevatorMotorA;
		public static SpeedController elevatorMotorB;
		public static Encoder encoder;
		public static DigitalInput lowerLimit;
		public static DigitalInput upperLimit;
	}
	
	public static class IRCClaw {
		public static void init() {
			clawMotor = new Talon(PWM_2);
			upperLimit = new DigitalInput(DIGITAL_IO_9);
			lowerLimit = new DigitalInput(DIGITAL_IO_8);
		}
		
		public static SpeedController clawMotor;
		public static DigitalInput upperLimit;
		public static DigitalInput lowerLimit;
	}
	
	public static class IDashboard {
		public static void init() {
			table = NetworkTable.getTable(Dashboard.TABLE_NAME);
			pdp = new PowerDistributionPanel();
		}
		
		public static NetworkTable table;
		public static PowerDistributionPanel pdp;
	}
	
	public static DriveBase driveBase;
	public static RollerIntake rollerIntake;
	public static Gripper gripper;
	public static Elevator elevator;
	public static RCClaw claw;
	public static RobotDashboard dashboard;
}
