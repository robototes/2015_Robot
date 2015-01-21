package com.shsrobotics.recyclerush.actions;

import com.shsrobotics.library.PermissionAction;

public class ForkActions {
	public static PermissionAction autoCloseAction = new PermissionAction((args)->{
		
	},(args)->{
		return true;	// TODO fork close predicate
	});
}
