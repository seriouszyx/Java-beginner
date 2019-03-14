
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
		int a, b, c, i;
		a = 0;
		b = 1;
		i = 1;
		c = 0;
		println("fib(0)=0");
		println("fib(1)=1");
		while (c <= MAX_TERM_VALUE){
			i++;
			c = a + b;
			a = b;
			b = c;
			if(c < MAX_TERM_VALUE) {
				println("fib(" + i + ")=" + c);
			}
		} ;
	}

}
