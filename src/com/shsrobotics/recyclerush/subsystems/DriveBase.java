package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.library.fieldpositioning.PID2DOutput;
import com.shsrobotics.library.fieldpositioning.Point;
import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drive base
 */
public class DriveBase extends Subsystem implements Hardware.IDriveBase, PID2DOutput {
    
	static final int clicksPerRevolution = 48;
	static final double P_fl = 0.0; // TODO: CAN PID CONSTANTS
	static final double P_fr = 0.0;
	static final double P_rl = 0.0;
	static final double P_rr = 0.0;
	static final double I = 0.0;
	static final double D = 0.0;
	
	static final double P_X = 0.0; // TODO: PID CONSTANTS
	static final double I_X = 0.0;
	static final double D_X = 0.0;
	static final double P_Y = 0.0;
	static final double I_Y = 0.0;
	static final double D_Y = 0.0;
	static final double P_H = 0.0;
	static final double I_H = 0.0;
	static final double D_H = 0.0;
	
	private double x, y, h;
	
	public DriveBase() { // TODO: UNCOMMENT
//		frontLeft.changeControlMode(ControlMode.Speed);
//		frontRight.changeControlMode(ControlMode.Speed);
//		rearLeft.changeControlMode(ControlMode.Speed);
//		rearRight.changeControlMode(ControlMode.Speed);
		
//		frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		rearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		frontLeft.setPID(P_fl, I, D);
//		frontRight.setPID(P_fr, I, D);
//		rearLeft.setPID(P_rl, I, D);
//		rearRight.setPID(P_rr, I, D);
		
//		frontLeft.enableControl();
//		frontRight.enableControl();
//		rearLeft.enableControl();
//		rearRight.enableControl();
		
//		robotDrive.setSafetyEnabled(false);
		fieldPID.setPIDX(P_X, I_X, D_X);
		fieldPID.setPIDY(P_Y, I_Y, D_Y);
		fieldPID.setPIDZ(P_H, I_H, D_H);
		
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
	}
	
	/**
	 * Drive the robot
	 * @param x strafe speed
	 * @param y for/rev speed
	 * @param z rotation speed
	 */
	public void drive(double x, double y, double z) {
		robotDrive.mecanumDrive_Cartesian(x, y, z, 0.0);
		this.x = x;
		this.y = y;
		this.h = z;
	}
	
	/**
	 * Update the odometer and location tracking
	 */
	public void updateOdometer() {
		double v_x = odometer.getXVelocity();
		double v_y = odometer.getYVelocity();
		double v_h = odometer.getAngularVelocity();
		robotPosition.update(v_x, v_y, v_h);
	}

	/**
	 * Drive to a specific location and orientation
	 */
	public void driveTo(double x, double y, double h) {
		fieldPID.setSetpointAndEnable(new Point(x, y, h));
	}
	
	/**
	 * Returns whether the robot has reached its setpoint
	 * @return true if yes
	 */
	public boolean isOnTarget() {
		return fieldPID.isOnTarget();
	}
	
	/**
	 * Return the values last sent to the motors
	 * @return {x, y, heading}
	 */
	public double[] getOutput() {
		double[] r = {x, y, h};
		return r;
	}
	
    public void initDefaultCommand() { }
}

