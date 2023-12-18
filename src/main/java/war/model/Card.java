package war.model;

public class Card {

	private int value;
	private String name;
	private Suit suit;

	public Card(int value, String name, Suit suit) {
		this.value = value;
		this.name = name;
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public Suit getSuit() {
		return suit;
	}

}
