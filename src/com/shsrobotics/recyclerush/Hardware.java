package com.shsrobotics.recyclerush;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface Hardware extends Maps {
	
	public static class claw_Solenoid{
		public static DoubleSolenoid both = new DoubleSolenoid(solenoidBottom, solenoidTop);
	}
}
