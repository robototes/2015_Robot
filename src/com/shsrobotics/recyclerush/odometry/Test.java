package com.shsrobotics.recyclerush.odometry;


//import com.shsrobotics.recyclerush.Maps.Constants;

public class Test {
	
//	private static long start;
//	private static Odometer odometer = new Odometer();
//	private static OdometerLocator locator = new OdometerLocator(0, 0, 0);
//	private static OdometerLocator locatorB = new OdometerLocator(0, 0, 0);
//	private static final int N = 1; // number of square circuits to complete
//	private static double RAND = 1;
//	private static double DRIFT = 0.02;
//	
//	public static void main(String[] args) {
//		start = System.nanoTime();
//		int i = 0;
//		locator.reset();
//		
//		while ((System.nanoTime() - start)/1e9 < 20 * N) {
//			odometer.update();
//			
//			double[] data = getOutput();
//			double encX, encY, encH, acX, acY, acH = 0;
//			encX = data[0]*Constants.MAX_STRAFE_SPEED + (Math.random()-0.5) * RAND;
//			encY = data[1]*Constants.MAX_FOR_SPEED + (Math.random()-0.5) * RAND;
//			encH = data[2]*Constants.MAX_ROT_SPEED + (Math.random()-0.5) * 3;
//			acX = data[0]*Constants.MAX_STRAFE_SPEED + (Math.random()-0.5) * RAND;
//			acY = data[1]*Constants.MAX_FOR_SPEED + (Math.random()-0.5) * RAND;
//			acH = data[2]*Constants.MAX_ROT_SPEED + (Math.random()-0.5)/2 + (System.nanoTime() - start)/1e9*DRIFT;
//			if (Math.random() < 0.05) { // 5% of the time
//				encX /= 1.5;
//				encY /= 1.5;
//				encH /= 0.5 + Math.random();
//			}
//			double vX = 0.5*(encX + acX);
//			double vY = 0.5*(encY + acY);
//			double vH = 0.5*(encH + acH);
//			locatorB.update(vX, vY, vH);
//			
//			locator.update(odometer.getXVelocity(), odometer.getYVelocity(), odometer.getAngularVelocity());
//			
//			if (i++ % 3e4 == 0) {
//				System.out.println((System.nanoTime() - start)/1e9 + "," + locator.x + "," + locator.y + "," + locator.h + "," + locatorB.x + "," + locatorB.y + "," + locatorB.h);
////				System.out.println((System.nanoTime() - start)/1e9 + "," + odometer.getXVelocity() + "," + odometer.getYVelocity() + "," + odometer.getAngularVelocity());
//			}
//		}
//	}
//	
//	public static double[] getInput() {
//		double[] data = getOutput();
//		double encX, encY, encH, acX, acY, acH = 0;
//		
//		encX = data[0]*Constants.MAX_STRAFE_SPEED + (Math.random()-0.5) * RAND;
//		encY = data[1]*Constants.MAX_FOR_SPEED + (Math.random()-0.5) * RAND;
//		encH = data[2]*Constants.MAX_ROT_SPEED + (Math.random()-0.5) * 3;
//		acX = data[0]*Constants.MAX_STRAFE_SPEED + (Math.random()-0.5) * RAND;
//		acY = data[1]*Constants.MAX_FOR_SPEED + (Math.random()-0.5) * RAND;
//		acH = data[2]*Constants.MAX_ROT_SPEED + (Math.random()-0.5)/2 + (System.nanoTime() - start)/1e9*DRIFT;
//		
//		if (Math.random() < 0.05) { // 5% of the time
//			encX /= 1.5;
//			encY /= 1.5;
//			encH /= 0.5 + Math.random();
//		}
//		
//		double[] r = { encX, encY, encH, acX, acY, acH };
//		
//		return r;
//	}
//	
//	public static double[] getOutput() {
//		double t = (System.nanoTime() - start) / 1e9;
//		double x, y = 0; // ft/s for linear, deg/s for angular
//		double vh = 0 / Constants.MAX_ROT_SPEED; 
//		double h = vh * t;
//		double gv = 2;
//		
//		x = gv * Math.sin(t * Math.PI / 10) / Constants.MAX_STRAFE_SPEED;
//		y = gv * Math.cos(t * Math.PI / 10) / Constants.MAX_FOR_SPEED;
//		
////		double[][] v_ar = { {x}, {y} };
////		double[][] r_ar = { {Math.cos(-h * Math.PI/180), -Math.sin(-h * Math.PI/180)}, {Math.sin(-h * Math.PI/180), Math.cos(-h * Math.PI/180)} };
////		SimpleMatrix R = new SimpleMatrix(r_ar); // world coorinate frame
////		SimpleMatrix v = new SimpleMatrix(v_ar);
////		
////		SimpleMatrix vw = R.mult(v); // world velocity
////		
////		x = vw.get(0, 0);
////		y = vw.get(1, 0);
//		
//		double[] r = { x, y, vh };
//		
//		return r;		
//	}
}
