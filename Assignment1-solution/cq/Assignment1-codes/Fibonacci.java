
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
		System.out.println("This program lists the Fibonacci sequence:");
		long fib0 = 0;
		long fib1 = 1;
		long fibOut = 1;
		System.out.println(fib0+"\n"+fib1);
		while(fibOut<MAX_TERM_VALUE)
		{
			System.out.println(fibOut);
			fibOut = fib0 + fib1;
			fib0 = fib1;
			fib1 = fibOut;
		}
	}

}
