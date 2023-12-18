package war;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

import war.model.Player;

public class GameManager {

	public Set<Player> play(int rounds, Map<Integer, Player> players) {
		for (int i = 0; i < rounds; i++) {
			if (players.size() == 1) {
				break;
			}

			playRound(players);
		}

		return determineWinners(players);
	}

	private void playRound(Map<Integer, Player> players) {
		// Map<PlayerId, Drawn Card>
		Map<Integer, Integer> roundDetails = new HashMap<>();
		Set<Integer> playerIds = new HashSet<>();

		players.values().forEach(player -> {
			Stack<Integer> cards = player.getCards();
			roundDetails.put(player.getId(), cards.pop());
			if (!player.hasCards()) {
				playerIds.add(player.getId());
			}
		});

		// cards are unique, no ties.
		Integer playerIdWithMaxValue = Collections.max(roundDetails.entrySet(), Map.Entry.comparingByValue()).getKey();
		/*
		 * winner places in its discard pile, drawn cards from the other players, plus
		 * its own card that was drawn
		 */
		players.get(playerIdWithMaxValue).addCards(roundDetails.values().stream().collect(Collectors.toSet()));
		removePlayersWithoutCards(playerIds, playerIdWithMaxValue, players);
	}

	private void removePlayersWithoutCards(Set<Integer> playerIds, int roundWinnerId, Map<Integer, Player> players) {
		/*
		 * the eventual winner, might have drawn its last card. since that player won,
		 * it can keep playing
		 */
		if (playerIds.contains(roundWinnerId)) {
			playerIds.remove(roundWinnerId);
		}

		playerIds.forEach(id -> {
			players.remove(id);
		});
	}

	private Set<Player> determineWinners(Map<Integer, Player> players) {
		int max = 0;
		//Set<Player> winners = new HashSet<>();
		SortedSet<Player> winners = new TreeSet<>();
		for (Map.Entry<Integer, Player> player : players.entrySet()) {
			int numberOfCards = player.getValue().getNumberOfCards();
			if (numberOfCards > max) {
				winners.clear();
				winners.add(player.getValue());
				max = numberOfCards;
			} else if (numberOfCards == max) {
				winners.add(player.getValue());
			}
		}
		return winners;
	}

}
