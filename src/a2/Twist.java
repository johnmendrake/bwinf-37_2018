package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Twist {

	public static void main(String[] args) {
		// when there are no parameters, the program hasn't anything to do
		if (args.length == 0) {
			System.out.println("Please run program with a file as parameter.");
		} else {
			// program can be run with multiple files as parameters
			for (int i = 0; i < args.length; i++) {
				try {
					File sourceFile = new File(args[i]);
					Scanner sc = new Scanner(sourceFile);
//					sc.useDelimiter("\\W");
//					sc.useDelimiter("[^a-zA-Zäöü]+");
//
//					while (sc.hasNext()) {
//						System.out.println(sc.next());
//					}

					while (sc.hasNextLine()) {
						String[] words = sc.nextLine().split(" ");
						for (int j = 0; j < words.length; j++) {
							words[j] = twist(words[j]);
						}
						System.out.println(Arrays.toString(words));
					}

					sc.close();
				} catch (FileNotFoundException e) { // needed if the given file path is invalid
					System.out.println("WARNING!!! Given file doesn't exists or filepath is invalid!");
				}
			}
		}
	}

	/**
	 * Checks whether the given parameter is a normal word, that can be twisted. If
	 * this is the case, the method returns the twisted word, while otherwise the
	 * given String is returned unchanged.
	 * 
	 * @param word the String to be twisted
	 * @return the twisted String
	 */
	public static String twist(String word) {
		if (word.matches("[a-zA-Zöäü]+")) {
			List<String> tmpChars = new ArrayList<>();
			for (int curChar = 0; curChar < word.length(); curChar++) {
				tmpChars.add(Character.toString(word.charAt(curChar)));
			}

			String tword = "";
			tword = tword + tmpChars.get(0);
			tmpChars.remove(0);
			String lastChar = tmpChars.get(tmpChars.size() - 1);
			tmpChars.remove(tmpChars.size() - 1);

			Collections.shuffle(tmpChars);
			for (int i = 0; i < tmpChars.size(); i++) {
				tword = tword + tmpChars.get(i);
			}

			tword = tword + lastChar;

			return tword;
		} else {
			return word;
		}
	}
}
