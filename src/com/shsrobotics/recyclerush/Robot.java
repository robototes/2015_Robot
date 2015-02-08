/*
 * TEAM 2412
 *
 * JANUARY 2015
 *
 * FIRST ROBOTICS COMPETITION
 * RECYCLE RUSH
 * SOURCE CODE
 */

package com.shsrobotics.recyclerush;


import static com.shsrobotics.recyclerush.Hardware.Subsystems.elevator;
import static com.shsrobotics.recyclerush.Hardware.Subsystems.gripper;
import static com.shsrobotics.recyclerush.Hardware.Subsystems.rcClaw;
import static com.shsrobotics.recyclerush.Hardware.Subsystems.rollers;
import static com.shsrobotics.recyclerush.Hardware.Subsystems.driveBase;

import java.util.HashMap;
import java.util.Map.Entry;

import sun.rmi.runtime.NewThreadAction;

import com.shsrobotics.recyclerush.actions.AutoIntake;
import com.shsrobotics.library.FRCRobot;
import com.shsrobotics.library.JoystickButton;
import com.shsrobotics.library.JoystickCallbackButton;
import com.shsrobotics.library.SubsystemState;
import com.shsrobotics.library.fieldpositioning.PID2D;
import com.shsrobotics.library.fieldpositioning.RobotPosition;
import com.shsrobotics.library.util.functions.UpdateFunction;
import com.shsrobotics.recyclerush.Hardware.Subsystems;
import com.shsrobotics.recyclerush.actions.ElevatorActions;
import com.shsrobotics.recyclerush.actions.ForkActions;
import com.shsrobotics.recyclerush.drive.DriveBase;
import com.shsrobotics.recyclerush.elevator.Elevator;
import com.shsrobotics.recyclerush.gripper.Gripper;
import com.shsrobotics.recyclerush.rcclaw.RCClaw;
import com.shsrobotics.recyclerush.rollers.Rollers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Robot extends FRCRobot implements Hardware, RobotPosition {
	
	PID2D drivePID;
	boolean readStart = false;
	public static NetworkTable networkTable;
	int[] startPos = new int[2];
	
	//HashMap<String,Object> networkMap;
	
	UpdateFunction networktableFunction = new UpdateFunction(()->{
		HashMap<String,Object> map = new HashMap<>();
		Hardware.Subsystems.elevator.returnState(map);
		gripper.returnState(map);
		rollers.returnState(map);
		rcClaw.returnState(map);
		NetworkTable nt = networkTable;
		for ( Entry e : map.entrySet() ) {
			String k = (String)e.getKey();
			Object o = e.getValue();
			if ( o instanceof Double )
				nt.putNumber(k, ((Double)o).doubleValue());
			else if ( o instanceof Integer )
				nt.putNumber(k, ((Integer)o).doubleValue());
			else if ( o instanceof String )
				nt.putString(k, (String)o);
			else if ( o instanceof SubsystemState )
				nt.putString(k, ((SubsystemState)o).toString());
			else
				nt.putValue(k, o);
			 
		}
		
		// if on a field and is disabled
		return DriverStation.getInstance().isFMSAttached() && DriverStation.getInstance().isDisabled();
	});
	
	@Override
	public void disabledPeriodic() {
		UpdateFunction.setActive();
		super.disabledPeriodic();
		
			
	}
	
	@Override
	public void robotInit() {
		networkTable = NetworkTable.getTable("ROBOT");
		//elevator = new Elevator(null, null, null, null);
		gripper = new Gripper(Hardware.MotorControllers.gripper, LimitSwitches.gripperOuterLimit, LimitSwitches.gripperInnerLimit, LimitSwitches.gripperToteStop, Control.pdp, 1);
		//rcClaw = new RCClaw(null, null, null);
		//rollers = new Rollers(null, null, null, null, null);
		driveBase = new DriveBase(null,Hardware.MotorControllers.frontLeft,null,null, null, this);
		drivePID = new PID2D(null,this);
		//UpdateFunction.run(networktableFunction);
		
	}
	
	@Override
	public void autonomousInit() {
	}
	
	@Override
	public void autonomousPeriodic() {
		UpdateFunction.setActive();
	}
	
	public void teleopInit() {
		Subsystems.driveBase.drive();
	}
	
	@Override
	public void teleopPeriodic() {
		Buttons.autoIntake.whenPressed();
//		Buttons.release.whenReleased();
		
//		networkTable.putString("ERROR", Hardware.MotorControllers.frontLeft.getSpeed()+", set to: " + Hardware.MotorControllers.frontLeft.getSetpoint());
//		Hardware.MotorControllers.frontLeft.setP(networkTable.getNumber("PID_P"));
//		Hardware.MotorControllers.frontLeft.setI(Robot.networkTable.getNumber("PID_I"));
//		Hardware.MotorControllers.frontLeft.setD(networkTable.getNumber("PID_D"));
//		networkTable.putNumber("encVelocity", Hardware.MotorControllers.frontLeft.getEncVelocity());
		
		if (Buttons.gripperIn.held()) {
			gripper.manualClose();
		} else if (Buttons.gripperOut.held()) {
			gripper.manualOpen();
		} else {
			gripper.manualStop();
		}
		
//		Hardware.MotorControllers.frontLeft.set(secondary.getX()*48*4-10);
		
		UpdateFunction.setActive();
	}
	
	@Override
	public double getX() {
		return 0;
	}
	
	@Override
	public double getY() {
		return 0;
	}
	
	@Override
	public double getCollisionRadius() {
		return 36;
	}
	
	@Override
	public PID2D getPID() {
		return drivePID;
	}

	@Override
	public double getHeading() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static final class Buttons {
		// buttons are arbitrary right now.
		public static JoystickCallbackButton autoIntake = new JoystickCallbackButton(secondary, 1, ()->{
			new AutoIntake().start();
		});
		public static JoystickCallbackButton release = new JoystickCallbackButton(secondary, 2, ()->{
			Subsystems.gripper.manualStop();
		});
		
		public static JoystickCallbackButton elevatorUp = new JoystickCallbackButton(secondary, 5, ()->{
			//Subsystems.elevator.moveUp();
		});
		public static JoystickCallbackButton elevatorDown = new JoystickCallbackButton(secondary, 3, ()->{
			//Subsystems.elevator.moveDown();
		});

		public static JoystickButton gripperIn = new JoystickButton(secondary, 8);
		public static JoystickButton gripperOut = new JoystickButton(secondary, 7);
		
		
		public static JoystickCallbackButton rcClawUp = new JoystickCallbackButton(secondary, 6, ()->{
			
		});
		public static JoystickCallbackButton rcClawDown = new JoystickCallbackButton(secondary, 4, ()->{
			
		});
	}
	
}
