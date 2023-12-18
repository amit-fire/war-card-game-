package war;

import java.util.ArrayList;
import java.util.List;

import war.model.Card;
import war.model.SpecialCard;
import war.model.Suit;

public class DeckGenerator {

	private int NUMBER_OF_CARDS_PER_SUIT = 13;

	public List<Card> generate() {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_CARDS_PER_SUIT; i++) {
			Suit[] suits = Suit.values();
			for (int j = 0; j < suits.length; j++) {
				cards.add(new Card(i + 2, getCardName(i + 2), suits[j]));
			}
		}
		return cards;
	}

	private String getCardName(int i) {
		if (i <= 10) {
			return Integer.toString(i);
		} else {
			return getSpecialCardName(i);
		}
	}

	private String getSpecialCardName(int i) {
		// Jokers are not part of War
		switch (i) {
		case 11:
			return SpecialCard.JACK.toString();
		case 12:
			return SpecialCard.QUEEN.toString();
		case 13:
			return SpecialCard.KING.toString();
		case 14:
			return SpecialCard.ACE.toString();
		default:
			return "";
		}
	}

}
