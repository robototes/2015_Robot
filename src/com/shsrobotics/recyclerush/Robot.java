package com.shsrobotics.recyclerush;

import com.shsrobotics.library.FRCRobot;
import com.shsrobotics.recyclerush.auto.Autonomous2015;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;
import com.shsrobotics.recyclerush.commands.CloseGripper;
import com.shsrobotics.recyclerush.commands.OpenGripper;
import com.shsrobotics.recyclerush.commands.Release;
import com.shsrobotics.recyclerush.commands.SetElevator;
import com.shsrobotics.recyclerush.stacks.StackManager;
import com.shsrobotics.recyclerush.stacks.ToteIn;
import com.shsrobotics.recyclerush.stacks.ToteOut;

import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends FRCRobot implements Hardware {

	Autonomous2015 autonomousCommand;
	
    public void robotInit() {
    	/*
    	 * INITIALIZATION
    	 */
    	super.robotInit();
    	
    	/*
		 * COMMAND-BASED SCHEDULER
		 */
		Scheduler.getInstance().run();
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
    	IDriveBase.robotPosition.x = autonomousCommand.getStartingX();
    	IDriveBase.robotPosition.y = autonomousCommand.getStartingY();
    	IDriveBase.robotPosition.h = autonomousCommand.getStartingHeading();
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

    public void teleopPeriodic() { // TODO: UNCOMMENT
        /*
         * COMMAND-BASED SCHEDULER
         */
    	Scheduler.getInstance().run();
    	
    	/*
    	 * DRIVING
    	 */
		driveBase.drive(driverJoystick.outputX(), driverJoystick.outputY(), driverJoystick.outputZ());
//        driveBase.updateOdometer();
    	
    	
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
//        Buttons.gripperOpen.whenPressed(new OpenGripper());
//        Buttons.gripperClose.whenPressed(new CloseGripper());
        
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
        
        /*
         * RC CLAW
         */
        if (Buttons.clawUp.held()) {
        	claw.up();
        } else if (Buttons.clawDown.held()) {
        	claw.down();
        } else {
        	claw.stop();
        }
        
    }
    
}
