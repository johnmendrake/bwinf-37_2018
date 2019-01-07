package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Untwist {

	public static void main(String[] args) {
		// prepare word list
		try {
			File wordlistFile = new File("src/a2/woerterliste.txt");
			Scanner wlsc = new Scanner(wordlistFile);

			ArrayList<String> wordlist = new ArrayList<String>();
			while (wlsc.hasNextLine()) {
				wordlist.add(wlsc.nextLine());
			}
			wlsc.close();
		} catch (Exception e) {
		}

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

//						String output = "";
//						for (int i = 0; i < words.size(); i++) {
//							if (i % 2 == 0) {
//								output += words.get(i);
//							} else {
//								output += untwist(words.get(i));
//							}
//						}
//						System.out.println(output);
					}

					System.out.println(untwist("hlHalo"));

					sc.close();
				} catch (FileNotFoundException e) { // needed if the given file path is invalid
					System.out.println("WARNING!!! Given file doesn't exists or filepath is invalid!");
				}
			}
		}
	}

	public static String untwist(String tword) {
		// count occurrence of characters
		HashMap<Character, Integer> occ = new HashMap<Character, Integer>(26);
		for (int i = 0; i < tword.length(); i++) {
			char curChar = tword.charAt(i);

			if (Character.isUpperCase(curChar)) {
				curChar = Character.toLowerCase(curChar);
			}

			occ.put(curChar, occ.getOrDefault(curChar, 0) + 1);
		}

		// get words from the word list sharing the first character and write them to a
		// scratch list

		// erase all words with a different length

		// erase all words with a different character at the end

		// for remaining words
		// count occurrence of characters and compare them to the figures of the passed
		// words
		// delete all words with different character count

		// I can't take grammar into account, so just take the first remaining word and
		// return it

		return "";
	}

//	public static int[] letterCount(String pWord) {
//		int[] output = new int[26];
//		return output;
//	}
}
