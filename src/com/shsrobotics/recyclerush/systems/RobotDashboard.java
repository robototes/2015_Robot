package com.shsrobotics.recyclerush.systems;

import com.shsrobotics.recyclerush.Hardware;

public class RobotDashboard implements Hardware {
	
	public static void updateView() {
		// DRIVE SYSTEM temp and current
		dashboard.putNumber(Dashboard.FL_TALON_TEMP, Wheels.frontLeft.getTemp());
		dashboard.putNumber(Dashboard.FL_TALON_CURR, Wheels.frontLeft.getOutputCurrent());
		dashboard.putNumber(Dashboard.RL_TALON_TEMP, Wheels.rearLeft.getTemp());
		dashboard.putNumber(Dashboard.RL_TALON_CURR, Wheels.rearLeft.getOutputCurrent());
		dashboard.putNumber(Dashboard.FR_TALON_TEMP, Wheels.frontRight.getTemp());
		dashboard.putNumber(Dashboard.FR_TALON_CURR, Wheels.frontRight.getOutputCurrent());
		dashboard.putNumber(Dashboard.RR_TALON_TEMP, Wheels.rearRight.getTemp());
		dashboard.putNumber(Dashboard.RR_TALON_CURR, Wheels.rearRight.getOutputCurrent());
		// PDP info
		dashboard.putNumber(Dashboard.PDP_TEMP, PDP.getTemperature());
		dashboard.putNumber(Dashboard.PDP_VOLT, PDP.getVoltage());
	}
}