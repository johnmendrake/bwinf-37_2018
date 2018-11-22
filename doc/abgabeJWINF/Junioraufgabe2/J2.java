import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class J2 {

	/**
	 * The main method of the program. All essential work is done here.
	 * 
	 * @param args files to be observe, given as command line arguments
	 */
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
					// information is divided with spaces here ...
					parrotSc.useDelimiter(" ");
					// fill ArrayList with data
					while (parrotSc.hasNextInt()) {
						parrot.add(parrotSc.nextInt());
					}

					// debugging information
					System.out.printf("parrotStr as array:\t%s\n", parrot);

					System.out.println();

					// process pirate line
					// get second line from sourceFile
					String pirateStr = sc.nextLine();
					System.out.printf("pirateStr:\t%s\n", pirateStr);
					// create ArrayList
					List<Integer> pirate = new ArrayList<>();
					// create scanner on string
					Scanner pirateSc = new Scanner(pirateStr);
					// information is divided with spaces here ...
					pirateSc.useDelimiter(" ");
					// fill ArrayList with data
					while (pirateSc.hasNext()) {
						String tmp = pirateSc.next();
						// checks if current token is a question mark
						if (Pattern.matches("[0-9]", tmp)) {
							pirate.add(Integer.parseInt(tmp));
						} else { // add a zero to the ArrayList, if it is a question mark
							pirate.add(0);
						}
					}

					// debugging information
					System.out.printf("pirateStr as array:\t%s\n", pirate);

					System.out.println();

					// adjust arrays
					// check every value in the pirate list
					for (int i = 0; i < pirate.size(); i++) {
						// compare to every value in the parrot list
						for (int j = 0; j < pirate.size(); j++) {
							// if the current pirate token was a zero, we don't have to compare
							if (pirate.get(j) != 0) {
								// if the lists don't match, make the last parrot value the first and start a
								// new comparison from the first value
								if (pirate.get(j) != parrot.get(j)) {
									parrot.add(0, parrot.get(parrot.size() - 1));
									parrot.remove(parrot.size() - 1);
									break;
								}
							}
						}
					}

					// debugging information
					System.out.println(pirate);

					// still debugging information
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

					// this is the actual solution
					System.out.printf("\nDie korrekte Reihenfolge beginnend im Norden lautet:\t(%s)\n%s\n",
							sourceFile.getName(), parrot);

					System.out.println();
					System.out.println();

					// close scanners
					parrotSc.close();
					pirateSc.close();
					sc.close();
				} catch (FileNotFoundException e) { // needed if the given file path is invalid
					System.out.println("WARNING!!! Given file doesn't exists or filepath is invalid!");
				}
			}
		}
	}
}
