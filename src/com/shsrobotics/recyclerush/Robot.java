package com.shsrobotics.recyclerush;

import com.shsrobotics.library.FRCRobot;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;
import com.shsrobotics.recyclerush.commands.CloseGripper;
import com.shsrobotics.recyclerush.commands.OpenGripper;
import com.shsrobotics.recyclerush.commands.Release;
import com.shsrobotics.recyclerush.commands.SetElevator;
import com.shsrobotics.recyclerush.stacks.StackManager;
import com.shsrobotics.recyclerush.stacks.ToteIn;
import com.shsrobotics.recyclerush.stacks.ToteOut;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends FRCRobot implements Hardware {

	Command autonomousCommand;
	
    public void robotInit() {
    	/*
    	 * INITIALIZATION
    	 */
    	super.robotInit();
    }
	
	public void disabledPeriodic() {
		/*
		 * COMMAND-BASED SCHEDULER
		 */
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	/*
    	 * AUTONOMOUS SELECTION
    	 */
    	autonomousCommand = dashboard.getAutonomous();
    	autonomousCommand.start();
    }

    public void autonomousPeriodic() {
    	/*
    	 * COMMAND-BASED SCHEDULER
    	 */
    	Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	/*
    	 * CANCELLING AUTONOMOUS ROUTINE
    	 */
    	if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        /*
         * COMMAND-BASED SCHEDULER
         */
    	Scheduler.getInstance().run();
    	
    	/*
    	 * DRIVING
    	 */
//        driveBase.drive(driverJoystick.outputX(), driverJoystick.outputY(), driverJoystick.outputZ());
    	
    	/*
    	 * AUTOMATIC STACK MANAGMENT AND INTAKE
    	 */
//        Buttons.autoIntake.whenPressed(new AutoIntake());	
//        	Buttons.autoIntake.whenReleased(new CancelAutoIntake());
//        Buttons.release.whenPressed(new Release());
//        // track object possession
//        if (ToteIn.get()) {
//        	StackManager.totes++;
//        } else if (ToteOut.get()) {
//        	StackManager.totes = 0;
//        }
        
        /*
         * GRIPPER
         */
        Buttons.gripperOpen.whenPressed(new OpenGripper());
        Buttons.gripperClose.whenPressed(new CloseGripper());
        
        /*
         * ELEVATOR
         */
//        double elevatorLevel = -2 * (driverJoystick.getThrottle() - 1);
//        if (Buttons.setElevatorDiscrete.pressed()) {
//        	new SetElevator(Math.floor(elevatorLevel));
//        } else if (Buttons.setElevatorContinuous.pressed()) {
//        	new SetElevator(elevatorLevel).start();
//        }
//        if (elevator.isAtBottom()) {
//        	elevator.reset();
//        }
        
        /*
         * ROLLERS
         */
//        if (Buttons.rollersIn.held()) {
//        	rollerIntake.in();
//        } else if (Buttons.rollersOut.held()) {
//        	rollerIntake.out();
//        } else {
//        	rollerIntake.stop();
//        }
    }
    
}
