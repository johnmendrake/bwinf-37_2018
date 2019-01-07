package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Twist {

	public static void main(String[] args) {
		// when there are no parameters, the program hasn't anything to do
		if (args.length == 0) {
			System.out.println("Please run program with a file as parameter.");
		} else {
			// program can be run with multiple files as parameters
			for (int curArg = 0; curArg < args.length; curArg++) {
				try {
					File sourceFile = new File(args[curArg]);
					Scanner sc = new Scanner(sourceFile);

					while (sc.hasNextLine()) {
						// get the current line
						String curLine = sc.nextLine();
						// prepare an array list for the individual words
						ArrayList<String> words = new ArrayList<String>();
						// prepare an empty slot for the first word
						words.add("");

						// indicates in which index the sorted characters should go
						// even numbers are for special characters, uneven for normal letters, which can
						// be twisted
						int curIndex = 0;
						// goes through the complete line
						for (int curPos = 0; curPos < curLine.length(); curPos++) {
							// get the current character as a String, so it can easier be processed later on
							String curChar = Character.toString(curLine.charAt(curPos));

							if (curChar.matches("[a-zA-Zäöü]")) { // the character is a letter
								// uneven slots are for letters, they can be entered directly
								if (!(curIndex % 2 == 0)) {
									// appends letter to the String in the current slot
									words.set(curIndex, words.get(curIndex) + curChar);
								} else {
									// index is even, move on to the next
									curIndex++;
									// create slot
									words.add("");
									// appends letter to the String in the current slot
									words.set(curIndex, words.get(curIndex) + curChar);
								}
							} else { // the character isn't a letter
								// even slots are for special characters, they can be entered directly
								if ((curIndex % 2 == 0)) {
									// appends special character to the String in the current slot
									words.set(curIndex, words.get(curIndex) + curChar);
								} else {
									// index is uneven, move on to the next
									curIndex++;
									// create slot
									words.add("");
									// appends letter to the String in the current slot
									words.set(curIndex, words.get(curIndex) + curChar);
								}
							}
						}

						String output = "";
						for (int i = 0; i < words.size(); i++) {
							if (i % 2 == 0) {
								output += words.get(i);
							} else {
								output += twist(words.get(i));
							}
						}
						System.out.println(output);
					}

					sc.close();
				} catch (FileNotFoundException e) { // needed if the given file path is invalid
					System.out.println("WARNING!!! Given file doesn't exists or filepath is invalid!");
				}
			}
		}
	}

	public static String twist(String word) {
		if (word.length() <= 3) { // doesn't make sense to twist words with 3 characters or less
			return word;
		} else {
			// create a ArrayList to shuffle the characters
			ArrayList<String> tmpChars = new ArrayList<String>();
			// distribute the characters to the single indexes of the ArrayList
			for (int pos = 0; pos < word.length(); pos++) {
				tmpChars.add(Character.toString(word.charAt(pos)));
			}

			// preserve first and last character, they shouldn't get mixed up
			String tword = tmpChars.get(0);
			tmpChars.remove(0);
			String lastChar = tmpChars.get(tmpChars.size() - 1);
			tmpChars.remove(tmpChars.size() - 1);

			// shuffle the characters and prepare the String to be returned
			Collections.shuffle(tmpChars);
			for (int pos = 0; pos < tmpChars.size(); pos++) {
				tword += tmpChars.get(pos);
			}
			tword += lastChar;

			return tword;
		}
	}
}
