package com.shsrobotics.recyclerush.actions;

import com.shsrobotics.library.PermissionAction;
import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.Robot.Buttons;

public class ForkActions implements Hardware {
	
	public static PermissionAction autoCloseAction = new PermissionAction((args)->{
		Subsystems.gripper.close();
	},(args)->{
		return Hardware.Subsystems.gripper.controlsTote() && Buttons.autoIntake.held();
	});
}
