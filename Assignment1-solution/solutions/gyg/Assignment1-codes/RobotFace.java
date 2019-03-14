/*
 * File: RobotFace.java
 * --------------------
 * This program draws a robot face using GRects and GOvals, centered
 * in the graphics window.  We make sure to define constants at the
 * top of our program instead of using magic numbers. We also write
 * the program in terms of reusable and general methods
 * drawRectangle and drawCircle.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {

	/* Constants for the drawing */ 
	private static final int HEAD_WIDTH = 150; 
	private static final int HEAD_HEIGHT = 250; 
	private static final int EYE_RADIUS = 20; 
	private static final int MOUTH_WIDTH = 100; 
	private static final int MOUTH_HEIGHT = 30; 
	
	public void run() {
		setSize(600, 400);
		// You fill the codes here
		//face
		GRect face = new GRect(150, 250);
		face.setFilled(true);
		face.setColor(Color.gray);
		add(face, 225, 75);
		//leftEye
		GArc leftEye = new GArc(40, 40, 20, 360);
		leftEye.setFilled(true);
		leftEye.setColor(Color.yellow);
		add(leftEye, 245, 125);
		//rightEye
		GArc rightEye = new GArc(40, 40, 20, 360);
		rightEye.setFilled(true);
		rightEye.setColor(Color.yellow);
		add(rightEye, 315, 125);
		//mouth
		GRect mouth = new GRect(100, 30);
		mouth.setFilled(true);
		mouth.setColor(Color.white);
		add(mouth, 250, 250);
	}

}