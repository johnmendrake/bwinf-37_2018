/**
 * 
 */
/**
 * @author Dennis Kuehn
 * 2018-11-19
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Twist {
	// Text wird hier eingegeben
	static String text = "hallo, wie geht es dir ?";
	static String verschluesselterText = "";

	public static void main(String[] args) {
		informieren();
		entscheiden();
	}

	public static void entscheiden() {

		Scanner erkenner = new Scanner(System.in);
		int erkanntes = erkenner.nextInt();
		System.out.println(erkanntes);
		if (erkanntes == 1) {
			verschluesseln();
		} else {
			entschluesseln();
		}
	}
    //Klaehrt den Benutzer auf, was und wie das Programm macht.
	public static void informieren() {
		System.out.println("Es gibt zwei Moeglichkeiten zu twisten. \n");
		System.out.println("Die erste Moeglichkeit ist einen normalen Text zu verschluesseln.");
		System.out.println(
				"Die Zweite ist die Moeglichkeit einen bereits mit diesem Programm verschluesselten Text wieder zurueckzugewinnen. \n");
		System.out.println(
				"Um Option 1 zu waehlen tippen sie die Zahl 1 auf ihrer Tastatur. Fuer Option 2 die Zahl 2 druecken");
		System.out.println("Anschliessend Auswahl mit Enter bestaetigen \n");
		//Setzt einen Strich zwischen der Einleitung und der Textverabreitung.
		for (int zaehler = 0; zaehler < 109; zaehler++) {
			System.out.print("_");
		}
		System.out.print("_ \n");
	}
	//Diese Methode gibt den Text wieder, sowohl vorher, als auch nacher.
	public static void verschluesseln() {
		System.out.println("Dein Text unverarbeitet: \n" + text + "\n");
		System.out.println("Dein Text verarbeitet: ");
		//Das woerter-array hat in den einzelnen Indexen die Woerter des Textes.
		String[] woerter = text.split(" ");
		//Die einzelnen Woerter werden mit der mischen()-Methode bearbeitet. Das wird solange wiederholt, wie der Text lange ist.
		for (String current : woerter) {
			StringBuilder builder = new StringBuilder();
			//Der String "wort" wird einer anderen Methode uebergeben, um da Wort fuer Wort innerlich zu vermischen.
			String wort = "" + builder.append(current).append(" ");
			mischen(wort);
		}
	}
	//Fast eine Kopie von Verschluesseln
    //Eigentlich nur optional noetig. Ich habe die Methode trotzdem eingebaut, da man sprachlich einen kleinen Unterschied hat.
	public static void entschluesseln() {
		System.out.println("Dein Text verarbeitet: \n" + text + "\n");
		System.out.println("Dein Text unverarbeitet: ");
		String[] array = text.split(" ");
		for (String current : array) {
			StringBuilder builder = new StringBuilder();
			String wort = "" + builder.append(current).append(" ");
			mischen(wort);
		}

	}
	//Bestimmte Stellen werden mit anderen mit Hilfe einer Hilfsvariable getauscht. Kann auch zum entmischen verwendet werden.
	public static void mischen(String s) {
		char[] stellen = s.toCharArray();
		for (int x = 1; x < stellen.length -3; x++) {
			char hilf = stellen[x];
			if (x < stellen.length) {
				stellen[x] = stellen[x + 1];
				stellen[x + 1] = hilf;
			} else {
				stellen[x] = stellen[x - 1];
				stellen[x - 1] = hilf;
			}
		}
		//Ausgabe des verarbeiteten Textes
		for (int o = 0; o < stellen.length; o++) {
			System.out.print(stellen[o]);
		}
	}
}
