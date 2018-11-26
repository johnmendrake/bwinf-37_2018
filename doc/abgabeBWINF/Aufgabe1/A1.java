import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class A1 {
	// prepare counter for requests
	private static int sentFollowingRequests;

	/**
	 * The main method of the program. All essential work is done here.
	 * 
	 * @param args database files given as command line arguments
	 */
	public static void main(String[] args) {
		// when there are no parameters, the program hasn't anything to do
		if (args.length == 0) {
			System.out.println("Please run program with database file as parameter.");
		} else {
			// program can be run with multiple database files as parameters
			for (int i = 0; i < args.length; i++) {
				// no requests to pseudo network server yet
				sentFollowingRequests = 0;
				try { // needed in case a given file path is invalid
					System.out.println("NOTE: The following lines are dedicated to debugging.");

					// following line of code is for debugging and testing to replace command line
					// argument
					// NOTE: To make use of it, you need the right structure of directories,
					// otherwise it may not work or has to be adapted!
//					File sourceFile = new File("src/a1/superstar4.txt");

					// set source file to the parameter, currently being processed
					File sourceFile = new File(args[i]);

					// get the first line containing the members from sourceFile
					Scanner sc = new Scanner(sourceFile);
					sc.useDelimiter("\n");
					String namesStr = sc.next();

					// split string of network members and distribute them to an ArrayList
					List<String> names = new ArrayList<>();
					Scanner nsc = new Scanner(namesStr);
					nsc.useDelimiter(" ");
					while (nsc.hasNext()) {
						names.add(nsc.next());
					}

					// debugging information
					System.out.println("Recognized network members:");
					System.out.println(names);
					System.out.printf("The network has %s members.\n", names.size());

					System.out.println();

					// process rest of the sourceFile and distribute data to two ArrayLists

					// Format:
					// users.get(x) follows targets.get(x)
					List<String> users = new ArrayList<>();
					List<String> targets = new ArrayList<>();
					while (sc.hasNext()) {
						Scanner tmpSc = new Scanner(sc.next());
						tmpSc.useDelimiter(" ");
						users.add(tmpSc.next());
						targets.add(tmpSc.next());

						tmpSc.close();
					}

					// debugging information
					System.out.println("recognized data about users following other users:");
					System.out.printf("entrycount: %s\tusers:\t\t%s\n", users.size(), users);
					System.out.printf("entrycount: %s\ttargets:\t%s\n", targets.size(), targets);

					System.out.println();

					// create a new ArrayList, containing all potential superstars
					List<String> potSuperStar = new ArrayList<>(names);
					// for now, all members are potential superstars

					// check for every user, if he follows one of the other users to find the
					// superstar in the group if he exists
					for (int userNum = 0; userNum < names.size(); userNum++) {
						for (int targetNum = 0; targetNum < names.size(); targetNum++) {
							// if there is only one potential superstar left, we can directly check this one
							if (potSuperStar.size() == 1)
								break;
							// to keep costs and requests low, check if requested target is a potential
							// superstar before sending a request
							if (targetNum != userNum && potSuperStar.contains(names.get(targetNum))) {
								// this is the actual request, whether user X follows user Y
								if (checkFollowingStatus(names.get(userNum), names.get(targetNum), users, targets)) {
									// if the request returns true, the user X who follows user Y cannot be a
									// superstar and can therefore be removed from potential superstar list to
									// reduce needed requests
									potSuperStar.remove(names.get(userNum));
								} else {
									// if the request returns true, user Y cannot be followed by all users and
									// therefore cannot be a superstar, so he can be removed from the potential
									// superstar list
									potSuperStar.remove(names.get(targetNum));
								}
							}
						}
					}

					// make sure that superstar doesn't follow ANY member of the network
					if (!potSuperStar.isEmpty()) {
						for (int targetNum = 0; targetNum < names.size(); targetNum++) {
							if (!Objects.equals(names.get(targetNum), potSuperStar.get(0))) {
								if (checkFollowingStatus(potSuperStar.get(0), names.get(targetNum), users, targets)) {
									potSuperStar.remove(0);
									break;
								}
							}
						}
					}

					System.out.println();

					// when there is a name left in the potential superstar list, it is the one we
					// have been searching for
					if (!potSuperStar.isEmpty()) {
						System.out.printf("The superstar is %s.\t\t(%s)\n", potSuperStar.get(0), sourceFile.getName());
					} else { // otherwise there is no superstar in the network
						System.out.printf("No superstar was found.\t\t(%s)\n", sourceFile.getName());
					}
					System.out.printf("Needed %s requests.\n", sentFollowingRequests);
					System.out.println();
					System.out.println();

					// close resources
					sc.close();
					nsc.close();
				} catch (FileNotFoundException e) { // needed if the given file path is invalid
					System.out.println("WARNING!!! Given file doesn't exists or filepath is invalid!");
				}
			}
		}
	}

	/**
	 * This method is used to sent the request to the pseudo network server. It
	 * returns true if user follows target
	 * 
	 * @param user    the name of the user you want to observe
	 * @param target  the name of the user you want to know whether he's followed by
	 *                the user from the first parameter
	 * @param users   the database file, extracted in main, containing the list of
	 *                users, who follow other users
	 * @param targets the database file, extracted in main, containing the list of
	 *                users, who are followed by other users
	 * @return
	 */
	public static boolean checkFollowingStatus(String user, String target, List<String> users, List<String> targets) {
		// of course the has to be recorded as a request. Request cost money!!!
		sentFollowingRequests++;

		// print out information on the request to be sent
		System.out.printf("%s\t\tfollows\t\t%s?\t\t", user, target);

		// rush through the first list on search of the user
		for (int i = 0; i < users.size(); i++) {
			// if the user has been found in the first list, check if the target is in the
			// second list. at the same index as the user appears in the first list
			if (Objects.equals(users.get(i), user) && Objects.equals(targets.get(i), target)) {
				// the request returns true
				System.out.println("true");
				return true;
			}
		}
		// the request returns false
		System.out.println("false");
		return false;
	}
}