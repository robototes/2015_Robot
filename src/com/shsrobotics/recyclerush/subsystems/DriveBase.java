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
	static final double P = 0.15; 
	static final double I = 0.0;
	static final double D = 0.0;
	static final double F = 0;
	
	public static final double SMOOTHING_X = 20;
	public static final double SMOOTHING_Y = 20;
	public static final double SMOOTHING_Z = 24;
	public static final double SLOW_DRIVE_SCALE = 0.5; // so that when cubed it's 12.5%
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
	
	public DriveBase() {
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
		double[] v = odometer.get(); 
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
			scale_x = 0.64;
			scale_y = SLOW_DRIVE_SCALE;
			scale_z = SLOW_DRIVE_SCALE;
			driverJoystick.setSmoothing(SMOOTHING_X * 4, SMOOTHING_Y * 5, SMOOTHING_Z * 4);
		} else {
			driverJoystick.setSmoothing(SMOOTHING_X, SMOOTHING_Y, SMOOTHING_Z);
			scale_x = 1.0;
			scale_y = 1.0;
			scale_z = 0.75;
		}
	}
	
    public void initDefaultCommand() { }
}

