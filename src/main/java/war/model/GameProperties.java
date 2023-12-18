package war.model;

public class GameProperties {

	private int rounds;
	private int numberOfPlayers;
	private int numberOfCardsPerPlayer;

	public GameProperties(int rounds, int numberOfPlayers, int numberOfCardsPerPlayer) {
		this.rounds = rounds;
		this.numberOfPlayers = numberOfPlayers;
		this.numberOfCardsPerPlayer = numberOfCardsPerPlayer;
	}

	public int getRounds() {
		return rounds;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public int getNumberOfCardsPerPlayer() {
		return numberOfCardsPerPlayer;
	}
}
