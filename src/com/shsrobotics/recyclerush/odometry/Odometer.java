package com.shsrobotics.recyclerush.odometry;

import org.ejml.simple.SimpleMatrix;
import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.systems.DriveBase;

public class Odometer implements Hardware {
	
	private EncoderOdometer encoderOdometer = new EncoderOdometer();
	private AccGyrOdometer accGyrOdometer = new AccGyrOdometer();
	
	double[][] H_ar = { {1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {1, 0, 0}, {0, 1, 0}, {0, 0, 1} };
	double q_cv = Constants.PROCESS_COVARIANCE;
	double[][] Q_ar = { {q_cv, q_cv, q_cv}, {q_cv, q_cv, q_cv}, {q_cv, q_cv, q_cv} };
	double r_cv = Constants.OBSERVATION_COVARIANCE;
	double[][] R_ar = { {r_cv, r_cv, r_cv}, {r_cv, r_cv, r_cv}, {r_cv, r_cv, r_cv} };
	
	private SimpleMatrix x = new SimpleMatrix(3, 1);
	private SimpleMatrix P = new SimpleMatrix(3, 3);
	private SimpleMatrix H = new SimpleMatrix(H_ar);
	private SimpleMatrix Q = new SimpleMatrix(Q_ar);
	private SimpleMatrix R = new SimpleMatrix(R_ar);
	private SimpleMatrix S = new SimpleMatrix(6, 3);
	

	public Odometer() {
	}
	
	public void reset() {	
		encoderOdometer.reset();
	}
	
	public void update() {
		double[][] eV = encoderOdometer.get();
		double[][] acV = accGyrOdometer.get();
		double[][] uV = DriveBase.getOutput();
		
		double[][] zV = { eV[0], eV[1], eV[2], acV[0], acV[1], acV[2] };
		
		SimpleMatrix z = new SimpleMatrix(zV);
		SimpleMatrix u = new SimpleMatrix(uV);
		
		SimpleMatrix x_p = x.plus(u);
		SimpleMatrix P_p = P.plus(Q);
		SimpleMatrix y = z.minus( H.mult(x_p) );
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
