package com.shsrobotics.recyclerush.subsystems;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.auto.RobotSet;
import com.shsrobotics.recyclerush.auto.ToteSet;
import com.shsrobotics.recyclerush.stacks.StackManager;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The robot software dashboard
 */
public class RobotDashboard extends Subsystem implements Hardware.IDashboard {
    
	/**
	 * Update the dashboard
	 */
    public void update() {
    	// robot only
    	dashboard.putNumber(Dashboard.PDP_CURR, PDP.getTotalCurrent());
    	dashboard.putNumber(Dashboard.PDP_TEMP, PDP.getTemperature());
    	dashboard.putNumber(Dashboard.PDP_POWER, PDP.getTotalPower());
    	dashboard.putNumber(Dashboard.PDP_VOLT, PDP.getVoltage());
    	// specific motors
    	dashboard.putNumber(Dashboard.ELEV_A_CURR, PDP.getCurrent(PDPPorts.ELEV_MOTOR_A));
    	dashboard.putNumber(Dashboard.ELEV_B_CURR, PDP.getCurrent(PDPPorts.ELEV_MOTOR_B));
    	dashboard.putNumber(Dashboard.GRIPPER_CURR, PDP.getCurrent(PDPPorts.GRIPPER_MOTOR));
    	// totes and stack management
    	dashboard.putNumber(Dashboard.TOTE_COUNT, StackManager.totes);
    }
    
    /**
     * Get the selected autonomous command
     * @return the command to run
     */
    public Command getAutonomous() {
    	int choice = (int) dashboard.getNumber(Dashboard.AUTO_MODE);
    	Command r;
    	
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

    public void initDefaultCommand() { }
}

