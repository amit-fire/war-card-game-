GAME DESCRIPTION:

Performance can be improved. For the first iteration, I was focused on
reaching a working game quickly.

cards (unique numbers from 1 to number_of_player 
number_of_cards_per_player, up to Integer.MAX_VALUE) are shuffled prior to
being dealt.

Each round a player draws a card. The player with the highest value card,
wins the round.

The round winner collects the cards from the round and places them in a
separate pile ("used") from the pile of cards from which cards are drawn
("unused").

Once the last card is drawn from the "unused" pile, there are 2 outcomes:

1. The player has a "used" pile, in which case, this pile now becomes the
"unused" pile.

2. The player does not have a "used" pile, in which case, the player is out
of the game.

If a single player is left, the game is immediately stopped. That player is
the winner. Otherwise, continue playing.

If there round limit has been reached, determining winner(s) could be handled
in various way:

1. No winner - declare tie.

2. Count which player(s) have the most cards.

3. Count which player(s) have the largest value of cards.

4. A combination of 2 and 3: determine one of them as the first tie breaker
and if there's still a tie, use the other one as a tie breaker.

5. Other options that I haven't thought of.

I went with approach 2.

Example:

Player 1 has 3 cards

Player 2 has 1 card

Player 3 has 8 cards

Player 4 has 8 cards

Players 3 and 4 are the winners.

Ties: there are no ties, at the round level.

To have a tie, cards cannot be unique.

Suppose there are 2 players, each starts with 4 cards.

Suppose that the order in which the cards are set is 1,2,3,4 (regardless of
how many shuffles are performed). This use case will be resolved by the round
limit.

Another scenario:

Player 1 draws 1

Player 2 draws 2

Player 3 draws 3

Player 4 draws 3

Suppose Player 3 and 4 have no more card. What should happen next?

Void the round?

Due to these complications, ties are out of scope for this iteration.

Data Structure: A stack has been used to store the cards. It mirrors what
happens in real life where the cards are stacked when being dealt and the
card on top is the one that is drawn.

2 stacks per player are used, one for the "unused" pile and one for the
"used" pile.

Once the "unused" pile is empty, if there are cards in the "used" pile, cards
will be drawn from it, and won cards will be placed in the "unused" pile.

The stacks roles is reversed. A flag controls the state. This is to avoid
moving cards from the "used" stack to the "unused" stack once the "unused"
stack becomes empty.

There's no shuffle during the game. Adding a shuffle, wouldn't change
anything.

Testing: see GameTest class

While not necessary for the game to work, had a need to use real
representation of a card, the following files would be used: Card (Class),
SpecialCard (Enum, used for non-number cards to map between the card's name
and its numerical value), Suit (Enum). DeckGenerator would create the deck.