package com.shsrobotics.recyclerush.auto;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.commands.AutoIntake;
import com.shsrobotics.recyclerush.commands.CancelAutoIntake;
import edu.wpi.first.wpilibj.command.Command;

public class RobotSet extends Command implements Hardware {

	@Override
	protected void initialize() {
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
	}

	@Override
	protected void interrupted() {
		end();
	}

}
