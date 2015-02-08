package com.shsrobotics.recyclerush;

import com.shsrobotics.recyclerush.drive.DriveBase;
import com.shsrobotics.recyclerush.elevator.Elevator;
import com.shsrobotics.recyclerush.gripper.Gripper;
import com.shsrobotics.recyclerush.rcclaw.RCClaw;
import com.shsrobotics.recyclerush.rollers.Rollers;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public interface Hardware extends Maps {
	
	public static class Control {
		public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	}
	
	public static class MotorControllers {
		public static final CANTalon frontLeft = new CANTalon(1);
		//public static final CANTalon frontRight = new CANTalon(0);
		//public static final CANTalon backLeft = new CANTalon(0);
		//public static final CANTalon backRight = new CANTalon(0);
		public static final Jaguar gripper = new Jaguar(0);
	}
	
	public static class LimitSwitches {
		public static final DigitalInput
			gripperOuterLimit = new DigitalInput(1),
			gripperInnerLimit = new DigitalInput(0),
			gripperToteStop = new DigitalInput(2);
	}
	
	public static class Subsystems {
		public static Elevator elevator;
		public static Gripper gripper;
		public static Rollers rollers;
		public static RCClaw rcClaw;
		public static DriveBase driveBase;
	}
}
