package war.model;

import java.util.Set;
import java.util.Stack;

/* 
 * the reason to implement Comparable is that in one of the tests,
 * a set of players is provided and it's convenient to iterate based on the player's id.
 * the set could have been ordered in the test, but seems like it might be useful in
 * other cases to be able to compare players.
 */
public class Player implements Comparable<Player> {

	private int id;
	private String name;
	private Stack<Integer> unused;
	private Stack<Integer> used = new Stack<>();
	private boolean addToUsed = true;

	public Player(int id, String name, Stack<Integer> cards) {
		this.id = id;
		this.name = name;
		this.unused = cards;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Stack<Integer> getCards() {
		if (unused.isEmpty()) {
			addToUsed = false;
			return used;
		}
		addToUsed = true;
		return unused;
	}

	public void addCards(Set<Integer> cards) {
		if (addToUsed) {
			used.addAll(cards);
		} else {
			unused.addAll(cards);
		}
	}

	// getNumberOfCards == 0 is equivalent, but i like this style
	public boolean hasCards() {
		return !unused.isEmpty() || !used.isEmpty();
	}

	public int getNumberOfCards() {
		return unused.size() + used.size();
	}

	@Override
	public int compareTo(Player o) {
		return Integer.compare(id, o.getId());
	}

}
