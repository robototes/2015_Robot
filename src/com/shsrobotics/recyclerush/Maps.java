package com.shsrobotics.recyclerush;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.JoystickButtonStart;
import com.shsrobotics.library.JoystickCallbackButton;
import com.shsrobotics.library.fieldpositioning.Field;
import com.shsrobotics.recyclerush.Hardware.Subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public interface Maps extends GLOBAL {
	
	ToteResponseIIR primary = new ToteResponseIIR(0);
	Joystick secondary = new Joystick(1);
	
	public static final class Ports {
		public static final int
			frontLeftCANID = 0,
			frontRightCANID = 0,
			backLeftCANID = 0,
			backRightCANID = 0;
		public static final int 
			elevatorMotorPort = 0,
			rcClawMotorPort = 0,
			gripperMotorPort = 0;
		public static final class ElevatorSensors {
			public static final int
				topLimit = 0,
				bottomLimit = 0,
				encoder = 0;
		}
		public static final class GripperSensors {
			public static final int
				openLimit = 0,
				closeLimit = 0;
		}
		public static final class DriveSensors {
			public static final int
				flEncoder = 0,
				frEncoder = 0,
				blEncoder = 0,
				brEncoder = 0,
				gyroscope = 0;
		}
	}
	
	public static final class Field {
		
		/**
		 * 
		 * @param img
		 * @return
		 * @author google code
		 */
		private static BufferedImage toBufferedImage(Image img) {
		    if (img instanceof BufferedImage) {
		        return (BufferedImage) img;
		    }

		    // Create a buffered image with transparency
		    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(img, 0, 0, null);
		    bGr.dispose();

		    // Return the buffered image
		    return bimage;
		}
		
		private static com.shsrobotics.library.fieldpositioning.Field initField() {
			com.shsrobotics.library.fieldpositioning.Field f = null;
			try {
				f = new com.shsrobotics.library.fieldpositioning.Field(402, 300, toBufferedImage(ImageIO.read(new File("field.png"))), com.shsrobotics.library.fieldpositioning.Field.CONNECTIVITY_8);
			} catch (IOException e) {
				DriverStation.reportError("Failed to load field reference image, initializing to null", false);
				e.printStackTrace();
			}
			return f;
		}
		
		public static final com.shsrobotics.library.fieldpositioning.Field field = initField();
		
		public static int[][] loadPath(String pathname) {
			int[][] res = null;
			LinkedList<int[]> pList = new LinkedList<>();
			File path = new File(pathname);
			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				String line;
				while ( (line = br.readLine()) != null ) {
					int[] point = new int[2];
					String[] p = line.split(",");
					point[0] = convert(p[0]);
					point[1] = convert(p[1]);
					pList.add(point);
				}
				res = new int[res.length][2];
				for ( int i = 0; i < res.length; i++ ) {
					int[] e = pList.get(i);
					res[i] = e;
				}
				DriverStation.reportError("Successful parse of csv path file", false);
			}
			catch (FileNotFoundException fnfe) {
				DriverStation.reportError("Path file not found: " + path.getAbsolutePath(), false);
				//fnfe.printStackTrace();
			}
			catch (IOException ioe) {
				DriverStation.reportError("IOException in loading a path", false);
				//ioe.printStackTrace();
			}
			catch ( ArrayIndexOutOfBoundsException aioobe ) {
				DriverStation.reportError("Illegal syntax in csv path file: " + path.getAbsolutePath(), false);
			}
			return res;
		}

		private static final int convert(String i) {
			try {
				return Integer.parseInt(i);
			}
			catch (NumberFormatException nfe) {
				return 0;
			}
		}
		
	}
	
	
	public static final class Constants {
		
	}
	
	public static final class Dashboard {
		
	}
	
	
};