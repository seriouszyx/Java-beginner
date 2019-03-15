
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
		
		int a=1;
		
		for(int n=0;n<10000;) {
			
			println(n);
			
			n+=a;
			
			a=n-a;
			
		}
	}

}
