package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drive base
 */
public class DriveBase extends Subsystem implements Hardware.IDriveBase {
    
	static final int clicksPerRevolution = 48;
	static final double P_fl = 0.0;
	static final double P_fr = 0.0;
	static final double P_rl = 0.0;
	static final double P_rr = 0.0;
	static final double I = 0.0;
	static final double D = 0.0;
	
	public DriveBase() {
		frontLeft.changeControlMode(ControlMode.Speed);
		frontRight.changeControlMode(ControlMode.Speed);
		rearLeft.changeControlMode(ControlMode.Speed);
		rearRight.changeControlMode(ControlMode.Speed);
		frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		frontLeft.setPID(P_fl, I, D);
		frontRight.setPID(P_fr, I, D);
		rearLeft.setPID(P_rl, I, D);
		rearRight.setPID(P_rr, I, D);
		frontLeft.enableControl();
		frontRight.enableControl();
		rearLeft.enableControl();
		rearRight.enableControl();
//		robotDrive.setSafetyEnabled(false);
	}
	
	/**
	 * Drive the robot
	 * @param x strafe speed
	 * @param y for/rev speed
	 * @param z rotation speed
	 */
	public void drive(double x, double y, double z) {
//		robotDrive.mecanumDrive_Cartesian(x*x*x, y*y*y, z*z*z, 0.0);
	}

    public void initDefaultCommand() { }
}

