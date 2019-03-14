
/*
 * File: Fibonacci.java
 * --------------------
 * This program lists the terms in the Fibonacci sequence up to
 * a constant MAX_TERM_VALUE, which is the largest Fibonacci term
 * the program will display.
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {

   /* Defines the largest term to be displayed */
	private static final int MAX_TERM_VALUE = 10000;

	public void run() {
		// You fill the codes here
		setSize(400, 400);
		println("This program lists the Fibonacci sequence.");
		int a = 0, b = 1;
		for(int i = 0; i < 100; i++) {
			println(a);
			if(b < 10000) {  //b比a大，因此只需要判断b是否超过100000就能控制a和b
				println(b);
			} else {
				break;
			}
			a = a +b;
			b = b + a;
		}
	}
}
