
package Asg2;

/**
 * Name: Finehout, Isaac CMIS 242/6384 Date: 1/29/2023
 *
 * OrderSystem allows the user to create a list of snacks.
 * The output follows the Asg2 instructions precisely,
 * with the following three exceptions:
 *
 * 1. the toString methods of FruitySnack and SaltySnack are different
 * 2. When the user exits the program, it prints off all the snacks they made
 * 3. Error handling is implemented
 *
 * @author fineh
 */
import java.util.ArrayList;
import java.util.Scanner;

public class OrderSystem {

	/**
	 * Declare list of snacks
	 */
	private static ArrayList<Snack> snacks;

	/**
	 * Constructor for handler
	 *
	 * @param snacks a list of snacks
	 */
	private OrderSystem() {
		snacks = new ArrayList<Snack>();
	}

	/**
	 * Print the snack menu
	 */
	private void printMenu() {

		// @formatter:off

		System.out.println("MENU\n"
						 + "1: Order a Snack\n"
						 + "2: Exit program\n");

		// @formatter:on

	}

	/**
	 * Process the user's menu choice. stdin is an argument from main
	 *
	 * @param c the user's choice
	 */
	private void processChoice(int c, Scanner stdin) {
		switch (c) {
		case (1):
			userOrdersSnack(stdin);
			break;
		case (2):
			printSnacks();
			break;
		default:
			System.err.println(c + " - invalid input. Try again");
			break;

		}
	}

	/**
	 * userOrdersSnack uses a series of nested if statements. If the user enters
	 * incorrect information at any point, the nested if statements break out and
	 * the user has to start over at the main menu.
	 *
	 * @param userInput            see if user input is valid
	 * @param snackChoice          determine correct subclass constructor
	 * @param size                 used in subclass constructor
	 * @param hasSpecialSnack      determine if snack is citrusy or nutty
	 * @param userEnteredValidInfo determine if a new snack should be made
	 */
	private void userOrdersSnack(Scanner stdin) {

		String userInput;
		int snackChoice = -1;
		int size = -1;
		boolean hasSpecialSnack = false;

		boolean userEnteredValidInfo = true;

		// Get snackChoice
		System.out.println("Do you want Fruit Snack (1) or Salty Snack (2): ");
		userInput = stdin.nextLine();
		if (userInput.equals("1") || userInput.equals("2")) { // Else return error
			snackChoice = Integer.parseInt(userInput);

			// Get size
			System.out.println("What size do you want: S, M, or L: ");
			userInput = stdin.nextLine();

			//@formatter:off

			if (userInput.equalsIgnoreCase("s")
					|| userInput.equalsIgnoreCase("m")
					|| userInput.equalsIgnoreCase("l")) { // Else return error
				//A ternary expression that converts userInput size into an int
				size = userInput.equalsIgnoreCase("s") ? Snack.getSMALL_SIZE() :
					userInput.equalsIgnoreCase("m") ? Snack.getMEDIUM_SIZE() :
						Snack.getLARGE_SIZE();

				//@formatter:on

				// Get specialSnack
				// A ternary expression that chooses a specialSnack string depending snackChoice
				String askForSpecial = (snackChoice == 1) ? "Do you want citrus fruit included?"
						: "Do you want nut snack included?";
				System.out.println(askForSpecial + " true/false: ");
				userInput = stdin.nextLine();
				if (userInput.equalsIgnoreCase("true") || userInput.equalsIgnoreCase("false")) {
					// A ternary expression that converts userInput into a boolean
					hasSpecialSnack = userInput.equalsIgnoreCase("true") ? true : false;

					// Error message for incorrect specialSnack entry
				} else {
					System.err.println(userInput + " - invalid special snack choice. Try again.");
					userEnteredValidInfo = false;
				}
				// Error message for incorrect size entry
			} else {
				System.err.println(userInput + " - invalid size. Try again.");
				userEnteredValidInfo = false;
			}

			// Error message for incorrect snackChoice entry
		} else {
			System.err.println(userInput + " - invalid snack choice. Try again.");
			userEnteredValidInfo = false;
		}

		if (userEnteredValidInfo) {

			// Demonstrates how to use array list

			if (snackChoice == 1) {
				snacks.add(new FruitySnack(size, hasSpecialSnack));
			} else {
				snacks.add(new SaltySnack(size, hasSpecialSnack));
			}
			System.out.println("You have chosen snack type = " + snacks.get(snacks.size() - 1));
		}
	}

	/**
	 * Used at the end of the program. Shows that array list successfully compiled a
	 * list of snacks.
	 */
	private void printSnacks() {

		if (snacks.size() == 0) {
			System.out.println("Thanks for using the snacks program!");

		} else {
			System.out.println("Your snacks: ");
			for (Snack snack : snacks) {
				System.out.println(snack);
			}
			System.out.println("Enjoy!");
		}
	}

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		OrderSystem handler = new OrderSystem();
		int choice;

		do {
			handler.printMenu();
			System.out.println("Enter your selection:");
			choice = stdin.nextInt();
			stdin.nextLine(); // Prevents scanner overflow runtime bug
			handler.processChoice(choice, stdin);
		} while (choice != 2);
		stdin.close();
	}

}
