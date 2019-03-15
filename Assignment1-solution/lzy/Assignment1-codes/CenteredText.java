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
		double centerY = getHeight()/2;
		double centerX = getWidth()/2;
		GLabel label = new GLabel("Java rocks my socks!");
        label.setFont("SansSerif 28");
        add(label, centerX-100, centerY);
	}
}