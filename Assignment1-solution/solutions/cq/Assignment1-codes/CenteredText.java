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
		GLabel label = new GLabel("Programming is Awesome!");
        label.setFont("SansSerif-28");
		add(label, this.getCenterX()/2, this.getCenterY());
	}
}