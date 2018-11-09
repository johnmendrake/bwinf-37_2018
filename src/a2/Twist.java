package a2;

import java.io.File;
import java.io.FileNotFoundException;
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
					
					while(sc.hasNext(Pattern.compile("\w"))) {
						
					}
					
					sc.close();
				} catch (FileNotFoundException e) {
					
				}
			}
		}
	}
}
