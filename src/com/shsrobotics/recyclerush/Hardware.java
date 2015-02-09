package com.shsrobotics.recyclerush;

import com.shsrobotics.recyclerush.Maps.Dashboard;
import com.shsrobotics.recyclerush.subsystems.DriveBase;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.Gripper;
import com.shsrobotics.recyclerush.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public interface Hardware extends Maps {
	
	public interface IDriveBase {
		public static final CANTalon 
			frontLeft = new CANTalon(CAN_2),
			frontRight = new CANTalon(CAN_3),
			rearLeft = new CANTalon(CAN_4),
			rearRight = new CANTalon(CAN_5);
		public static final RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		public static final BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();
		public static final Gyro gyroscope = new Gyro(ANALOG_0);
	}
	
	public interface IRollerIntake {
		public static final SpeedController leftRoller = new Jaguar(PWM_1);
		public static final SpeedController rightRoller = new Jaguar(PWM_2);
		public static final DigitalInput toteStop = new DigitalInput(DIGITAL_IO_2);
	}
	
	public interface IGripper {
		public static final SpeedController gripperMotor = new Jaguar(PWM_0);
		public static final DigitalInput innerLimit = new DigitalInput(DIGITAL_IO_0);
		public static final DigitalInput outerLimit = new DigitalInput(DIGITAL_IO_1);
	}
	
	public interface IElevator {
		public static final CANTalon elevatorMotorA = new CANTalon(CAN_0);
		public static final CANTalon elevatorMotorB = new CANTalon(CAN_1);
		public static final DigitalInput lowerLimit = new DigitalInput(DIGITAL_IO_3);
		public static final DigitalInput upperLimit = new DigitalInput(DIGITAL_IO_4);
	}
	
	public interface IDashboard {
		public static final NetworkTable dashboard = NetworkTable.getTable(Dashboard.TABLE_NAME);
		public static final PowerDistributionPanel PDP = new PowerDistributionPanel();
	}
	
	public static final DriveBase driveBase = new DriveBase();
	public static final RollerIntake rollerIntake = new RollerIntake();
	public static final Gripper gripper = new Gripper();
	public static final Elevator elevator = new Elevator();
	public static final Dashboard dashboard = new Dashboard();
}
