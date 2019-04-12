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
	public static final double CANVAS_HEIGHT = 600;//在屏幕中显示的尺寸

	// Number of bricks in each row
	public static final int NBRICK_COLUMNS = 10;//每一行的砖块

	// Number of rows of bricks
	public static final int NBRICK_ROWS = 10;

	// Separation between neighboring bricks, in pixels
	public static final double BRICK_SEP = 4;//砖的间距

	// Width of each brick, in pixels,显示屏的每个砖块的宽度，需要计算
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	// Height of each brick, in pixels
	public static final double BRICK_HEIGHT = 8;

	// Offset of the top brick row from the top, in pixels
	public static double BRICK_Y_OFFSET = 70;
	
	//每一行的实际宽度
	public static final double BRICK_X_OFFSET =  Math.floor(
			(NBRICK_COLUMNS * BRICK_WIDTH) + (NBRICK_COLUMNS-1) * BRICK_SEP);

	// Dimensions of the paddle，鼠标移动的挡板的长宽
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 10;

	// Offset of the paddle up from the bottom ,距离底部的距离
	public static final double PADDLE_Y_OFFSET = 30;

	// Radius of the ball in pixels，球
	public static final double BALL_RADIUS = 10;

	// The ball's vertical velocity.，垂直速率
	public static final double VELOCITY_Y = 3.0;

	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).，生成的随机速度的范围
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	// Animation delay or pause time between ball moves (ms)暂停的时间
	public static final double DELAY = 1000.0;

	// Number of turns 
	public static final int NTURNS = 3;

	// Number of score 
	public static int NSCORES = 0;

	public void run() {
		int turns = NTURNS;
		int scores = 0;
		while(turns>0)
		{
			setSize(420, 550);
			
			// Set the window's title bar text
			setTitle("Breakout");

			// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
			// and getHeight() to get the screen dimensions, not these constants!
			setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
			final double WIDTH = Math.floor(getWidth()/2 - BRICK_X_OFFSET / 2);
			setBricks(NBRICK_COLUMNS,NBRICK_ROWS, WIDTH, BRICK_WIDTH, BRICK_HEIGHT);//依次：一行砖的个数，一列砖的个数，距离右边的距离，砖的宽度，砖的高度
			setPaddle();
			try {
				scores +=setBall(VELOCITY_X_MIN/1000, VELOCITY_X_MAX/1000, VELOCITY_Y/1000, turns, NSCORES);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			removeAll();
			BRICK_Y_OFFSET -= NBRICK_ROWS*BRICK_HEIGHT+(BRICK_SEP*NBRICK_ROWS);
			
			turns--;
		}
		setGameout(scores);
	}
	
	public void setBricks(double columns, double rows, double width, double brick_width, double brick_height)
	{
		//设置部分
		//行块
		double num = 0;
		while(num <= NBRICK_ROWS)
		{
			if(num == 0 || num ==1)
			{
				setBrick(columns, width, (int)num, Color.red, brick_width, brick_height);
			}
			if(num == 2 || num == 3)
			{
				setBrick(columns, width, (int)num, Color.orange, brick_width, brick_height);
			}
			if(num == 4||num == 5)
			{
				setBrick(columns, width, (int)num, Color.yellow, brick_width, brick_height);
			}
			if(num == 6 || num == 7)
			{
				setBrick(columns, width, (int)num, Color.green, brick_width, brick_height);
			}
			if(num == 8 || num == 9)
			{
				setBrick(columns, width, (int)num, Color.cyan, brick_width, brick_height);
			}
			num++;
		}
			
	}
	
	
	void setBrick(double columns, double width, int i, Color a, double brick_width, double brick_height)
	{
		int j = i;
		for(int k = (int)(i*columns); k<(j+1)*columns; k++)
		{
			bricks_col[k] = new GRect(brick_width, brick_height);
			bricks_col[k].setColor(a);
			bricks_col[k].setFilled(true);
			add(bricks_col[k],width,BRICK_Y_OFFSET);
			width += BRICK_SEP + BRICK_WIDTH;
		}	
		BRICK_Y_OFFSET +=BRICK_HEIGHT+BRICK_SEP;
		width = Math.floor(getWidth()/2 - BRICK_X_OFFSET / 2);
	}
	
	public void setPaddle()
	{
		paddle.setColor(Color.black);
		paddle.setFilled(true);
		
		addMouseListeners();
	}
	public int setBall(double vxMin, double vxMax, double vy, double turns, int scores) throws InterruptedException 
	{
		double turn = turns;
		GOval ball = new GOval(2*BALL_RADIUS, 2*BALL_RADIUS);
		ball.setColor(Color.black);
		ball.setFilled(true);
		add(ball,this.getWidth()/2-BALL_RADIUS,this.getHeight()/2-BALL_RADIUS);
		double vx = rgen.nextDouble(vxMin, vxMax);
		if(rgen.nextBoolean(0.5))
			vx = -vx;
		while(true)
		{
			ball.move(vx, vy);
			gobj1 = getElementAt(ball.getX(),ball.getY());
			gobj2 = getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY());
			gobj3 = getElementAt(ball.getX(),ball.getY()+2*BALL_RADIUS);
			gobj4 = getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY()+2*BALL_RADIUS);
			for(int i = 0; i<bricks_col.length; i++)
			{
				if(gobj1 == bricks_col[i]||gobj2 == bricks_col[i]||gobj3 == bricks_col[i]||gobj4 == bricks_col[i])
				{
					AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
					bounceClip.play();
					
					vy *= 1.11;//机打一次改变速度修改
					vx *= 1.11;
					remove(bricks_col[i]);//不同颜色砖块得到不同分数
					if(0 <= i && i<20)
						scores += 20;
					if(20 <= i && i<40)
						scores += 15;
					if(40 <= i && i<60)
						scores += 10;
					if(60 <= i && i<80)
						scores += 5;
					if(80 <= i && i<=100)
						scores += 1;
				}
					
			}
			if(gobj1 != null && gobj2 != null)
			{
				vy = -vy;
			}
			else if(gobj2 != null && gobj4 != null)
			{
				vx = -vx;
			}
			else if(gobj1 != null && gobj3 != null)
			{
				vx = -vx;
			}
			else if(gobj3 != null && gobj4 != null)
			{
				vy = -vy;
			}
			if(ball.getX()<0||ball.getX()>this.getWidth()-2*BALL_RADIUS)
			{
				vx = -vx;
			}
			if(ball.getY()<0)
				vy = -vy;
			if(ball.getY()>this.getHeight()+2*BALL_RADIUS)
			{
				setGameOver((int)turns);
				return scores;
			}
			
		}
	}
	
	public void setGameOver(int turns) throws InterruptedException
	{
		turns--;
		GLabel label = new GLabel("only "+turns+" chance(s)");
		label.setFont("Courier-52");
		add(label, this.getWidth()/2-label.getWidth()/2, this.getHeight()/2);
		Thread.sleep((long) DELAY);
		remove(label);
	}
	
	public void setGameout(int scores)
	{
		GLabel label = new GLabel("Game over");
		label.setFont("Courier-52");
		add(label, this.getWidth()/2-label.getWidth()/2, this.getHeight()/2);

		GLabel scorelabel = new GLabel("You got "+scores+" score(s)");
		scorelabel.setFont("Courier-20");
		add(scorelabel, this.getWidth()/2-scorelabel.getWidth()/2, this.getHeight()/2+label.getHeight());
	}
	
	public GObject gobj1;
	public GObject gobj2;
	public GObject gobj3;
	public GObject gobj4;
	
	public void mouseMoved(MouseEvent e)
	{
		last = new GPoint(e.getPoint());
//		gobj = getElementAt(last);
//		if(gobj != null) {
//			label.setColor(Color.RED);
//		} else {
//			label.setColor(Color.BLUE);
//		}
		double moveMentX = e.getX() - paddle.getWidth()/2;
		if(moveMentX < 0)
		{
			moveMentX = 0;
		}
		else if(moveMentX > this.getWidth() - PADDLE_WIDTH)
		{
			moveMentX = this.getWidth() - PADDLE_WIDTH;
		}
		add(paddle, moveMentX,this.getHeight()-PADDLE_Y_OFFSET);
	}

	
	private GPoint last;
	
	private GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);

	private GRect[] bricks_col = new GRect[NBRICK_COLUMNS*NBRICK_ROWS];
	//ball
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
























