package war;

import java.util.Scanner;

import war.exception.GameSetupException;
import war.exception.InvalidInputException;
import war.exception.InvalidNumberOfPlayersException;
import war.model.GameProperties;

public class InputHandler {

	public GameProperties handle() throws Exception {

		// TODO: externalize
		System.out.println(
				"The game requires 3 parameters.\n1. number of rounds.\n2. number of players.\n3. number of cards per player.\nMaximum product of the two numbers cannot exceed "
						+ Integer.MAX_VALUE + ".");

		Scanner scanner = new Scanner(System.in);

		try {
			// TODO: externalize
			System.out.println("Enter the number of rounds");
			int rounds = getValue(scanner, 0);
			// TODO: externalize
			System.out.println("Enter the number of players");
			int numberOfPlayers = getPlayers(scanner, 1);
			// TODO: externalize
			System.out.println("Enter the number of cards per player");
			int numberOfCardsPerPlayer = getValue(scanner, 0);

			validateLimit(numberOfPlayers, numberOfCardsPerPlayer);

			return new GameProperties(rounds, numberOfPlayers, numberOfCardsPerPlayer);
		} finally {
			scanner.close();
		}
	}

	private int getValue(Scanner scanner, int limit) throws InvalidInputException {
		if (scanner.hasNextInt()) {
			int value = scanner.nextInt();
			if (value > limit) {
				return value;
			}
		}

		throw new InvalidInputException();
	}

	private int getPlayers(Scanner scanner, int limit) throws InvalidNumberOfPlayersException {
		try {
			return getValue(scanner, limit);
		} catch (InvalidInputException e) {
			throw new InvalidNumberOfPlayersException();
		}
	}

	private void validateLimit(int numberOfPlayers, int numberOfCardsPerPlayer) throws GameSetupException {
		try {
			Math.multiplyExact(numberOfPlayers, numberOfCardsPerPlayer);
		} catch (ArithmeticException e) {
			throw new GameSetupException(numberOfPlayers, numberOfCardsPerPlayer);
		}
	}

}
