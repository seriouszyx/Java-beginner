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
		// You fill the codes here
		double centerY = getHeight();
		double centerX = getWidth();
		GLabel label = new GLabel("GraphicsProgram");
		label.setFont("SansSerif-28");
		double labelWidth = label.getWidth();
		double labelHeight = label.getHeight();
		add(label, (centerX - labelWidth)/2, (centerY - labelHeight)/2);
	}
	
	
}