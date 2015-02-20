package com.shsrobotics.recyclerush.subsystems;

import static com.shsrobotics.recyclerush.Hardware.IDriveBase.gyroscope;
import static com.shsrobotics.recyclerush.Hardware.*;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class TurnPID extends PIDController {

	static TurnPIDOutput output = new TurnPIDOutput();
	
	public TurnPID() {
		super(DriveBase.P_H, DriveBase.I_H, DriveBase.D_H, gyroscope, output);
	}
	
	public void setForward(double f) {
		output.setForward(f);
	}
	
	static class TurnPIDOutput implements PIDOutput {
		double y = 0.0;
		
		public void pidWrite(double output) {
			driveBase.drive(0, y, output);
		}
		
		public void setForward(double f) {
			y = f;
		}
	}
}
