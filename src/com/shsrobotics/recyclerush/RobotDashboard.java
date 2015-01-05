package com.shsrobotics.recyclerush;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotDashboard implements Hardware {
	
	public static void updateView() {
		// DRIVE SYSTEM temp and current
		SmartDashboard.putNumber(Dashboard.FL_TALON_TEMP, Wheels.frontLeft.getTemp());
		SmartDashboard.putNumber(Dashboard.FL_TALON_CURR, Wheels.frontLeft.getOutputCurrent());
		SmartDashboard.putNumber(Dashboard.RL_TALON_TEMP, Wheels.rearLeft.getTemp());
		SmartDashboard.putNumber(Dashboard.RL_TALON_CURR, Wheels.rearLeft.getOutputCurrent());
		SmartDashboard.putNumber(Dashboard.FR_TALON_TEMP, Wheels.frontRight.getTemp());
		SmartDashboard.putNumber(Dashboard.FR_TALON_CURR, Wheels.frontRight.getOutputCurrent());
		SmartDashboard.putNumber(Dashboard.RR_TALON_TEMP, Wheels.rearRight.getTemp());
		SmartDashboard.putNumber(Dashboard.RR_TALON_CURR, Wheels.rearRight.getOutputCurrent());
		// PDP info
		SmartDashboard.putNumber(Dashboard.PDP_TEMP, PDP.getTemperature());
		SmartDashboard.putNumber(Dashboard.PDP_VOLT, PDP.getVoltage());
	}
}
