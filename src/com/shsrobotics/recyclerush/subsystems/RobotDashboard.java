package com.shsrobotics.recyclerush.subsystems;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.auto.Autonomous2015;
import com.shsrobotics.recyclerush.auto.RobotSet;
import com.shsrobotics.recyclerush.auto.ToteSet;
import com.shsrobotics.recyclerush.stacks.StackManager;
import static com.shsrobotics.recyclerush.Hardware.IDriveBase.*;
import static com.shsrobotics.recyclerush.Hardware.*;
import static com.shsrobotics.recyclerush.Hardware.IDashboard.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.PDPJNI;

/**
 * The robot software dashboard
 */
public class RobotDashboard extends Subsystem {
    
	/**
	 * Update the dashboard
	 */
    public void update() {
    	// robot only
    	table.putNumber(Dashboard.PDP_CURR, pdp.getTotalCurrent());
    	table.putNumber(Dashboard.PDP_TEMP, toFarenheit(pdp.getTemperature()));
    	table.putNumber(Dashboard.PDP_POWER, pdp.getTotalPower());
    	table.putNumber(Dashboard.PDP_VOLT, pdp.getVoltage());
    	// specific motors
    	table.putNumber(Dashboard.ELEV_CURR, pdp.getCurrent(PDPPorts.ELEV_MOTOR_A));
    	table.putNumber(Dashboard.GRIPPER_CURR, pdp.getCurrent(PDPPorts.GRIPPER_MOTOR));
    	// totes and stack management
    	table.putNumber(Dashboard.TOTE_COUNT, StackManager.totes);
    	table.putBoolean(Dashboard.HAS_RC, StackManager.hasRC);
    	table.putNumber(Dashboard.ELEVATOR_POSITION, elevator.getPosition());
    	// robot position
    	table.putNumber(Dashboard.X_POSITION, driveBase.getXPosition());
    	table.putNumber(Dashboard.Y_POSITION, driveBase.getYPosition());
    	table.putNumber(Dashboard.HEADING, driveBase.getHeading());
    	// global
    	table.putNumber(Dashboard.SOFTWARE_VERSION, SOFTWARE_VERSION);
    }
    
    /**
     * Get the selected autonomous command
     * @return the command to run
     */
    public Autonomous2015 getAutonomous() {
    	int choice = (int) table.getNumber(Dashboard.AUTO_MODE);
    	Autonomous2015 r;
    	
    	switch(choice) {
    		case Autonomous.ROBOT_SET:
    			r = new RobotSet();
    			break;
    		case Autonomous.TOTE_SET:
    			r = new ToteSet();
    			break;
    		case Autonomous.STACK_SET:
    			r = new RobotSet();
    			break;
    		case Autonomous.CENTER_CONTAINERS:
    			r = new RobotSet();
    			break;
			default:
				r = new RobotSet();
				break;
    	}
    	
    	return r;
    }
    
    public boolean disableGripper() {
    	return table.getBoolean(Dashboard.DISABLE_GRIPPER, false);
    }
    
    public boolean disableRollers() {
    	return table.getBoolean(Dashboard.DISABLE_ROLLERS, false);
    }
    
    public boolean disableElevator() {
    	return table.getBoolean(Dashboard.DISABLE_ELEVATOR, false);
    }
    
    public boolean disableClaw() {
    	return table.getBoolean(Dashboard.DISABLE_CLAW, false);
    }
    
    public boolean disableDrive() {
    	return table.getBoolean(Dashboard.DISABLE_DRIVE, false);
    }

    public void initDefaultCommand() { }
    
    /**
     * Convert C to F
     * @param celsius
     * @return farenheit
     */
    public static double toFarenheit(double celsius) {
    	return celsius*1.8 + 32;
    }

    /**
     * Manage stick faults and other power issues
     */
	public void manageFaults() {
		ByteBuffer status = ByteBuffer.allocateDirect(4);
		status.order(ByteOrder.LITTLE_ENDIAN);
		PDPJNI.clearPDPStickyFaults(status.asIntBuffer());
		status.rewind();
		byte[] res = new byte[4];
		status.get(res);
		if (res[0] + res[1] + res[2] + res[3] == 0) return;
		System.out.println(res[0] + "," + res[1] + "," + res[2] + "," + res[3]);
	}
}

