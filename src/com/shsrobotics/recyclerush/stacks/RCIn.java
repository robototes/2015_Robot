package com.shsrobotics.recyclerush.stacks;

import static com.shsrobotics.recyclerush.Hardware.*;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.RollerIntake.RollerState;
import com.shsrobotics.recyclerush.subsystems.Gripper.GripperState;

import static com.shsrobotics.recyclerush.stacks.RCIn.RCState.*;

/**
 * Triggers when a tote enters the possesion of the robot
 */
public class RCIn {
	
	protected enum RCState {
		START, 
		ROLLERS_ON, 
		OBJECT_IN,
		ELEV_GRND,
		GRIPPER_CLOSE,
		END;
	}
	
	public static RCState state = START;
	
    public static boolean get() {
        switch (state) {
        	case START:
        		if (rollerIntake.getState() == RollerState.IN) state = ROLLERS_ON;
        		break;
        	case ROLLERS_ON:
        		if (rollerIntake.isRCIn()) state = OBJECT_IN;
        		break;
        	case OBJECT_IN:
        		if (Elevator.LEVEL == 0) state = ELEV_GRND;
        		break;
        	case ELEV_GRND:
        		if (gripper.getState() == GripperState.CLOSED) state = GRIPPER_CLOSE;
        		break;
        	case GRIPPER_CLOSE:
        		state = END;
        		break;
        	case END:
        		state = START;
        		break;
			default:
				state = START;
				break;
        }
    	
    	return state == END;
    }
}
