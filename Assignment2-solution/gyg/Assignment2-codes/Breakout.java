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
import java.util.Random;
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
	public static final long DELAY = 1000 / 60;

	// Number of turns 
	public static final int NTURNS = 3;
	//声明添加砖块的方法
	private GRect[] bricks = new GRect[100];
	public void Bricks (int n, double x, double y, int cr) {
		bricks[n] = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
		bricks[n].setFilled(true);
		bricks[n].setColor(color[cr]);
		add(bricks[n], x ,y);
	}
	private Color[] color = {Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.CYAN,Color.BLACK};
	private GPoint Left,Right,Top,Bottom;
	private GObject LEFT,RIGHT,BOTTOM,TOP;
	private GOval BAll;
	private GRect paddle,bricksName;
	private double XCenter,vx,vy,paddleX;
	private int num = 0;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private GLabel label = new GLabel("");
	public void run() {
		setSize(420, 550);
		//显示打碎的方块个数
		label.setFont("Courier-24");
		label.setColor(Color.BLUE);
		label.setLabel("0");
		add(label,(getWidth()-label.getWidth())/2, getHeight());
		// Set the window's title bar text
		setTitle("Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		
		/* You fill this in, along with any subsidiary methods */
		//设置砖块
		XCenter = getWidth()/2;
		for(int i = 1; i <= 10; i ++) {
			for(int j = 0; j < 5; j++) {
				Bricks(((i-1)*10+j),XCenter-(BRICK_SEP/2 +BRICK_SEP*j+BRICK_WIDTH*(j+1)), (BRICK_Y_OFFSET+BRICK_HEIGHT*i+BRICK_SEP*(i-1)), (i-1)/2);			
			}
			for(int k = 0; k < 5; k++) {
				Bricks(((i-1)*10+k+5),XCenter+(BRICK_SEP/2 +BRICK_SEP*k+BRICK_WIDTH*k), (BRICK_Y_OFFSET+BRICK_HEIGHT*i+BRICK_SEP*(i-1)), (i-1)/2);
			}
		}
		//设置挡板
		paddle = new GRect(XCenter-BALL_RADIUS, getHeight()/2-BALL_RADIUS, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle, XCenter-PADDLE_WIDTH/2 ,getHeight()-PADDLE_Y_OFFSET);
		paddle.addMouseMotionListener(this);
		//设置球
		BAll = new GOval(BALL_RADIUS, BALL_RADIUS);
		BAll.setFilled(true);
		BAll.setColor(Color.PINK);
		add(BAll, XCenter-BALL_RADIUS, getHeight()/2-BALL_RADIUS);
		active();
	}
	public void active() {
		vx=rgen.nextDouble(VELOCITY_X_MIN , VELOCITY_X_MAX);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		vy=VELOCITY_Y;
		Boolean move = true;
		while(move) {
			double BAllX = BAll.getX();
			double BAllY = BAll.getY();
		    Left = new GPoint(BAllX,BAllY+BALL_RADIUS);
		    Right = new GPoint(BAllX+2*BALL_RADIUS,BAllY+BALL_RADIUS);
		    Bottom = new GPoint(BAllX+BALL_RADIUS,BAllY+BALL_RADIUS*2);
		    Top = new GPoint(BAllX+BALL_RADIUS,BAllY);
		    if(BAllX <= 0||BAllX+2*BALL_RADIUS >=CANVAS_WIDTH) {
		    	vx = -vx;
		    }
		    if(BAllY <= 0) {
		    	vy=-vy;
		    }
		    if(BAllY >= getHeight()-PADDLE_Y_OFFSET) {
		    	System.out.println("GAME OVER!");
		    	break;
		    }
		    LEFT = getElementAt(Left);
		    RIGHT = getElementAt(Right);
		    BOTTOM = getElementAt(Bottom);
		    TOP = getElementAt(Top);
		    
		   	if(TOP != null) {
		    	vy = -vy;
		    	move = judgeBricks(TOP);
		   	} else if(RIGHT != null) {
		    	vx = -vx;
		    	move = judgeBricks(RIGHT);
		    } else if
		    (BOTTOM != null) {
		    	vy = -vy;
		    	move = judgeBricks(BOTTOM);
		    }else if(LEFT != null) {
		    	vx = -vx;
		    	move = judgeBricks(LEFT);
		   	}
		    BAll.move(vx, vy); 
		    try {  
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {  
                e.printStackTrace();
            }
		}
	}
	public void mouseMoved(MouseEvent e) {
		paddleX = e.getX();
		if(paddleX >= CANVAS_WIDTH-PADDLE_WIDTH) {
			paddleX = CANVAS_WIDTH-PADDLE_WIDTH;
		}
		paddle.move(paddleX-paddle.getX(), 0);
	}
	//判断方块颜色以及是否为挡板,难度升级
	public Boolean judgeBricks(GObject brick) {
		AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
		int colornum = 0;
		try {
			if(brick.getWidth() == BRICK_WIDTH && brick.getColor() == Color.CYAN ) {
				remove(brick);
				bounceClip.play();
				++num;
				label.setLabel(" "+num+" ");
			} else if(brick.getWidth() == BRICK_WIDTH && brick.getColor() != Color.CYAN) {
				for(int i = 0;i <color.length; i++) {
					if(brick.getColor() == color[i]) {
						colornum = i;
					}
				}
				brick.setColor(color[colornum+1]);
				bounceClip.play();
			}
		}catch(Exception ex) {};
		if(num == 100) {
			System.out.println("You win !");
			return false;
		}else {
			return true;
		}
	}
	  
}
