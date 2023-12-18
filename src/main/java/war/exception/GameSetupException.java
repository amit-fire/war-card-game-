package war.exception;

public class GameSetupException extends Exception {

	private static final long serialVersionUID = 1L;

	private int numberOfPlayers, numberOfCardsPerPlayer;

	public GameSetupException(int numberOfPlayers, int numberOfCardsPerPlayer) {
		this.numberOfPlayers = numberOfPlayers;
		this.numberOfCardsPerPlayer = numberOfCardsPerPlayer;
	}

	public String getMessage() {
		// TODO: externalize
		return "The product of NUMBER_OF_PLAYERS (" + numberOfPlayers + ") and NUMBER_OF_CARDS_PER_PLAYER ("
				+ numberOfCardsPerPlayer + ") exceeds the limit (" + Integer.MAX_VALUE + ").";
	}

}
