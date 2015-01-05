package com.shsrobotics.recyclerush;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;

public interface Hardware extends Maps {
	
	public static final PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	public static final class Wheels {
		public static final CANTalon frontLeft = new CANTalon(CAN_0);
		public static final CANTalon frontRight = new CANTalon(CAN_1);
		public static final CANTalon rearLeft = new CANTalon(CAN_2);
		public static final CANTalon rearRight = new CANTalon(CAN_3);
	}
	
	public static final RobotDrive robotDrive = new RobotDrive(Wheels.frontLeft, Wheels.rearLeft, Wheels.frontRight, Wheels.rearRight);
}
