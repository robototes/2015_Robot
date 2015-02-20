package com.shsrobotics.recyclerush.auto;

import static com.shsrobotics.recyclerush.Hardware.*;

import com.shsrobotics.recyclerush.Maps.Autonomous;
import com.shsrobotics.recyclerush.commands.CloseGripper;
import com.shsrobotics.recyclerush.commands.KeepIn;
import com.shsrobotics.recyclerush.commands.OpenGripper;
import com.shsrobotics.recyclerush.commands.RollersIn;
import com.shsrobotics.recyclerush.commands.TurnTo;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;
import com.shsrobotics.recyclerush.commands.TimedDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToteSet extends CommandGroup implements Autonomous2015 {

	Command rollersOn;
	
	public ToteSet() {
		rollersOn = new KeepIn();
		addSequential(new OpenGripper());
		addParallel(new TimedDrive(0, Autonomous.drivingSpeed, 0, Autonomous.oneToteTime));
		addSequential(new RollersIn());
		addParallel(rollersOn);
		addSequential(new TurnTo(60, Autonomous.toteSetForwardWhileTurn));
		addSequential(new TimedDrive(0, Autonomous.drivingSpeed, Autonomous.curveCorrection, Autonomous.toteSetDrivingTime));
	}
	
	public void end() {
		rollersOn.cancel();
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
