package com.shsrobotics.recyclerush.drive;

import com.shsrobotics.library.Subsystem;
import com.shsrobotics.library.SubsystemState;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveBase implements Subsystem {
	
	boolean estop = false;
	CANTalon fl, fr, bl, br;
	RobotDrive drive;
	Joystick driveStick;
	
	public DriveBase(CANTalon fl, CANTalon fr, CANTalon bl, CANTalon br, Joystick driveStick) {
		this.fl = fl;
		this.fr = fr;
		this.bl = bl;
		this.br = br;
		drive = new RobotDrive(fl, bl, fr, br);
		this.driveStick = driveStick;
	}
	
	private double cube(double d) {
		return d*d*d;
	}
	
	public void drive() {
		if ( !estop )
			drive.mecanumDrive_Cartesian(cube(driveStick.getX()), cube(driveStick.getY()), driveStick.getZ(), 0);
	}
	
	
	@Override
	public void estop() {
		estop = true;
	}

	@Override
	public SubsystemState getState() {
		if ( estop )
			return SubsystemState.ESTOP;
		else
			return SubsystemState.IDLE;
	}

}
