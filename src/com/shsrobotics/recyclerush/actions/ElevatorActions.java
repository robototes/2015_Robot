package com.shsrobotics.recyclerush.actions;

import com.shsrobotics.library.PermissionAction;
import com.shsrobotics.recyclerush.Hardware;

import edu.wpi.first.wpilibj.DriverStation;

public class ElevatorActions implements Hardware {
	public static PermissionAction autoMoveUp = new PermissionAction((args)->{
		if ( args.length == 0 ) {
			// Sybsystems.elevator.moveUpOne();
		}
		else if ( args[0] instanceof Double ) {
			//Subustems.elevator.moveUp((Double)args[0]);
		}
		else {
			String j;
			j = args[0].getClass().getCanonicalName();
			if ( j == null ) j = "$anonymous";
			System.err.println("Unknown parameter type: ElevatorActions.autoMoveUp("+j+")");
		}
	}, (args)->{
		return DriverStation.getInstance().isAutonomous() || Buttons.elevatorPermButton.held();	// TODO elevator UP predicate
	});
	
	
	public static PermissionAction autoMoveDown = new PermissionAction((args)->{
		
	},(args)->{
		return DriverStation.getInstance().isAutonomous() || Buttons.elevatorPermButton.held();
	});
	
	public static void test(Object... args) {
		args[0].getClass().getCanonicalName();
	}
}
