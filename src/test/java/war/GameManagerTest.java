package war;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import war.model.Player;

public class GameManagerTest {

	@Test
	public void testGameEndsAtfinalRound() {
		Map<Integer, Player> players = new HashMap<>();

		Stack<Integer> stack = new Stack<>();
		stack.addAll(List.of(1, 2, 3, 4));
		Player p1 = new Player(1, "1", stack);
		players.put(1, p1);

		stack = new Stack<>();
		stack.addAll(List.of(5, 6, 7, 8));
		Player p2 = new Player(2, "2", stack);
		players.put(2, p2);

		Set<Player> winners = new GameManager().play(3, players);

		Assert.assertEquals(1, winners.size());
		Assert.assertEquals(2, winners.iterator().next().getId());
		Assert.assertEquals(1, p1.getNumberOfCards());
		Assert.assertEquals(7, p2.getNumberOfCards());
	}

	@Test
	public void testGameEndsPriorToFinalRound() {
		Map<Integer, Player> players = new HashMap<>();

		Stack<Integer> stack = new Stack<>();
		stack.addAll(List.of(1, 2, 3, 4));
		Player p1 = new Player(1, "1", stack);
		players.put(1, p1);

		stack = new Stack<>();
		stack.addAll(List.of(5, 6, 7, 8));
		Player p2 = new Player(2, "2", stack);
		players.put(2, p2);

		Set<Player> winners = new GameManager().play(5, players);

		Assert.assertEquals(1, winners.size());
		Assert.assertEquals(2, winners.iterator().next().getId());
		Assert.assertEquals(0, p1.getNumberOfCards());
		Assert.assertEquals(8, p2.getNumberOfCards());
	}

	@Test
	public void testTieGame() {
		Map<Integer, Player> players = new HashMap<>();

		Stack<Integer> stack = new Stack<>();
		stack.addAll(List.of(1, 4));
		Player p1 = new Player(1, "1", stack);
		players.put(1, p1);

		stack = new Stack<>();
		stack.addAll(List.of(2, 3));
		Player p2 = new Player(2, "2", stack);
		players.put(2, p2);

		Set<Player> winners = new GameManager().play(2, players);

		Assert.assertEquals(2, winners.size());
		Assert.assertEquals(2, p1.getNumberOfCards());
		Assert.assertEquals(2, p2.getNumberOfCards());
	}

	@Test
	public void testMultipleWinnersButNotEveryoneIsAWinner() {
		Map<Integer, Player> players = new HashMap<>();

		Stack<Integer> stack = new Stack<>();
		stack.addAll(List.of(1, 4, 5, 8));
		Player p1 = new Player(1, "1", stack);
		players.put(1, p1);

		stack = new Stack<>();
		stack.addAll(List.of(2, 3, 6, 7));
		Player p2 = new Player(2, "2", stack);
		players.put(2, p2);

		stack = new Stack<>();
		stack.addAll(List.of(9, 12, 13, 16));
		Player p3 = new Player(3, "3", stack);
		players.put(3, p3);

		stack = new Stack<>();
		stack.addAll(List.of(10, 11, 14, 15));
		Player p4 = new Player(4, "4", stack);
		players.put(4, p4);

		Set<Player> winners = new GameManager().play(2, players);

		Assert.assertEquals(2, winners.size());
		Iterator<Player> iterator = winners.iterator();

		int id = 3;
		while (iterator.hasNext()) {
			Player player = iterator.next();
			// expected ids are 3 & 4
			Assert.assertEquals(id, player.getId());
			id = 4;
		}

		Assert.assertEquals(2, p1.getNumberOfCards());
		Assert.assertEquals(2, p2.getNumberOfCards());
		Assert.assertEquals(6, p3.getNumberOfCards());
		Assert.assertEquals(6, p4.getNumberOfCards());
	}
}
