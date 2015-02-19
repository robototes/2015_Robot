package com.shsrobotics.recyclerush.auto;

import static com.shsrobotics.recyclerush.Maps.Autonomous;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;

import edu.wpi.first.wpilibj.command.Command;
import static com.shsrobotics.recyclerush.Hardware.*;
import static com.shsrobotics.recyclerush.Hardware.IDriveBase.*;

public class RobotSet extends Command implements Autonomous2015 {

	@Override
	protected void initialize() {
		setTimeout(Autonomous.robotSetDrivingTime);
	}

	@Override
	protected void execute() {
		driveBase.drive(0.0, Autonomous.drivingSpeed, Autonomous.curveCorrection);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		driveBase.drive(0.0, 0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
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
