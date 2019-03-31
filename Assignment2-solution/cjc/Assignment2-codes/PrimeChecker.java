/*
 * File: PrimeChecker.java
 * -----------------------------
 * Checks whether a series of numbers are prime
 */

import java.util.Scanner;

import acm.program.*;

public class PrimeChecker extends ConsoleProgram {
	
	int[] testCases = {2, 3, 8, 37,  42, 87, 361, 382, 729, 1019};
	boolean[] answers = {true, true, false, true, false, false, false, false, false, true};
	
	/**
	 * Don't modify this method! You only need to edit the 
	 * isPrime method so that it works correctly. 
	 * 
	 * The run method just tests your method against a variety
	 * of numbers to make sure that it's behaving as expected.
	 */
	public void run() {	
		for (int i = 0; i < testCases.length; i++) {
			int testCase = testCases[i];
			boolean solution = answers[i];
			boolean returned = isPrime(testCase);
			
			if (solution == returned) {
				println("Your solution worked for n = " + testCase + ".");
				
			} else {
				println("Your method returned " + returned + " for n = " + 
						testCase + ", but it should have returned " + solution + ".");
			}
		}
	}
	
	/**
	 * 
	 * @param n a positive integer greater than 1
	 * @return true if n is prime and false otherwise
	 */
	public boolean isPrime(int n) {

		double square = Math.sqrt(n);//判断是否未为质数，只需看这个数开方之后的数之前的数。如果在开方数之前的数都不能整除，后面的就不用看了。

		int remainder;
		int i;
		for(i = 2; i < square; i++) {
			remainder = n % i;
			if(remainder == 0) {
				break;
			}
		}
		if(i > square) { //当循环提前结束的时候，就说明不是质数。否则就是质数。
			return true;
		}else {
			return false;
		}
		
	}

	public static void main(String[] args) {
		PrimeChecker primerChecker = new PrimeChecker();
		primerChecker.run();
	}

}
