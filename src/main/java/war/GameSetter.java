package war;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import war.model.Player;

public class GameSetter {

	Map<Integer, Player> setup(int numberOfPlayers, int numberOfCardsPerPlayer) {
		List<Integer> cards = generateCards(numberOfPlayers * numberOfCardsPerPlayer);
		Collections.shuffle(cards);
		Map<Integer, Player> players = generatePlayers(cards, numberOfPlayers);
		return players;
	}

	private List<Integer> generateCards(int end) {
		return IntStream.rangeClosed(1, end).boxed().collect(Collectors.toList());
	}

	private Map<Integer, Player> generatePlayers(List<Integer> cards, int numberOfPlayers) {
		Map<Integer, Player> players = new HashMap<>();

		int start = 0, end = cards.size() / numberOfPlayers;

		for (int i = 0; i < numberOfPlayers; i++) {
			Player player = generatePlayer(i + 1, String.valueOf(i + 1), cards.subList(start, start + end));
			players.put(player.getId(), player);
			start += end;
		}

		return players;
	}

	private Player generatePlayer(int id, String name, List<Integer> cards) {
		Stack<Integer> stack = new Stack<>();
		stack.addAll(cards);
		return new Player(id, name, stack);
	}

}
