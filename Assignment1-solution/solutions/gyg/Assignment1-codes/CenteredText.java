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
		double centerX = getWidth()/2;
		double centerY = getHeight()/2;
		// You fill the codes here
		GLabel java = new GLabel("Java rocks my socks!");
		java.setFont("SansSerif-28");
		add(java, centerX-java.getWidth()/2, centerY); //Center this sentence
	}
}