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
import java.util.ArrayList;
import java.util.List;

public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	//it's not enough sometimes;so i have the N_GUESSES = Word.length+3;
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_X = 125;
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
	
	public void init() {
		add(canvas);
		drawBackground();
	}
	
	private static final GImage bg = new GImage("background.jpg");
	private static final GImage pc = new GImage("parachute.png");
	private static final GImage karelFlipped = new GImage("karelFlipped.png");
	private static final GImage karel = new GImage("karel.png");
	private static final GLine line1 = new GLine(100, 180, 200, 230);
	private static final GLine line2 = new GLine(200, 180, 200, 230);
	private static final GLine line3 = new GLine(300, 180, 200, 230);
	
	private void drawBackground() {
		bg.setSize(800,500);
		canvas.add(bg, 0, 0);
		
		pc.setSize(PARACHUTE_WIDTH,PARACHUTE_HEIGHT);
		canvas.add(pc,PARACHUTE_Y,PARACHUTE_Y);
		
		karel.setSize(KAREL_SIZE,KAREL_SIZE);
		canvas.add(karel,KAREL_X,KAREL_Y);
		
		canvas.add(line1);
		canvas.add(line2);
		canvas.add(line3);
		
	}
	
	private void Failing() {
		canvas.removeAll();
		
		bg.setSize(800,500);
		canvas.add(bg, 0, 0);
		
		karelFlipped.setSize(KAREL_SIZE,KAREL_SIZE);
		canvas.add(karelFlipped,KAREL_X,KAREL_Y);
		
		pc.setSize(PARACHUTE_WIDTH,PARACHUTE_HEIGHT);
		canvas.add(pc,PARACHUTE_Y,PARACHUTE_Y);
		
	}
	
	public void run() {
		setSize(800, 500);
		// shall we?
		
		String word = getRandomWord();
		char[] charWord = word.toCharArray();
		char[] lines = new char[charWord.length];
		boolean j=true;
		for(int i=0;i<lines.length;i++) {
				lines[i]='_';
		}
		println("Welcome to Hangman");
		print("Your word now looks like this:");
		for(int i=0;i<lines.length;i++) {
			print(lines[i]+" ");
		}
		print("\n");
		int m=lines.length+3;
		String str = null;
		println("You have "+m+" guesses left.");

		while(m>0) {
			str=Input(str);
			m=Judge(str,charWord,lines,m);
			
			if(m>0) {
				print('\n');
				println("You're completely hung.");
			}
			if(m==0) {
				print('\n');
				println("You're completely hung.");
				Failing();
			}
			if(m==-1) {
				println("You win!");
			}
		}
		print("The word is:"+word);
	}
	
	//the way
	
	
	/**
	 * Method: Get Random Word
	 * -------------------------
	 * This method returns a word to use in the hangman game. It randomly 
	 * selects from among 10 choices.
	 */
	private String getRandomWord() {
		int index = rg.nextInt(50);
		ArrayList<String> words = new ArrayList<String>();
		try {
			File myFile = new File("ShorterLexicon.txt");
			FileInputStream fileReader = new FileInputStream(myFile);
			InputStreamReader filewords = new InputStreamReader(fileReader, "utf-8");
			BufferedReader reader = new BufferedReader(filewords);
			String line = null;
			while((line = reader.readLine()) != null) {
				words.add(line);
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return words.get(index);
	}
	
	private String Input(String str) {
		print("Your guess: ");
		str = readLine();
		return str;
	}
	
    private static int n=0;
	private int Judge(String str,char[] charWord,char[] lines,int m) {
		char a = str.charAt(0);
		boolean b=false;

		if(a>='a'&&a<='z')
			a-=32;
		for(int i=0;i<lines.length;i++) {
			if(a==charWord[i]) {
				lines[i]=a;
				b=true;
				n++;
			}

		}
		String line = new String(lines);
		String word = new String(charWord);
		//judge the end
		if(line.equals(word)) {
			m=-1;
			
			return m;
		}
		if(b==false) {
			m--;
			println("There are no "+a+"' in the word.");
			println("You have "+m+" guesses left.");
		}
		else {
			println("Your guess is correct.");
			println("You have "+m+" guesses left.");
		}
			
		print("Your word looks like this:");
		for(int j=0;j<lines.length;j++) {
			print(lines[j]+" ");
		}
		return m;
	}

}
