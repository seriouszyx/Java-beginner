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
		GRect head = new GRect(HEAD_WIDTH,HEAD_HEIGHT);
		head.setFilled(true);
		head.setColor(Color.gray);
		double centerX = this.getCenterX()-HEAD_WIDTH/2;
		double centerY = this.getCenterY()-HEAD_HEIGHT/2;
		add(head,centerX,centerY);
		
		GOval eyeLeft = new GOval(2*EYE_RADIUS,2*EYE_RADIUS);
		GOval eyeRight = new GOval(2*EYE_RADIUS,2*EYE_RADIUS);
		eyeLeft.setFilled(true);
		eyeLeft.setColor(Color.yellow);
		eyeRight.setFilled(true);
		eyeRight.setColor(Color.yellow);
		add(eyeLeft,centerX+20,centerY+40);
		add(eyeRight,centerX+90,centerY+40);
		

		GRect mouth = new GRect(MOUTH_WIDTH,MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setColor(Color.white);
		add(mouth,centerX+25,centerY+180);
		
	}

}