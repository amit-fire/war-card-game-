package war;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import war.model.GameProperties;
import war.model.Player;

public class War {

	public static void main(String[] args) {
		try {
			play(new InputHandler().handle());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void play(GameProperties properties) {
		/*
		 * Players are passed to Game to enable testing. Otherwise, due to the random
		 * nature of the game, it would be impossible.
		 */
		Map<Integer, Player> players = new GameSetter().setup(properties.getNumberOfPlayers(),
				properties.getNumberOfCardsPerPlayer());
		Set<Player> winners = new GameManager().play(properties.getRounds(), players);
		System.out.println("Winner(s): "
				+ String.join(", ", winners.stream().map(e -> String.valueOf(e.getId())).collect(Collectors.toList())));
	}

}
