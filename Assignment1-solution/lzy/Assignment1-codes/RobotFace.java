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

        // add the face
        GRect face = new GRect(200, 350); // width and height
        face.setColor(Color.gray); // make the square gray
        face.setFilled(true); // fill the square
        add(face, 170, 50); // add the square to the screen

        // add the mouse
        GRect mouse = new GRect(160, 40);
        mouse.setColor(Color.white);
        mouse.setFilled(true);
        add(mouse, 190, 310);

        // add the left-eye
        GOval lefteye = new GOval(50, 50); // width and height
        lefteye.setColor(Color.yellow);
        lefteye.setFilled(true);
        add(lefteye, 190, 100); // add to location 
        
     // add the right-eye
        GOval righteye = new GOval(50, 50); // width and height
        righteye.setColor(Color.yellow);
        righteye.setFilled(true);
        add(righteye, 300, 100); // add to location 

        
	}

}