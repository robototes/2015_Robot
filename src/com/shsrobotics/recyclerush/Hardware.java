package com.shsrobotics.recyclerush;

import com.shsrobotics.library.fieldpositioning.PID2D;
import com.shsrobotics.recyclerush.odometry.Odometer;
import com.shsrobotics.recyclerush.odometry.OdometerLocator;
import com.shsrobotics.recyclerush.subsystems.DriveBase;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.Gripper;
import com.shsrobotics.recyclerush.subsystems.RCClaw;
import com.shsrobotics.recyclerush.subsystems.RobotDashboard;
import com.shsrobotics.recyclerush.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public interface Hardware extends Maps { // TODO: FINALIZE PORTS
	
	public interface IDriveBase {
		public static final CANTalon 
			frontLeft = new CANTalon(CAN_2),
			frontRight = new CANTalon(CAN_3),
			rearLeft = new CANTalon(CAN_4),
			rearRight = new CANTalon(CAN_5);
//		public static final RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		public static final BuiltInAccelerometer accelerometer = new BuiltInAccelerometer(); // TODO: DECIDE WHICH ACCEL USING
		public static final Gyro gyroscope = new Gyro(ANALOG_0); // TODO: ATTACH
		public static final Odometer odometer= new Odometer();
		public static final OdometerLocator robotPosition = new OdometerLocator(0, 0, 0);
		public static final PID2D fieldPID = new PID2D(driveBase, robotPosition);
	}
	
	public interface IRollerIntake {
		public static final SpeedController leftRoller = new Talon(PWM_1);
		public static final SpeedController rightRoller = new Talon(PWM_0);
		public static final DigitalInput toteStop = new DigitalInput(DIGITAL_IO_2);
	}
	
	public interface IGripper {
		public static final SpeedController gripperMotor = new Talon(PWM_4);
		public static final DigitalInput innerLimit = new DigitalInput(DIGITAL_IO_0);
		public static final DigitalInput outerLimit = new DigitalInput(DIGITAL_IO_1);
	}
	
	public interface IElevator {
		public static final SpeedController elevatorMotorA = new Talon(PWM_3);
		public static final SpeedController elevatorMotorB = new Talon(PWM_2);
		public static final Encoder encoder = new Encoder(DIGITAL_IO_5, DIGITAL_IO_6);
		public static final DigitalInput lowerLimit = new DigitalInput(DIGITAL_IO_3);
		public static final DigitalInput upperLimit = new DigitalInput(DIGITAL_IO_4);
	}
	
	public interface IRCClaw {
		public static final SpeedController clawMotor = new Talon(PWM_5);
	}
	
	public interface IDashboard extends Maps {
		public static final NetworkTable dashboard = NetworkTable.getTable(Dashboard.TABLE_NAME);
		public static final PowerDistributionPanel PDP = new PowerDistributionPanel();
	}
	
	public static final DriveBase driveBase = new DriveBase();
	public static final RollerIntake rollerIntake = new RollerIntake();
	public static final Gripper gripper = new Gripper();
	public static final Elevator elevator = new Elevator();
	public static final RCClaw claw = new RCClaw();
	public static final RobotDashboard dashboard = new RobotDashboard();
}
