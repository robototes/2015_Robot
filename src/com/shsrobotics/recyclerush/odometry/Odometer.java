package com.shsrobotics.recyclerush.odometry;

import com.shsrobotics.recyclerush.Hardware;
import com.shsrobotics.recyclerush.systems.DriveBase;

public class Odometer implements Hardware {

//	private AccGyrOdometer accGyrOdometer = new AccGyrOdometer();
//	private EncoderOdometer encoderOdometer = new EncoderOdometer();
	
	double x = 0;
	double y = 0;
	double h = 0;
	
	double det_S_x = Constants.PROCESS_VARIANCE[0] * (Constants.GYRO_OBSERVATION_VARIANCE[0] - 2 * Constants.OBSERVATION_COVARIANCE[0]
			+ Constants.ENCODER_OBSERVATION_VARIANCE[0]) + Constants.GYRO_OBSERVATION_VARIANCE[0] * Constants.ENCODER_OBSERVATION_VARIANCE[0]
			- Constants.OBSERVATION_COVARIANCE[0] * Constants.OBSERVATION_COVARIANCE[0];
	double det_S_y = Constants.PROCESS_VARIANCE[1] * (Constants.GYRO_OBSERVATION_VARIANCE[1] - 2 * Constants.OBSERVATION_COVARIANCE[1]
			+ Constants.ENCODER_OBSERVATION_VARIANCE[1]) + Constants.GYRO_OBSERVATION_VARIANCE[1] * Constants.ENCODER_OBSERVATION_VARIANCE[1]
			- Constants.OBSERVATION_COVARIANCE[1] * Constants.OBSERVATION_COVARIANCE[1];
	double det_S_h = Constants.PROCESS_VARIANCE[2] * (Constants.GYRO_OBSERVATION_VARIANCE[2] - 2 * Constants.OBSERVATION_COVARIANCE[2]
			+ Constants.ENCODER_OBSERVATION_VARIANCE[2]) + Constants.GYRO_OBSERVATION_VARIANCE[2] * Constants.ENCODER_OBSERVATION_VARIANCE[2]
			- Constants.OBSERVATION_COVARIANCE[2] * Constants.OBSERVATION_COVARIANCE[2];
	double K_x_gyr = Constants.PROCESS_VARIANCE[0] * (2 * Constants.PROCESS_VARIANCE[0] + Constants.OBSERVATION_COVARIANCE[0] 
			+ Constants.GYRO_OBSERVATION_VARIANCE[0]) / det_S_x;
	double K_x_enc = Constants.PROCESS_VARIANCE[0] * (2 * Constants.PROCESS_VARIANCE[0] + Constants.OBSERVATION_COVARIANCE[0] 
			+ Constants.ENCODER_OBSERVATION_VARIANCE[0]) / det_S_x;
	double K_y_gyr = Constants.PROCESS_VARIANCE[1] * (2 * Constants.PROCESS_VARIANCE[1] + Constants.OBSERVATION_COVARIANCE[1] 
			+ Constants.GYRO_OBSERVATION_VARIANCE[1]) / det_S_y;
	double K_y_enc = Constants.PROCESS_VARIANCE[1] * (2 * Constants.PROCESS_VARIANCE[1] + Constants.OBSERVATION_COVARIANCE[1] 
			+ Constants.ENCODER_OBSERVATION_VARIANCE[1]) / det_S_y;
	double K_h_gyr = Constants.PROCESS_VARIANCE[2] * (2 * Constants.PROCESS_VARIANCE[2] + Constants.OBSERVATION_COVARIANCE[2] 
			+ Constants.GYRO_OBSERVATION_VARIANCE[2]) / det_S_h;
	double K_h_enc = Constants.PROCESS_VARIANCE[2] * (2 * Constants.PROCESS_VARIANCE[2] + Constants.OBSERVATION_COVARIANCE[2] 
			+ Constants.ENCODER_OBSERVATION_VARIANCE[2]) / det_S_h;

	public Odometer() {
		K_x_gyr /= K_x_gyr + K_x_enc;
		K_x_enc /= K_x_gyr + K_x_enc;
		K_y_gyr /= K_y_gyr + K_y_enc;
		K_y_enc /= K_y_gyr + K_y_enc;
		K_h_gyr /= K_h_gyr + K_h_enc;
		K_h_enc /= K_h_gyr + K_h_enc;
	}
	
	public void reset() {	
//		encoderOdometer.reset();
	}
	
	public void update() {
//		double[] data = DriveBase.getOutput();
//		double[] z_gyr = accGyrOdometer.get();
//		double[] z_enc = encoderOdometer.get();
		double[] data = Test.getOutput();
		double[] in = Test.getInput();
		double[] z_gyr = {in[3], in[4], in[5]};
		double[] z_enc = {in[0], in[1], in[2]};
		
		double x_p = Constants.MAX_STRAFE_SPEED * data[0];
		double y_p = Constants.MAX_FOR_SPEED * data[1];
		double h_p = Constants.MAX_ROT_SPEED * data[2];
		
		double diff_x_gyr = z_gyr[0] - x_p;
		double diff_y_gyr = z_gyr[1] - y_p;
		double diff_h_gyr = z_gyr[2] - h_p;
		double diff_x_enc = z_enc[0] - x_p;
		double diff_y_enc = z_enc[1] - y_p;
		double diff_h_enc = z_enc[2] - h_p;
		
		x = x_p + K_x_gyr * diff_x_gyr + K_x_enc * diff_x_enc;
		y = y_p + K_y_gyr * diff_y_gyr + K_y_enc * diff_y_enc;
		h = h_p + K_h_gyr * diff_h_gyr + K_h_enc * diff_h_enc;
	}
	
	public double getXVelocity() {
		return x;
	}
	
	public double getYVelocity() {
		return y;
	}
	
	public double getAngularVelocity() {
		return h;
	}
}
