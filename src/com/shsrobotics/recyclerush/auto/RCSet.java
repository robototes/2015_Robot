package com.shsrobotics.recyclerush.auto;

import com.shsrobotics.recyclerush.Maps.Autonomous;
import com.shsrobotics.recyclerush.commands.TurnTo;
import com.shsrobotics.recyclerush.commands.ClawDown;
import com.shsrobotics.recyclerush.commands.ClawHalfUp;
import com.shsrobotics.recyclerush.commands.ClawUp;
import com.shsrobotics.recyclerush.commands.DelayedCommand;
import com.shsrobotics.recyclerush.commands.TimedDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Gets 1 RC
 */
public class RCSet extends CommandGroup implements Autonomous2015 {

    public RCSet() {
    	addParallel(new TurnTo(45, 0.48));
    	addSequential(new ClawDown());
    	addSequential(new TimedDrive(0, -Autonomous.drivingSpeed, Autonomous.curveCorrection, 0.33));
    	addSequential(new WaitCommand(.4));
    	addSequential(new ClawHalfUp());
    	addSequential(new TimedDrive(0, Autonomous.drivingSpeed, Autonomous.curveCorrection, 2));
    	addSequential(new TurnTo(-45));
    	addParallel(new ClawDown());
    	addSequential(new TimedDrive(0, Autonomous.drivingSpeed * 0.6, Autonomous.curveCorrection, 0.1));
    	
    	/*
    	addSequential(new TimedDrive(0, Autonomous.drivingSpeed, 0, Autonomous.RCsetForTime));
    	addSequential(new TurnTo(-180));
    	addParallel(new ClawDown());
    	addSequential(new DelayedCommand(new TurnTo(0), Autonomous.clawDriveDelay));
    	addParallel(new ClawUp());
    	addSequential(new TimedDrive(0, 0, Autonomous.drivingSpeed, Autonomous.RCsetTurnTime));
    	*/
    }

	@Override
	public double getStartingX() {
		return 0;
	}

	@Override
	public double getStartingY() {
		return 0;
	}

	@Override
	public double getStartingHeading() {
		return 0;
	}

}
