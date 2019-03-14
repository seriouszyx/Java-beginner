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
		
        // head
        GRect head = new GRect(HEAD_WIDTH, HEAD_HEIGHT);
        head.setColor(Color.BLUE);
        head.setFilled(true);
        add(head, 70, 70);
        
        //eyes
        GOval leftEye = new GOval(20, 20); // width and height
        leftEye.setColor(Color.RED);
        leftEye.setFilled(true);
        add(leftEye, 90, 110);
        
        GOval rightEye = new GOval(20, 20); // width and height
        rightEye.setColor(Color.RED);
        rightEye.setFilled(true);
        add(rightEye, 180, 110);
        
        //mouth
        GRect mouth = new GRect(MOUTH_WIDTH, MOUTH_HEIGHT);
        mouth.setColor(Color.green);
        mouth.setFilled(true);
        add(mouth, 95, 220);

	}

}