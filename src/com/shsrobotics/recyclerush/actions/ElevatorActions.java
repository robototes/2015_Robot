package com.shsrobotics.recyclerush.actions;

import com.shsrobotics.library.PermissionAction;

public class ElevatorActions {
	PermissionAction moveUp = new PermissionAction((args)->{
		
	}, (args)->{
		return true;	// TODO elevator UP predicate
	});
	
	PermissionAction moveUpMany = new PermissionAction((args)->{
		
	}, (args)->{
		return true;	// TODO elevator UP many predicate
	});
	
	PermissionAction moveDown = new PermissionAction((args)->{
		
	},(args)->{
		return true;	// TODO elevator DOWN predicate
	});
	
	PermissionAction moveDownMany = new PermissionAction((args)->{
		
	},(args)->{
		return true;	// TODO elevator DOWN many predicate
	});
	
}
