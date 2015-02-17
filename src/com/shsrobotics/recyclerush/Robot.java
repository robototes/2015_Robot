package com.shsrobotics.recyclerush;


import java.util.LinkedList;

import com.shsrobotics.library.FRCRobot;
import com.shsrobotics.library.Subsystem;
import com.shsrobotics.recyclerush.auto.Autonomous2015;
import com.shsrobotics.recyclerush.auto.RobotSet;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;
import com.shsrobotics.recyclerush.commands.CloseGripper;
import com.shsrobotics.recyclerush.commands.EndMatch;
import com.shsrobotics.recyclerush.commands.OpenGripper;
import com.shsrobotics.recyclerush.commands.Release;
import com.shsrobotics.recyclerush.commands.SetElevator;
import com.shsrobotics.recyclerush.stacks.RCIn;
import com.shsrobotics.recyclerush.stacks.StackManager;
import com.shsrobotics.recyclerush.stacks.ToteIn;
import com.shsrobotics.recyclerush.stacks.ObjectOut;
import com.shsrobotics.recyclerush.subsystems.Elevator;
import com.shsrobotics.recyclerush.subsystems.RobotDashboard;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import static com.shsrobotics.recyclerush.Hardware.*;

public class Robot extends FRCRobot {

	Autonomous2015 autonomousCommand;
	
	boolean hasStartedEndMatchRoutine = false;
	
    public void robotInit() {
    	/*
		 * SYSTEM INITIALIZATION
		 */
		Hardware.initialize();
    	
		/*
		 * CAN INITIALIZATION
		 */
		System.out.println("VOLTAGE: " + IDashboard.pdp.getVoltage());
		System.out.println("CAN TEST (FL): " + RobotDashboard.toFarenheit(IDriveBase.frontLeft.getTemp()));
		System.out.println("CAN TEST (FR): " + RobotDashboard.toFarenheit(IDriveBase.frontRight.getTemp()));
		System.out.println("CAN TEST (RL): " + RobotDashboard.toFarenheit(IDriveBase.rearLeft.getTemp()));
		System.out.println("CAN TEST (RR): " + RobotDashboard.toFarenheit(IDriveBase.rearRight.getTemp()));
		System.out.println();

		System.out.println("=== ROBOT IS READY ===");
    }
	
	public void disabledPeriodic() {
		/*
		 * COMMAND-BASED SCHEDULER
		 */
		Scheduler.getInstance().run();
		
		/*
		 * STOP ELEVATOR POSITION SETTING
		 */
		elevator.setManual(true);
	}
	
    public void autonomousInit() {
    	/*
    	 * AUTONOMOUS SELECTION
    	 */
//    	autonomousCommand = dashboard.getAutonomous();
    	autonomousCommand = new RobotSet();
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
		driveBase.updateSmoothingAndScale(Buttons.rcClawDrive.held(), StackManager.getObjects());
		driveBase.drive(driverJoystick.outputX(), driverJoystick.outputY(), driverJoystick.outputZ());
        driveBase.updateOdometer();
    	
    	/*
    	 * AUTOMATIC STACK MANAGMENT AND INTAKE
    	 */
        if (Buttons.autoIntake.pressed()) new AutoIntake().start();	
        if (Buttons.autoIntake.released()) new CancelAutoIntake().start();
        if (Buttons.release.pressed()) new Release().start();
        // track object possession
        if (ToteIn.get()) {
        	StackManager.totes++;
        } else if (RCIn.get()) {
        	StackManager.hasRC = true;
        } else if (ObjectOut.get()) {
        	StackManager.totes = 0;
        	StackManager.hasRC = false;
        }
        
        /*
         * GRIPPER
         */
        if (!Buttons.disableGripper.held()) {
        	if (Buttons.gripperOpen.pressed()) new OpenGripper().start();
        	if (Buttons.gripperClose.pressed()) new CloseGripper().start();
        }
        
        /*
         * ELEVATOR
         */
        if (!Buttons.disableElevator.held()) {
	        double elevatorLevel = 5 * (-secondaryJoystick.getRawAxis(Buttons.elevatorPosition) + 0.5) / 1.5;
	        if (Buttons.setElevator.pressed()) {
	        	new SetElevator(Math.floor(Math.abs(elevatorLevel))).start();
	        } 
	        
	        if (Buttons.elevatorUp.held()) {
	        	elevator.setManual(true);
	        	elevator.up();
	        } else if (Buttons.elevatorDown.held()) {
	        	elevator.setManual(true);
	        	elevator.down();
	        } else if (elevator.isManual()) {
	        	elevator.stop();
	        }
        }
        
        if (elevator.isAtBottom()) {
        	elevator.reset();
        }
        
        /*
         * ROLLERS
         */
        if (!Buttons.disableRollers.held()) {
	        if (Buttons.rollersIn.held()) {
	        	rollerIntake.setManual(true);
	        	rollerIntake.in();
	        } else if (Buttons.rollersOut.held()) {
	        	rollerIntake.setManual(true);
	        	rollerIntake.out();
	        } else if (rollerIntake.isManual()){
	        	rollerIntake.stop();
	        }
        }
        
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
        
		/*
		 * DASHBOARD
		 */
		dashboard.update();
		
		/*
		 * END-OF-MATCH MOTION
		 */
		if (DriverStation.getInstance().getMatchTime() > END_MATCH_MOTION_TIME && !hasStartedEndMatchRoutine) {
			hasStartedEndMatchRoutine = true;
			new EndMatch().start();
			System.out.println("end match started");	// TODO remove line
		}
		
		if (driverJoystick.getRawButton(3)) {
			IDriveBase.odometer.reset();
		}
		
		/*
		 * POWER MANAGEMENT
		 */
		dashboard.manageFaults();
    }
    
}
