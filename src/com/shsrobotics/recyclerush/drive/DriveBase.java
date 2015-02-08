package com.shsrobotics.recyclerush.drive;

import java.util.Map;

import com.shsrobotics.library.Subsystem;
import com.shsrobotics.library.SubsystemState;
import com.shsrobotics.library.fieldpositioning.RobotPosition;
import com.shsrobotics.recyclerush.Robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.hal.CanTalonSRX;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DriveBase implements Subsystem {
	
	boolean estop = false;
	CANTalon fl, fr, bl, br;
	RobotDrive drive;
	Joystick driveStick;
	RobotPosition robot;
	
	public DriveBase(CANTalon fl, CANTalon fr, CANTalon bl, CANTalon br, Joystick driveStick, RobotPosition robot) {
		if ( !Robot.networkTable.containsKey("PID_P") )
			Robot.networkTable.putNumber("PID_P", .1);
		if ( !Robot.networkTable.containsKey("PID_I") )
			Robot.networkTable.putNumber("PID_I", .001);
		if ( !Robot.networkTable.containsKey("PID_D") )
			Robot.networkTable.putNumber("PID_D", 0);
		if ( !Robot.networkTable.containsKey("SETPOINT") )
			Robot.networkTable.putNumber("SETPOINT", 0);
		
		this.fl = fl;
		this.fr = fr;
		this.bl = bl;
		this.br = br;
		
		fr.reverseOutput(true);
		fr.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		fr.changeControlMode(ControlMode.Speed);
		//fr.setPID(Robot.networkTable.getNumber("PID_P"), Robot.networkTable.getNumber("PID_I"), Robot.networkTable.getNumber("PID_D"), 0, 100, 40, 0);
		fr.enableControl();

		fl.reverseOutput(true);
		fl.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		fl.changeControlMode(ControlMode.Speed);
		//fl.setPID(Robot.networkTable.getNumber("PID_P"), Robot.networkTable.getNumber("PID_I"), Robot.networkTable.getNumber("PID_D"), 0, 100, 40, 0);
		fl.enableControl();

		br.reverseOutput(true);
		br.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		br.changeControlMode(ControlMode.Speed);
		//br.setPID(Robot.networkTable.getNumber("PID_P"), Robot.networkTable.getNumber("PID_I"), Robot.networkTable.getNumber("PID_D"), 0, 100, 40, 0);
		br.enableControl();
		
		bl.reverseOutput(true);
		bl.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		bl.changeControlMode(ControlMode.Speed);
		//bl.setPID(Robot.networkTable.getNumber("PID_P"), Robot.networkTable.getNumber("PID_I"), Robot.networkTable.getNumber("PID_D"), 0, 100, 40, 0);
		bl.enableControl();
		
		
		//drive = new RobotDrive(fl, bl, fr, br);
		this.driveStick = driveStick;
		this.robot = robot;
	}
	
	/**
	 * sets pid values for speed control
	 * @param p
	 * @param i
	 * @param d
	 * @param f
	 * @param izone
	 * @param ramp
	 * @param profile
	 */
	public void setPIDFZR(double p, double i, double d, double f, int izone, double ramp, int profile) {
		fr.setPID(p, i, d, f, izone, ramp, profile);
		fl.setPID(p, i, d, f, izone, ramp, profile);
		br.setPID(p, i, d, f, izone, ramp, profile);
		bl.setPID(p, i, d, f, izone, ramp, profile);
	}
	
	public void driveForward(double speed) {
		drive.arcadeDrive(speed, 0);
	}
	
	private double cube(double d) {
		return d*d*d;
	}
	
	public void drive() {
		if ( !estop )
			drive.mecanumDrive_Cartesian(cube(driveStick.getX()), cube(driveStick.getY()), driveStick.getZ(), 0);
		//fr.setPosition(pos);
	}
	
	
	@Override
	public void estop() {
		estop = true;
	}

	@Override
	public SubsystemState getState() {
		if ( estop )
			return SubsystemState.ESTOP;
		//else if ( robot.getPID().)
		else
			return SubsystemState.IDLE;
	}

	@Override
	public void returnState(Map<String, Object> keyvalue) {
		keyvalue.put(Messages.getString("DriveBase.0"), fr.getOutputCurrent()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.1"), fr.getOutputVoltage()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.2"), fr.getTemp()); //$NON-NLS-1$

		keyvalue.put(Messages.getString("DriveBase.3"), fl.getOutputCurrent()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.4"), fl.getOutputVoltage()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.5"), fl.getTemp()); //$NON-NLS-1$

		keyvalue.put(Messages.getString("DriveBase.6"), br.getOutputCurrent()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.7"), br.getOutputVoltage()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.8"), br.getTemp()); //$NON-NLS-1$

		keyvalue.put(Messages.getString("DriveBase.9"), bl.getOutputCurrent()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.10"), bl.getOutputVoltage()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.11"), bl.getTemp()); //$NON-NLS-1$
		
		keyvalue.put(Messages.getString("DriveBase.12"), robot.getX()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.13"), robot.getY()); //$NON-NLS-1$
		keyvalue.put(Messages.getString("DriveBase.14"), robot.getHeading()); //$NON-NLS-1$
		
		keyvalue.put(Messages.getString("DriveBase.15"), getState()); //$NON-NLS-1$
		
	}

}
