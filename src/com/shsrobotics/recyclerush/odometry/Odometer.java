package com.shsrobotics.recyclerush.odometry;

import org.ejml.simple.SimpleMatrix;
import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.systems.DriveBase;

public class Odometer implements Hardware {
	
//	private EncoderOdometer encoderOdometer = new EncoderOdometer();
//	private AccGyrOdometer accGyrOdometer = new AccGyrOdometer();
	
	double[][] H_ar = { {1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {1, 0, 0}, {0, 1, 0}, {0, 0, 1} };
	double[][] B_ar = { {Constants.MAX_STRAFE_SPEED, 0, 0}, {0, Constants.MAX_FOR_SPEED, 0}, {0, 0, Constants.MAX_ROT_SPEED} };
	double q_cv = Constants.PROCESS_COVARIANCE;
	double[][] Q_ar = { {q_cv, 0, 0}, {0, q_cv, 0}, {0, 0, q_cv} };
	double r_cv = Constants.OBSERVATION_COVARIANCE;
	double[][] R_ar = { 
			{r_cv, 0, 0, 0, 0, 0},
			{0, r_cv, 0, 0, 0, 0},
			{0, 0, r_cv, 0, 0, 0},
			{0, 0, 0, r_cv, 0, 0},
			{0, 0, 0, 0, r_cv, 0},
			{0, 0, 0, 0, 0, r_cv}
		};
	
	private SimpleMatrix x = new SimpleMatrix(3, 1); // state matrix
	private SimpleMatrix P = new SimpleMatrix(3, 3); // state covariance
	private SimpleMatrix B = new SimpleMatrix(B_ar); // control-input model
	private SimpleMatrix H = new SimpleMatrix(H_ar); // observation model
	private SimpleMatrix Q = new SimpleMatrix(Q_ar); // system covariance
	private SimpleMatrix R = new SimpleMatrix(R_ar); // more covariance
	private SimpleMatrix S = new SimpleMatrix(6, 6); // even more covariance

	public Odometer() {
	}
	
	public void reset() {	
//		encoderOdometer.reset();
	}
	
	public void update() {
//		double[][] eV = encoderOdometer.get();
//		double[][] acV = accGyrOdometer.get();
//		double[][] uV = DriveBase.getOutput();
//		double[][] zV = { eV[0], eV[1], eV[2], acV[0], acV[1], acV[2] };
		
		double[][] uV = Test.getOutput();
		double[][] zV = Test.getInput();
		
		SimpleMatrix z = new SimpleMatrix(zV);
		SimpleMatrix u = new SimpleMatrix(uV);
		
		SimpleMatrix x_p = x.plus(B.mult(u));
		SimpleMatrix P_p = P.plus(Q);
		SimpleMatrix y = z.minus(H.mult(x_p));
		S = H.mult(P_p).mult(H.transpose()).plus(R);
		SimpleMatrix K = P_p.mult(H.transpose()).mult(S.invert());
		x = x_p.plus(K.mult(y));
		P = SimpleMatrix.identity(3).minus(K.mult(H)).mult(P_p);
	}
	
	public double getXVelocity() {
		return x.get(0, 0);
	}
	
	public double getYVelocity() {
		return x.get(1, 0);
	}
	
	public double getAngularVelocity() {
		return x.get(2, 0);
	}
}
