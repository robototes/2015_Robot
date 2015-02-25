package com.shsrobotics.recyclerush;


import static com.shsrobotics.recyclerush.Hardware.claw;
import static com.shsrobotics.recyclerush.Hardware.dashboard;
import static com.shsrobotics.recyclerush.Hardware.driveBase;
import static com.shsrobotics.recyclerush.Hardware.elevator;
import static com.shsrobotics.recyclerush.Hardware.rollerIntake;
import static com.shsrobotics.recyclerush.Hardware.IDriveBase.gyroscope;
import static com.shsrobotics.recyclerush.Maps.END_MATCH_MOTION_TIME;
import static com.shsrobotics.recyclerush.Maps.driverJoystick;
import static com.shsrobotics.recyclerush.Maps.secondaryJoystick;

import com.shsrobotics.library.FRCRobot;
import com.shsrobotics.recyclerush.Hardware.IDashboard;
import com.shsrobotics.recyclerush.Hardware.IDriveBase;
import com.shsrobotics.recyclerush.Maps.Buttons;
import com.shsrobotics.recyclerush.auto.Autonomous2015;
import com.shsrobotics.recyclerush.commands.AssembleStack;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;
import com.shsrobotics.recyclerush.commands.CloseGripper;
import com.shsrobotics.recyclerush.commands.EndMatch;
import com.shsrobotics.recyclerush.commands.IntakeOne;
import com.shsrobotics.recyclerush.commands.OpenGripper;
import com.shsrobotics.recyclerush.commands.SetElevator;
import com.shsrobotics.recyclerush.commands.TurnTo;
import com.shsrobotics.recyclerush.stacks.ObjectOut;
import com.shsrobotics.recyclerush.stacks.RCIn;
import com.shsrobotics.recyclerush.stacks.StackManager;
import com.shsrobotics.recyclerush.stacks.ToteIn;
import com.shsrobotics.recyclerush.subsystems.RobotDashboard;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends FRCRobot {

	Autonomous2015 autonomousCommand;
	
	boolean hasStartedEndMatchRoutine = false;
	Command activePIDCommand;
	/*
	 * state for elevator position setting
	 * true: macro
	 * false: micro
	 */
	boolean elevatorSetState = true;
	
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
    	
    	/*
    	 * FIELD ALIGNMENT
    	 */
    	IDriveBase.alignToFieldPID.setForward(0.0);
    }

    public void teleopPeriodic() {
        /*
         * COMMAND-BASED SCHEDULER
         */
    	Scheduler.getInstance().run();
    	
    	/*
    	 * DRIVING
    	 */
		driveBase.updateSmoothingAndScale(Buttons.rcClawDrive.held(), StackManager.getObjects());
		if (!IDriveBase.alignToFieldPID.isEnable()) {
			driveBase.drive(driverJoystick.outputX(), driverJoystick.outputY(), driverJoystick.outputZ());
		}
        // align to field
        if (Buttons.alignToField.pressed()) {
        	activePIDCommand = new TurnTo( Math.round(gyroscope.getAngle() / 90) * 90 );
        	activePIDCommand.start();
        } else if (Buttons.alignToField.released()) {
        	activePIDCommand.cancel();
        }
    	
    	/*
    	 * AUTOMATIC STACK MANAGMENT AND INTAKE
    	 */
        if (Buttons.intakeOne.pressed()) new IntakeOne().start();
        if (Buttons.intakeOne.released()) new CancelAutoIntake().start();
        
        if (Buttons.assembleStack.pressed()) new AssembleStack().start();
        if (Buttons.assembleStack.released()) new CancelAutoIntake().start();
        
        if (Buttons.autoIntake.pressed()) new AutoIntake().start();	
        if (Buttons.autoIntake.released()) new CancelAutoIntake().start();
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
        if (Buttons.gripper.pressed()) new OpenGripper().start();
        if (Buttons.gripper.released()) new CloseGripper().start();
        
        /*
         * ELEVATOR
         */
        double rawLevel = secondaryJoystick.getRawAxis(Buttons.elevatorPosition);
        double elevatorLevel = elevator.getPosition();
        if (elevatorSetState) {
        	elevatorLevel = Math.floor(4 * (rawLevel + 1));
        } else {
        	if (rawLevel < -0.5) { // 0, GROUND LEVEL
        		elevatorLevel += 0;
        	} else if (rawLevel > 0 && rawLevel <= 0.5) { // 0.5, STEP LEVEL
        		elevatorLevel += 0.5;
        	} else if (rawLevel > 0.5) { // 0.75, STEP + BUFFER
        		elevatorLevel += 0.75;
        	} else { // 0.16, SCORING PLATFORM LEVEL
        		elevatorLevel += 0.16;
        	}
        }
        if (Buttons.setElevator.pressed()) {
        	new SetElevator(elevatorLevel).start();
        	elevatorSetState = !elevatorSetState;
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
        // reset when down
        if (elevator.isAtBottom()) {
        	elevator.reset();
        }
        
        
        /*
         * ROLLERS
         */
        double axisValue = secondaryJoystick.getRawAxis(Buttons.rollerSpeed);
        if (Math.abs(axisValue) <= 0.25) {
        	rollerIntake.stop();
        } else if (axisValue > 0.25 && axisValue <= 0.6) {
        	rollerIntake.slowIn(); 
        } else if (axisValue > 0.6) {
        	rollerIntake.in();
        } else if (axisValue < -0.6) {
        	rollerIntake.out();
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
		if (DriverStation.getInstance().getMatchTime() <= END_MATCH_MOTION_TIME && !hasStartedEndMatchRoutine) {
			hasStartedEndMatchRoutine = true;
			new EndMatch().start();
		}
		
		
		/*
		 * POWER MANAGEMENT
		 */
		dashboard.manageFaults();
    }
    
}
