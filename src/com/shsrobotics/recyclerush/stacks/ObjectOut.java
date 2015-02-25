package com.shsrobotics.recyclerush.stacks;

import static com.shsrobotics.recyclerush.Hardware.*;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.RollerIntake.RollerState;
import com.shsrobotics.recyclerush.subsystems.Gripper.GripperState;

import static com.shsrobotics.recyclerush.stacks.ObjectOut.ObjectState.*;

/**
 * Triggers when a tote enters the possesion of the robot
 */
public class ObjectOut {
	
	protected enum ObjectState {
		START, 
		ELEV_GRND,
		GRIPPER_OPEN,
		ROLLERS_ON, 
		OBJECT_OUT,
		END;
	}
	
	public static ObjectState state = START;
	
    public static boolean get() {
        switch (state) {
        	case START:
        		if (elevator.getPosition() <= 0.05) state = ELEV_GRND;
        		break;
        	case ELEV_GRND:
        		if (gripper.getState() == GripperState.OPEN) state = GRIPPER_OPEN;
        		break;
        	case GRIPPER_OPEN:
        		if (rollerIntake.getState() == RollerState.OUT) state = ROLLERS_ON;
        		break;
        	case ROLLERS_ON:
        		if (!rollerIntake.isToteIn()) state = OBJECT_OUT;
        		break;
        	case OBJECT_OUT:
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
