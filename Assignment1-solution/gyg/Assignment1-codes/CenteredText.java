/*
 * File: CenteredText.java
 * -----------------------
 * This programs displays a message centered in the graphics window.
 */

import acm.graphics.*;
import acm.program.*;

public class CenteredText extends GraphicsProgram {	

	public void run() {
		setSize(800, 600);
		int centerX = getWidth()/2;
		int centerY = getHeight()/2;
		// You fill the codes here
//		GLabel java = new GLabel("Java rocks my socks!");
//		java.setFont("SansSerif-28");
//		add(java, centerX-java.getWidth()/2, centerY); //Center this sentence
		for(int i =0; i <10; i++) {
			GLabel qwq = new GLabel("Java rocks my socks!");
			qwq.setFont("SansSerif-28");
			add(qwq, centerX-qwq.getWidth()/2, (centerY-qwq.getHeight()*5)+qwq.getHeight()*i); //Center this sentence	
		}
	}
}