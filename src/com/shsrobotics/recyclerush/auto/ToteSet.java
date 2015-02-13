package com.shsrobotics.recyclerush.auto;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;
import edu.wpi.first.wpilibj.command.Command;

public class ToteSet extends Command implements Hardware, Autonomous2015 {

	@Override
	protected void initialize() {
		new AutoIntake().start();
		driveBase.drive(0.0, Autonomous.drivingSpeed, 0.0);
		setTimeout(Autonomous.robotSetDrivingTime);
	}

	@Override
	protected void execute() { }

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		driveBase.drive(0.0, 0.0, 0.0);
		new CancelAutoIntake().start();
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
