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

import javax.swing.JOptionPane;

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
	
	// the board
	GRect board=new GRect(44,10);
	
	//the ball
	GOval ball = new GOval(BALL_RADIUS,BALL_RADIUS);

	public void run() {
		setSize(420, 550);
		
		// Set the window's title bar text
		setTitle("Breakout");
		JOptionPane.showMessageDialog(null, "打完砖块胜利！即将开始，请确定！！", "球球必须死", JOptionPane.ERROR_MESSAGE);


		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);

		/* You fill this in, along with any subsidiary methods */
		
		//set bricks;
		
		GRect[] brick1 = new GRect[20];
		for(int i = 0; i < brick1.length; i++) {
			brick1[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick1[i].setColor(Color.RED);
			brick1[i].setFilled(true);
			if(i <= 9) {
			    add(brick1[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), PADDLE_Y_OFFSET);
			}else {
				add(brick1[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP + BRICK_HEIGHT+PADDLE_Y_OFFSET);
			}
		}
		
		GRect[] brick2 = new GRect[20];
		for(int i = 0; i < brick2.length; i++) {
			brick2[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick2[i].setColor(Color.ORANGE);
			brick2[i].setFilled(true);
			if(i <= 9) {
			    add(brick2[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP * 2+ BRICK_HEIGHT * 2+PADDLE_Y_OFFSET);
			}else {
				add(brick2[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 3 + BRICK_HEIGHT * 3+PADDLE_Y_OFFSET);
			}
		}
		
		GRect[] brick3 = new GRect[20];
		for(int i = 0; i < brick3.length; i++) {
			brick3[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick3[i].setColor(Color.YELLOW);
			brick3[i].setFilled(true);
			if(i <= 9) {
			    add(brick3[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP * 4 + BRICK_HEIGHT * 4+PADDLE_Y_OFFSET);
			}else {
				add(brick3[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 5 + BRICK_HEIGHT * 5+PADDLE_Y_OFFSET);
			}
		}
		
		GRect[] brick4 = new GRect[20];
		for(int i = 0; i < brick3.length; i++) {
			brick4[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick4[i].setColor(Color.GREEN);
			brick4[i].setFilled(true);
			if(i <= 9) {
			    add(brick4[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP * 6 + BRICK_HEIGHT * 6+PADDLE_Y_OFFSET);
			}else {
				add(brick4[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 7 + BRICK_HEIGHT * 7+PADDLE_Y_OFFSET);
			}
		}
		
		GRect[] brick5 = new GRect[20];
		for(int i = 0; i < brick5.length; i++) {
			brick5[i] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick5[i].setColor(Color.cyan);
			brick5[i].setFilled(true);
			if(i <= 9) {
			    add(brick5[i], ((i + 1) * BRICK_SEP + i * BRICK_WIDTH), BRICK_SEP * 8 + BRICK_HEIGHT * 8+PADDLE_Y_OFFSET);
			}else {
				add(brick5[i],((i-9) * BRICK_SEP + (i-10) * BRICK_WIDTH), BRICK_SEP * 9 + BRICK_HEIGHT * 9+PADDLE_Y_OFFSET);
			}
		}
		//set the board;
		
		board.setColor(Color.gray);
		board.setFilled(true);
        add(board,CANVAS_WIDTH/2-22,CANVAS_HEIGHT-2*PADDLE_Y_OFFSET);
        addMouseListeners();
        
        //the ball
        ball.setColor(Color.black);
        ball.setFilled(true);
        add(ball,CANVAS_WIDTH/2-BALL_RADIUS,CANVAS_HEIGHT/2-BALL_RADIUS);
        ball_function();
	}
	private GObject gobj;
	private GPoint last;
	public void mouseMoved(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
		if(e.getX()>=420-44) {
			board.move(0,0);
		}
		else {
			board.move(e.getX()-board.getX(),0);
		}
	}
	//create a rand()
	private RandomGenerator rgen = RandomGenerator.getInstance();
	public void ball_function()
	{
		//the starting v
		double vx = rgen.nextDouble(VELOCITY_X_MIN , VELOCITY_X_MAX);
		double vy = VELOCITY_Y;
		
		if (rgen.nextBoolean(0.5)) 
			vx = -vx;
		
		
		
		//bounce
		
		//judge the over or continue
		boolean judge = true;
		int n=0;
		while(judge)
		{
			//judge the bricks true or false
			GObject judge_top = getElementAt(ball.getX()+BALL_RADIUS,ball.getY());
			GObject judge_bottom = getElementAt(ball.getX()+BALL_RADIUS,ball.getY()+2*BALL_RADIUS);
			GObject judge_left = getElementAt(ball.getX(),ball.getY()+BALL_RADIUS);
			GObject judge_right = getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY()+BALL_RADIUS);
			
			if(ball.getX()<=0||ball.getX()+2*BALL_RADIUS>=CANVAS_WIDTH) {
				vx=-vx;
			}
			else if(ball.getY()<=0) {
				vy=-vy;
			}
			
			if(judge_top != null) {
				vy=-vy;
				vx++;
				vy++;
				n++;
				remove(judge_top);
			}
			else if(judge_bottom != null ) {
				vy=-vy;
			}
			if(judge_left != null) {
				vx=-vx;
				vx++;
				vy++;
				n++;
				remove(judge_left);
			}
			else if(judge_right != null ) {
				vx=-vx;
				vx++;
				vy++;
				n++;
				remove(judge_right);
			}
			if(ball.getY()>=CANVAS_HEIGHT-PADDLE_Y_OFFSET) {
				JOptionPane.showMessageDialog(null, "你输了，快确定了说你爱我 ！！", "球球必须死", JOptionPane.ERROR_MESSAGE);
				judge=false;
			}
			if(n==100) {
				JOptionPane.showMessageDialog(null, "你赢了，猜着你就爱我 ！！", "球球可以不死", JOptionPane.ERROR_MESSAGE);
				judge=false;

			}
			ball.move(vx,vy);
			try {
				Thread.sleep(40);
			}catch(Exception ex){}
		}
	}
}
	