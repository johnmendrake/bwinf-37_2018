package j2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class J2 {

	public static void main(String[] args) {
		// when there are no parameters, the program hasn't anything to do
		if (args.length == 0) {
			System.out.println("Please run program with database file as parameter.");
		} else {
			// program can be run with multiple database files as parameters
			for (int x = 0; x < args.length; x++) {
				try {
					System.out.println("NOTE: The following lines are dedicated to debugging.");

					// following line of code is for debugging and testing to replace command line
					// argument
					// NOTE: To make use of it, you need the right structure of directories,
					// otherwise it may not work or has to be adapted!
//					File sourceFile = new File("src/j2/baywatch1.txt");

					// set source file to the parameter, currently being processed
					File sourceFile = new File(args[x]);
					// debugging information
					System.out.printf("%s\texists: %s\n", sourceFile.getAbsolutePath(), sourceFile.exists());
					// create scanner on file
					Scanner sc = new Scanner(sourceFile);

					// process parrot line
					// get first line from sourceFile
					String parrotStr = sc.nextLine();
					System.out.printf("parrotStr:\t%s\n", parrotStr);
					// create ArrayList
					List<Integer> parrot = new ArrayList<>();
					// create scanner on string
					Scanner parrotSc = new Scanner(parrotStr);
					parrotSc.useDelimiter(" ");
					while (parrotSc.hasNextInt()) {
						parrot.add(parrotSc.nextInt());
					}
					System.out.printf("parrotStr as array:\t%s\n", parrot);

					System.out.println();

					// process pirate line
					String pirateStr = sc.nextLine();
					System.out.printf("pirateStr:\t%s\n", pirateStr);
					List<Integer> pirate = new ArrayList<>();
					Scanner pirateSc = new Scanner(pirateStr);
					pirateSc.useDelimiter(" ");
					while (pirateSc.hasNext()) {
						String tmp = pirateSc.next();
						if (Pattern.matches("[0-9]", tmp)) {
							pirate.add(Integer.parseInt(tmp));
						} else {
							pirate.add(0);
						}
					}
					System.out.printf("pirateStr as array:\t%s\n", pirate);

					System.out.println();

					// adjust arrays
					for (int i = 0; i < pirate.size(); i++) {
						for (int j = 0; j < pirate.size(); j++) {
							if (pirate.get(j) != 0) {
								if (pirate.get(j) != parrot.get(j)) {
									parrot.add(0, parrot.get(parrot.size() - 1));
									parrot.remove(parrot.size() - 1);
									break;
								}
							}
						}
					}
					System.out.println(pirate);

					List<String> tmpPi = new ArrayList<>();
					for (int i = 0; i < pirate.size(); i++) {
						tmpPi.add(i, Integer.toString(pirate.get(i)));
					}
					for (int i = 0; i < tmpPi.size(); i++) {
						if (Integer.parseInt(tmpPi.get(i)) == 0) {
							tmpPi.set(i, " ");
						}
					}
					System.out.println(tmpPi);

					System.out.println(parrot);
					String delimiter = "";
					for (int i = 0; i < parrot.toString().length(); i++) {
						delimiter = delimiter + "-";
					}
					System.out.println(delimiter);
					System.out.printf("\nDie korrekte Reihenfolge beginnend im Norden lautet:\t(%s)\n%s\n",
							sourceFile.getName(), parrot);

					System.out.println();
					System.out.println();

					// close scanners
					parrotSc.close();
					pirateSc.close();
					sc.close();
				} catch (FileNotFoundException e) {
					System.out.println("Warning! Given FILE doesn't exist!");
				}
			}
		}
	}
}
