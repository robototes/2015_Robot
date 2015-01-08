package com.shsrobotics.recyclerush.systems;

import com.shsrobotics.recyclerush.Hardware;

public class DriveBase implements Hardware {
	public static void drive() {
		// get values and cube them.
		double x = driverJoystick.outputX();
		double y = driverJoystick.outputY();
		double z = driverJoystick.outputZ();
		x = x*x*x;
		y = y*y*y;
		z = z*z*z;
		// apply drive
		robotDrive.mecanumDrive_Cartesian(x, y, z, noGyroscope);
	}
}
