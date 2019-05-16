/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	/***********************************************************
	 *              Instance Variables                         *
	 ***********************************************************/
	
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();
	
	private GCanvas canvas = new GCanvas();
	
	/***********************************************************
	 *                    Methods                              *
	 ***********************************************************/
	
	private static final Image image2 = MediaTools.loadImage("Karel.png");
	private static final Image image3 = MediaTools.loadImage("KarelFlipped.png");
	private static final GImage Karel = new GImage(image2);
	private static final GImage KarelFlipped = new GImage(image3);
	private static final GLine line1 = new GLine(100, 180, 200, 230);
	private static final GLine line2 = new GLine(200, 180, 200, 230);
	private static final GLine line3 = new GLine(300, 180, 200, 230);
	public void init() {
		add(canvas);
		Image bgimg = MediaTools.loadImage("background.jpg");
		Image parachute = MediaTools.loadImage("parachute.png");
		GImage Background = new GImage(bgimg);
		GImage Parachute = new GImage(parachute);
		Karel.setSize(KAREL_SIZE, KAREL_SIZE);
		KarelFlipped.setSize(KAREL_SIZE, KAREL_SIZE);
		Parachute.setSize(PARACHUTE_WIDTH, PARACHUTE_HEIGHT);
		canvas.add(Background, 0, 0);
		canvas.add(Parachute, canvas.getWidth() / 2, PARACHUTE_Y);
		canvas.add(Karel, 130, KAREL_Y);
		canvas.add(line1);
		canvas.add(line2);
		canvas.add(line3);
		canvas.add(RinghtChar, 205 - RinghtChar.getWidth() / 2, PARTIALLY_GUESSED_Y);
		canvas.add(inputGlable, 205 - inputGlable.getWidth() / 2, INCORRECT_GUESSES_Y);
		RinghtChar.setFont(PARTIALLY_GUESSED_FONT);
		inputGlable.setFont(INCORRECT_GUESSES_FONT);
	}
	
	public void run() {
		setSize(800, 500);
		// shall we?;
		println("Welcom to Hangman");
		GameRule(rg);
	}
	
	/**
	 * Method: Get Random Word
	 * -------------------------
	 * This method returns a word to use in the hangman game. It randomly 
	 * selects from among 10 choices.
	 */
//	private String getRandomWord() {
//		int index = rg.nextInt(10);
//		if(index == 0) return "BUOY";
//		if(index == 1) return "COMPUTER";
//		if(index == 2) return "CONNOISSEUR";
//		if(index == 3) return "DEHYDRATE";
//		if(index == 4) return "FUZZY";
//		if(index == 5) return "HUBBUB";
//		if(index == 6) return "KEYHOLE";
//		if(index == 7) return "QUAGMIRE";
//		if(index == 8) return "SLITHER";
//		if(index == 9) return "ZIRCON";
//		throw new ErrorException("getWord: Illegal index");
//	}
	private String getRandomWord(int ran) {
		BufferedReader reader = null;
		String word = null;
		String RandomWord = "";
		int line = 0;
		try{
		   reader = new BufferedReader(new FileReader(new File("/Assignment3-codes/HangmanLexicon.txt")));
		   while((word = reader.readLine())!=null){
			   	if(line == ran) {
			   		RandomWord = reader.readLine();
			   	}
				line++;
		   }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(reader!=null){
				try{
					reader.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return RandomWord;
	}
	
	private String inputglable="";
	private String ringhtchar="";
	private	GLabel RinghtChar = new GLabel(inputglable);
	private GLabel inputGlable = new GLabel(ringhtchar);
	
	public void GameRule(RandomGenerator rg) {
		int guesses = N_GUESSES;
		String word = getRandomWord(rg.nextInt(10));
		char[] Charry = word.toCharArray();
		char[] temp =new char[Charry.length];
		for(int i=0;i<Charry.length;i++) {
			temp[i]='_';
		}
		//设置GLable
		PrintInformation(temp,guesses);
		while(true&&guesses>0) {
			String tempstr =readLine().toUpperCase();
			if(tempstr.length()>1) {
				print("输入不合法，请重新输入");
				tempstr =readLine().toUpperCase();
			}
			char input = tempstr.charAt(0);
			inputglable = String.valueOf(inputglable+input);
			inputGlable.setLabel(inputglable);
			int code =JudgeResult(input,Charry,temp);
			if(code==0) {
				guesses--;
				PrintInformation(temp,guesses,input,code,word);
			} else if(code==-1){
				PrintInformation(temp,guesses,input,code,word);
				break;
			} else {
				PrintInformation(temp,guesses,input,code,word);
			}
		}
	}
	//重载方法一个用于第一次打印信息
	public void PrintInformation(char[] temp,int leftguesses) {
		print("You word now looks like:");
		for(int i=0;i<temp.length;i++) {
			print(temp[i]+" ");
		}
		println();
		println("You have " + leftguesses + "left.");
		print("Your guess:");
	}
	public void PrintInformation(char[] temp,int leftguesses,char input,int JudgeResult,String word) {
		if(JudgeResult==0 && leftguesses>0) {
			println("There is no " + input +"' in the word.");
		} else if(JudgeResult==-1 && leftguesses>0){
			println("Your guess is correct.");
			println("You win");
			println("The word is:" + word);
			return;
		} else if(leftguesses>0 && JudgeResult>0) {
			println("Your guess is correct.");
		} else if(JudgeResult==0 && leftguesses==0) {
			println("You're completely hung");
			println("The word is:" + word);
			canvas.remove(Karel);
			canvas.remove(line1);
			canvas.remove(line2);
			canvas.remove(line3);
			canvas.add(KarelFlipped, 130, KAREL_Y);
			return;
		}
		print("You word now looks like:");
		for(int i=0;i<temp.length;i++) {
			print(temp[i]+" ");
		}
		ringhtchar = String.valueOf(temp);
		RinghtChar.setLabel(ringhtchar);
		println();
		println("You have " + leftguesses + "left.");
		print("Your guess:");
	}
	
	public int JudgeResult(char input,char[] arry,char[] temp) {
		int count =0;
		for(int i=0;i<arry.length;i++) {
			if(input==arry[i]) {
				temp[i]=arry[i];
				count++;
			}
		}
		int termination = 0;
		for(int i=0;i<arry.length;i++) {
			if(arry[i]==temp[i]) {
				termination++;
			}
		}
		if(count==0) {
			return 0;
		} else if(termination==temp.length){
			return -1;
		} else {
			return count;
		}
	}
	
	
} 
