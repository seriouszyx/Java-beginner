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
		int stringLength = input.length();
		int change = 1;
		for(int i = 0; i < stringLength; i++) {
			char cha = input.charAt(i);
			// 先判断是否为字母，如果不是字母直接拼接到output上
			if((cha >= 'a' && cha <= 'z') || (cha >= 'A' && cha <= 'Z')) {
				// 判断改字母的相对位置编号是不是偶数，如果时偶数并且时小写，就变小写为大写。反之则变大写为小写
				if((change % 2) == 0 && (cha >= 'a' && cha <= 'z')) {
					char cha1 = (char) (cha - 32);
					output = output + cha1;
				}else if((change % 2) != 0 && (cha >= 'A' && cha <= 'Z')) {
					char cha1 = (char) (cha + 32);
					output = output + cha1;
				}
				else {
					output = output + cha; // 如果都不是，说明时符合输出条件的，就直接拼接上去就ok了
				}
				change++;
			}else {
				output = output + cha;
			}
		}
		return output;
	}

	/****************************************************
	 *                  STARTER CODE                    *
	 * You can read this code, but you should not edit  *
	 * It (except to add more tests, if you so desire)  *
	 ****************************************************/

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
