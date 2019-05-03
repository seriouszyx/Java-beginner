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
		String output0= "";
		String output = "";
		// TODO: implement altCaps!
		//需要实现的功能：忽略空字符串，全部变成小写，大写，小写，大写的格式
		String input0 = input.toLowerCase();
		char[] a = input0.toCharArray();
		for(int i = 0; i < a.length; i++)
		{
			if(!((a[i] >= 'a'&&a[i] <= 'z') || (a[i] >= 'A'&&a[i] <= 'B')))
				continue;
			else
				output0 = output0 + a[i];
//				if(!Character.isLowerCase(a[i]))
//					 a[i] = Character.toLowerCase(a[i]);
//				if(!Character.isUpperCase(a[i]))
//					a[i] = Character.toUpperCase(a[i]);
			
		}
		String output1 = output0.toLowerCase();
		String output2 = output0.toUpperCase();
		char[] b = output1.toCharArray();//想到的是：将非字母字符去掉以后得到一个全是字母的数组，
		char[] c = output2.toCharArray();
		//然后进行字母大小转换以后再合并字符
		for(int i = 1; i < b.length; i+=2)
		{
			b[i] = c[i];
		}
		//此时的b数组已经是由间接字母展示
		//判断字符，如果是字符跳过，不是就全部替换
		for(int i = 0, j = 0; i < a.length; i++,j++)
		{
			if(!((a[i] >='a'&& a[i] <='z') || (a[i] >= 'A'&&a[i] <= 'B')))//如果不是字母，注意是大于等于或者是小于等于！
			{
				j--;
			}
			else
			{
				a[i] = b[j];
			}
		}
		for(int i = 0; i < a.length; i++)
		{
			output = output + a[i];
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
	
	private void replace(String str, char content, int index)
	{
		//返回：			从头开始到指定字符位置的字符串，注意不包括后面，然后添加添加的字符，连接后面
		str = str.substring(0, index) + content + str.substring(index+1);
	}


}
