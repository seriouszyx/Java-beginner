/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	// Number of bricks in each row
	public static final int NBRICK_COLUMNS = 10;

	// Number of rows of bricks
	public static final int NBRICK_ROWS = 10;

	// Separation between neighboring bricks, in pixels
	public static final double BRICK_SEP = 4;

	// Width of each brick, in pixels
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	// Height of each brick, in pixels
	public static final double BRICK_HEIGHT = 8;

	// Offset of the top brick row from the top, in pixels
	public static final double BRICK_Y_OFFSET = 70;

	// Dimensions of the paddle
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 10;

	// Offset of the paddle up from the bottom 
	public static final double PADDLE_Y_OFFSET = 30;

	// Radius of the ball in pixels
	public static final double BALL_RADIUS = 10;

	// The ball's vertical velocity.
	public static final double VELOCITY_Y = 3.0;

	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	// Animation delay or pause time between ball moves (ms)
	public static final double DELAY = 1000.0 / 60.0;

	// Number of turns 
	public static final int NTURNS = 3;
	// random number
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");


	GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
	GOval ball = new GOval(BALL_RADIUS, BALL_RADIUS);
	public void run() {
		setSize(420, 550);
		
		// Set the window's title bar text
		setTitle("Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);

		/* You fill this in, along with any subsidiary methods */
		// create bricks
		GRect[] bricks_1 = new GRect[20];
		for(int i = 0; i < bricks_1.length; i++) {
			bricks_1[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			bricks_1[i].setColor(Color.RED);
			bricks_1[i].setFilled(true);
			if(i <= 9) {
			    add(bricks_1[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP+PADDLE_Y_OFFSET);
			}else {
				add(bricks_1[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 2 + BRICK_HEIGHT+PADDLE_Y_OFFSET);
			}
		}
		GRect[] bricks_2 = new GRect[20];
		for(int i = 0; i < bricks_2.length; i++) {
			bricks_2[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			bricks_2[i].setColor(Color.ORANGE);
			bricks_2[i].setFilled(true);
			if(i <= 9) {
			    add(bricks_2[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP * 3 + BRICK_HEIGHT * 2+PADDLE_Y_OFFSET);
			}else {
				add(bricks_2[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 4 + BRICK_HEIGHT * 3+PADDLE_Y_OFFSET);
			}
		}
		GRect[] bricks_3 = new GRect[20];
		for(int i = 0; i < bricks_3.length; i++) {
			bricks_3[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			bricks_3[i].setColor(Color.YELLOW);
			bricks_3[i].setFilled(true);
			if(i <= 9) {
			    add(bricks_3[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP * 5 + BRICK_HEIGHT * 4+PADDLE_Y_OFFSET);
			}else {
				add(bricks_3[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 6 + BRICK_HEIGHT * 5+PADDLE_Y_OFFSET);
			}
		}
		GRect[] bricks_4 = new GRect[20];
		for(int i = 0; i < bricks_4.length; i++) {
			bricks_4[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			bricks_4[i].setColor(Color.GREEN);
			bricks_4[i].setFilled(true);
			if(i <= 9) {
			    add(bricks_4[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP * 7 + BRICK_HEIGHT * 6+PADDLE_Y_OFFSET);
			}else {
				add(bricks_4[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 8 + BRICK_HEIGHT * 7+PADDLE_Y_OFFSET);
			}
		}
		GRect[] bricks_5 = new GRect[20];
		for(int i = 0; i < bricks_4.length; i++) {
			bricks_5[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			bricks_5[i].setColor(Color.CYAN);
			bricks_5[i].setFilled(true);
			if(i <= 9) {
			    add(bricks_5[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP * 9 + BRICK_HEIGHT * 8+PADDLE_Y_OFFSET);
			}else {
				add(bricks_5[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 10 + BRICK_HEIGHT * 9+PADDLE_Y_OFFSET);
			}
		}
		// paddle setting
		paddle.setColor(Color.blue);
		paddle.setFilled(true);
		add(paddle, ((CANVAS_WIDTH - PADDLE_WIDTH) / 2), getHeight()-PADDLE_Y_OFFSET);
		addMouseListeners();
		// setting the ball
		ball.setColor(Color.red);
		ball.setFilled(true);
		add(ball, ((CANVAS_WIDTH - BALL_RADIUS)/2), ((CANVAS_HEIGHT - BALL_RADIUS)/2));
		
		ballGo();
		
	}
	private GObject gobj;
	private GPoint last;
	public void mouseMoved(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
		double nowX = paddle.getX();
		if((e.getX()) >= (CANVAS_WIDTH-PADDLE_WIDTH)) {
			paddle.move(0, 0);
		}else {
			paddle.move((e.getX()-nowX), 0);
		}
	}
    
    int numberBreakingBricks = 0;
	//make the ball move,and change its direction of speed when it meet something or the boundary.
	public void ballGo() {
		double vx=rgen.nextDouble(VELOCITY_X_MIN , VELOCITY_X_MAX);
		if(rgen.nextBoolean(0.5)) {
			vx = -vx;
	    }
		double vy = VELOCITY_Y;
		
		Boolean go = true;
		while(go) {
			double leftUpX = ball.getX();
			double leftUpY = ball.getY();
			if(leftUpY >= getHeight()-PADDLE_Y_OFFSET) {
				println("Game Over!!");
				break;
			}
		GPoint left = new GPoint(leftUpX, leftUpY + BALL_RADIUS);
			GObject gobj_left = getElementAt(left);
			GPoint right = new GPoint(leftUpX + BALL_RADIUS * 2, leftUpY + BALL_RADIUS);
			GObject gobj_right = getElementAt(right);
			GPoint up = new GPoint(leftUpX + BALL_RADIUS, leftUpY);
			GObject gobj_up = getElementAt(up);
			GPoint down = new GPoint(leftUpX + BALL_RADIUS, leftUpY + BALL_RADIUS * 2);
			GObject gobj_down = getElementAt(down);
			GPoint leftUp = new GPoint(leftUpX, leftUpY);

			if(ball.getY() <= 0) {
				vy = -vy;
			}
			
			if(gobj_down != null) {
				bounceClip.play();
				vy = -vy;
				go = judgeBricks(gobj_down);
			}else if(gobj_up != null) {
				bounceClip.play();
				vy = -vy;
				go = judgeBricks(gobj_up);
			}
			
			if(gobj_left != null) {
				bounceClip.play();
				vx = -vx;
				go = judgeBricks(gobj_left);
			}else if(gobj_right != null) {
				bounceClip.play();
				vx = -vx;
				go = judgeBricks(gobj_right);
			}
			if(ball.getX() <= 0 || ball.getX() >= CANVAS_WIDTH - BALL_RADIUS) {
				vx = -vx;
			}
			ball.move(vx, vy);
			
			try {
				Thread.sleep(10);
			}catch(Exception ex){}
		}
		
	}
	public Boolean judgeBricks(GObject a) {
		try {
			if(a.getWidth() == BRICK_WIDTH) {
				remove(a);
				numberBreakingBricks++;
			}
		}catch(Exception ex) {};
		if(numberBreakingBricks == 100) {
			println("Congratulation!!you are winner!!");
			return false;
		}else {
			return true;
		}
	}
}
