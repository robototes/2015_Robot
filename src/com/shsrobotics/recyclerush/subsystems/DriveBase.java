package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.library.fieldpositioning.PID2DOutput;
import com.shsrobotics.library.fieldpositioning.Point;

import static com.shsrobotics.recyclerush.Hardware.IDriveBase.*;

import com.shsrobotics.recyclerush.Maps;
import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drive base
 */
public class DriveBase extends Subsystem implements PID2DOutput, Maps {
    
	static final int clicksPerRevolution = 48;
	static final double TOLERANCE = 4.0;
	static final double P = 0.15; // TODO: REFLECT FOR ALL
//	static final double P_fr = 0.05;
//	static final double P_rl = 0.05;
//	static final double P_rr = 0.05;
	static final double I = 0.0;
	static final double D = 0.0;
	static final double F = 0;
	
	public static final double SMOOTHING_X = 15;
	public static final double SMOOTHING_Y = 15;
	public static final double SMOOTHING_Z = 15;
	public static final double SLOW_DRIVE_SCALE = 0.63; // so that when cubed it's 25%
	double scale_x = 1.0;
	double scale_y = 1.0;
	double scale_z = 1.0;
	
	static final double P_X = 0.0; // TODO: PID CONSTANTS
	static final double I_X = 0.0;
	static final double D_X = 0.0;
	static final double P_Y = 0.0;
	static final double I_Y = 0.0;
	static final double D_Y = 0.0;
	static final double P_H = 0.028;
	static final double I_H = 0.0024;
	static final double D_H = 0;
	
	private double x, y, h;
	
	public DriveBase() { // TODO: UNCOMMENT
//		frontLeft.changeControlMode(ControlMode.Speed);
//		frontRight.changeControlMode(ControlMode.Speed);
//		rearLeft.changeControlMode(ControlMode.Speed);
//		rearRight.changeControlMode(ControlMode.Speed);
//		
//		frontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		frontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		rearLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		rearRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		
//		frontLeft.setPID(P, I, D);
//			frontLeft.setF(F);
//		frontRight.setPID(P, I, D);
//			frontRight.setF(F);
//		rearLeft.setPID(P, I, D);
//			rearLeft.setF(F);
//		rearRight.setPID(P, I, D);
//			rearRight.setF(F);
//		
//		frontLeft.enableControl();
//		frontRight.enableControl();
//		rearLeft.enableControl();
//		rearRight.enableControl();
		
//		fieldPID.setPIDX(P_X, I_X, D_X);
//		fieldPID.setPIDY(P_Y, I_Y, D_Y);
//		fieldPID.setPIDZ(P_H, I_H, D_H);

		alignToFieldPID.setPID(P_H, I_H, D_H);
		alignToFieldPID.setAbsoluteTolerance(TOLERANCE);
		alignToFieldPID.setOutputRange(-1.0, 1.0);
		
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		
		robotDrive.setExpiration(1.0);
	}
	
	/**
	 * Drive the robot
	 * @param x strafe speed
	 * @param y for/rev speed
	 * @param z rotation speed
	 */
	public void drive(double x, double y, double z) {
		robotDrive.mecanumDrive_Cartesian(x*x*x, -y*y*y, z*z*z, 0.0);

		this.x = x;
		this.y = y;
		this.h = z;
	}
	
	/**
	 * Update the odometer and location tracking
	 */
	public void updateOdometer() {
		double[] v = odometer.get(); // TODO: finalize and uncomment
//		double v_x = odometer.getXVelocity();
//		double v_y = odometer.getYVelocity();
//		double v_h = odometer.getAngularVelocity();
//		double v_x = v[0];
//		double v_y = v[1];
//		double v_h = v[2];
//		robotPosition.update(v_x, v_y, v_h);
	}

	/**
	 * Drive to a specific location and orientation
	 */
	public void driveTo(double x, double y, double h) {
//		fieldPID.setSetpointAndEnable(new Point(x, y, h));
	}
	
	/**
	 * Returns whether the robot has reached its setpoint
	 * @return true if yes
	 */
	public boolean isOnTarget() {
//		return fieldPID.isOnTarget(); // TODO: uncomment
		return false;
	}
	
	/**
	 * Get X position of robot
	 * @return position
	 */
	public double getXPosition() {
		return odometer.get()[0];
	}
	
	/**
	 * Get Y position of robot
	 * @return position
	 */
	public double getYPosition() {
		return odometer.get()[1];
	}
	
	/**
	 * Get heading of robot
	 * @return heading
	 */
	public double getHeading() {
		return odometer.get()[2];
	}
	
	/**
	 * Return the values last sent to the motors
	 * @return {x, y, heading}
	 */
	public double[] getOutput() {
		double[] r = {x, y, h};
		return r;
	}
	
	/**
	 * Update smoothing constants to prevent catastrophe
	 * @param held if RC claw button to smooth drive is held
	 * @param objects the number of objects in possession
	 */
	public void updateSmoothingAndScale(boolean held, int objects) {
		if (held) {
			driverJoystick.setSmoothing(SMOOTHING_X, SMOOTHING_Y, SMOOTHING_Z*5);
			scale_x = 1.0;
			scale_y = 1.0;
			scale_z = SLOW_DRIVE_SCALE;
		} else if (objects > 0) {
			scale_x = 0.794;
			scale_y = SLOW_DRIVE_SCALE;
			scale_z = SLOW_DRIVE_SCALE;
			double ySmoothingFactor = 5;
			
			if (objects == 1) {
				ySmoothingFactor = 3;
			} else {
				scale_x = SLOW_DRIVE_SCALE;
			}
			
			driverJoystick.setSmoothing(SMOOTHING_X * 4, SMOOTHING_Y * ySmoothingFactor, SMOOTHING_Z * 4);
		} else {
			driverJoystick.setSmoothing(SMOOTHING_X, SMOOTHING_Y, SMOOTHING_Z);
			scale_x = 1.0;
			scale_y = 1.0;
			scale_z = 1.0;
		}
	}
	
    public void initDefaultCommand() { }
}

