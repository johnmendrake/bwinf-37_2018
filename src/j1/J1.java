package j1;

public class J1 {

	public static void main(String[] args) {
		// repeat procedure for every value of the dice
		for (int stepDistance = 1; stepDistance <= 6; stepDistance++) {
			int field = 0; // pointer to the current field
			int numTry = 0; // counts how often the dice has been rolled

			// prepare prevention of endless run
			boolean[] wasOnField = new boolean[101];
			for (int i = 0; i < wasOnField.length; i++) {
				wasOnField[i] = false;
			}

			// actual try
			while (field != 100) {
				// go step
				field += stepDistance;
				numTry++;

				// if greater than hundred, go backwards
				if (field > 100) {
					int tmp = field - 100;
					field = 100 - tmp;
				}

				// endless loop prevention / detection
				if (wasOnField[field] == true) {
					// message on endless loop
					System.out.printf("Finishing the game with step distance %d is impossible!\n", stepDistance);
					break;
				} else {
					wasOnField[field] = true;
				}

				field = handleLadders(field);

				// message on success
				if (field == 100) {
					System.out.printf("Game finished with stepDistance %d!\tNeeded to roll the dice %d times!\n",
							stepDistance, numTry);
				}
			}
		}
	}

	public static int handleLadders(int pField) {
		switch (pField) {
		case 6:
			return 27;
		case 14:
			return 19;
		case 19:
			return 14;
		case 21:
			return 53;
		case 27:
			return 6;
		case 31:
			return 42;
		case 33:
			return 38;
		case 38:
			return 33;
		case 42:
			return 31;
		case 46:
			return 62;
		case 51:
			return 59;
		case 53:
			return 21;
		case 57:
			return 97;
		case 59:
			return 51;
		case 62:
			return 46;
		case 65:
			return 85;
		case 68:
			return 80;
		case 70:
			return 76;
		case 76:
			return 70;
		case 80:
			return 68;
		case 85:
			return 65;
		case 92:
			return 98;
		case 96:
			return 57;
		case 98:
			return 92;
		default:
			return pField;
		}
	}

}
