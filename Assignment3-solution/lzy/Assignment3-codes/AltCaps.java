/*
 * File: AltCaps.java
 * ------------------
 * A sandcastle warmup for assgignment 4
 */

import acm.program.*;

public class AltCaps extends ConsoleProgram {

	/**
	 * Method: AltCaps
	 * ---------------
	 * Takes in an input string and returns the same string,
	 * except that the capitalization of the letters is changed
	 * to be alternating.
	 * Example usage:
	 * altCaps("aaaaa") -> "aAaAaA"
	 * altCaps("hello world") -> "hElLo WoRlD"
	 */
	private String altCaps(String input) {
		String output = "";
		// TODO: implement altCaps! 
		char[] inputArray=input.toCharArray();
		int k=0;
		for(int i=0;i<inputArray.length;i++) {
			if((int)inputArray[i]>=(int)'A'&&(int)inputArray[i]<=(int)'Z'||(int)inputArray[i]>=(int)'a'&&(int)inputArray[i]<=(int)'z') {
				k++;
				if(k%2!=0) {
					if((int)inputArray[i]>=(int)'A'&&(int)inputArray[i]<=(int)'Z') 
						inputArray[i]+=32;
				}
				else {
					if((int)inputArray[i]>=(int)'a'&&(int)inputArray[i]<=(int)'z')
						inputArray[i]-=32;
				}	
			}		
		}
		output=new String(inputArray);
		return output;
	}

	/****************************************************
	 *                  STARTER CODE                    *
	 * You can read this code, but you should not edit  *
	 * It (except to add more tests, if you so desire)  *
	 ***************************************************/

	// an instance variable which keeps track of how many tests 
	// have been run.
	private int testIndex = 0;
	
	// This run method executes a barrage of tests.
	public void run() {
		runTest("aaaaaa", "aAaAaA");
		runTest("bbbbbb", "bBbBbB");
		runTest("hello", "hElLo");
		runTest("hello world", "hElLo WoRlD");
		runTest("i love the beach", "i LoVe ThE bEaCh");
		runTest("----altj----", "----aLtJ----");
	}

	/**
	 * Method: Run Test
	 * ----------------
	 * Takes in an input and an expected output, and checks
	 * if the method altCap produces the correct output! Each
	 * call runs exactly one test.
	 */
	private void runTest(String input, String expectedOutput) {
		// call the altCaps method!
		String output = altCaps(input);
		
		// print out the results
		println("Test #:   " + testIndex);
		println("Input:    " + input);
		println("Output:   " + output);
		println("Expected: " + expectedOutput);
		
		// don't forget to use .equals when comparing strings
		if(expectedOutput.equals(output)) {
			println("Test passed");
		} else {
			println("Test failed");
		}
		
		// this adds a blank line so each test looks like a
		// paragraph of text
		println("");
		
		// keep track of how many tests have been run
		testIndex++;
	}





}
