package com.shsrobotics.recyclerush;

import com.shsrobotics.library.FRCRobot;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;
import com.shsrobotics.recyclerush.commands.CloseGripper;
import com.shsrobotics.recyclerush.commands.OpenGripper;
import com.shsrobotics.recyclerush.commands.Release;
import com.shsrobotics.recyclerush.commands.SetElevator;

import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends FRCRobot implements Hardware {

    public void robotInit() {
    	super.robotInit();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() { }

    public void autonomousPeriodic() { }

    public void teleopInit() { }

    public void teleopPeriodic() {
        /*
         * COMMAND-BASED SCHEDULER
         */
    	Scheduler.getInstance().run();
    	
    	/*
    	 * DRIVING
    	 */
        driveBase.drive(driverJoystick.outputX(), driverJoystick.outputY(), driverJoystick.outputZ());
    	
    	/*
    	 * AUTOMATIC STACK MANAGMENT AND INTAKE
    	 */
        Buttons.autoIntake.whenPressed(new AutoIntake());	
        	Buttons.autoIntake.whenReleased(new CancelAutoIntake());
        Buttons.release.whenPressed(new Release());
        
        /*
         * GRIPPER
         */
        Buttons.gripperOpen.whenPressed(new OpenGripper());
        Buttons.gripperClose.whenPressed(new CloseGripper());
        
        /*
         * ELEVATOR
         */
        double elevatorLevel = -2 * (driverJoystick.getThrottle() - 1);
        if (Buttons.setElevatorDiscrete.pressed()) {
        	new SetElevator(Math.floor(elevatorLevel));
        } else if (Buttons.setElevatorContinuous.pressed()) {
        	new SetElevator(elevatorLevel).start();
        }
        
        /*
         * ROLLERS
         */
        if (Buttons.rollersIn.pressed()) {
        	rollerIntake.in();
        } else if (Buttons.rollersOut.pressed()) {
        	rollerIntake.out();
        } else {
        	rollerIntake.stop();
        }
    }
    
}
