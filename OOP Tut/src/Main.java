import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Objects;

public class Main {

	public static void printMenu() {
		System.out.print(
				"\n" + "1. Set field\n" + "2. Clear field\n" + "3. Print game\n" + "4. Exit\n\n" + "Select an action [1-4]: ");
	}

	public static int parseInput() {
		Scanner in = new Scanner(System.in);
		try {
			return in.nextInt();
		} catch (InputMismatchException missE) {
			in.next(); // discard invalid input
			return -1;
		}
	}

	public static int requestInt(String msg, int min, int max) {
		Objects.requireNonNull(msg);

		while (true) {
			System.out.print("Please provide " + msg + ": ");
			int input = parseInput();
			if (input >= min && input <= max)
				return input;
			else {
				System.out.println("Invalid input. Must be between " + min + " and " + max);
			}
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) throw new IllegalArgumentException("Expecting path to game file as first argument.");
		GameGrid game = new GameGrid(args[0]);
		while (true) {
			printMenu();
			int input = parseInput();
			switch (input) {
			case 1:
				int x = requestInt("x coordinate", 1, 9)-1;
				int y = requestInt("y coordinate", 1, 9)-1; 
				int newVal = requestInt("new value:", 1, 9); 
				game.setField(x, y, newVal);
				break;
			case 2:
				int xa = requestInt("x coordinate", 1, 9)-1;
				int ya = requestInt("y coordinate", 1, 9)-1; 
				game.clearField(xa, ya);
				break;
			case 3:
				game.printGrid();
				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid number");
				break;
			}
		}
	}

	
}
