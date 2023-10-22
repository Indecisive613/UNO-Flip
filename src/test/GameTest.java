package test;

import main.Card;
import main.Game;
import main.GameRunner;
import main.Player;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test Game
 *
 * @author Jackie Smolkin-Lerner, Jake Siushansian
 */
public class GameTest {

    Game game;
    Stack<Card> deck;
    Player player1, player2,  player3, player4;

    @Before
    public void setUp() {

        ArrayList<Card> hand1 = new ArrayList<Card>(); // create empty hand for player 1
        ArrayList<Card> hand2 = new ArrayList<Card>(); // create empty hand for player 2
        ArrayList<Card> hand3 = new ArrayList<Card>(); // create empty hand for player 3
        ArrayList<Card> hand4 = new ArrayList<Card>(); // empty create hand for player 4
        player1 = new Player("Homer", hand1); // create player 1
        player2 = new Player("Marge", hand2); // create player 2
        player3 = new Player("Bart", hand3); // create player 3
        player4 = new Player("Lisa", hand4); // create player 4

        deck = GameRunner.createDeck(); // create the deck
        game = new Game(deck); // create the game with the players and the deck

        // add the players to the game
        game.addPlayer(player1);
        game.addPlayer(player3);
        game.addPlayer(player2);
        game.addPlayer(player4);
    }

    @Test
    public void testStartGame() {

        setUp();

        assertEquals(0, player1.getHand().size());
        assertEquals(0, player2.getHand().size());
        assertEquals(0, player3.getHand().size());
        assertEquals(0, player4.getHand().size());
        assertEquals(104, deck.size());

        game.shuffleDeck(); // shuffle the deck
        game.dealCards(); // deal the cards

        assertEquals(7, player1.getHand().size());
        assertEquals(7, player2.getHand().size());
        assertEquals(7, player3.getHand().size());
        assertEquals(7, player4.getHand().size());

        // All 4 players are dealt 7 cards + 1 card placed on the table
        assertEquals(104 - 7*4 - 1, deck.size());

        // there should be a top card on the table
        assertNotEquals(null, game.getTopCard());
    }

    @Test
    public void testDrawingCards() {

        setUp();

        game.shuffleDeck(); // shuffle the deck
        game.dealCards(); // deal the cards

        assertTrue(game.isRunning()); // the game should be running

        assertEquals(player1, game.getCurrentPlayer()); // the current player should be player1

        // player 1 draws a card from the deck
        game.drawCard(game.getCurrentPlayer());
        assertEquals(8, player1.getHand().size());
        assertEquals(104 - 7*4 - 1 - 1, deck.size());

        assertTrue(game.isRunning()); // the game should be running
        game.advanceTurn();

        assertEquals(player2, game.getCurrentPlayer()); // the current player should be player2
        assertEquals(8, player2.getHand().size());
        assertEquals(104 - 7*4 - 1 - 1 - 1, deck.size());

        assertTrue(game.isRunning()); // the game should be running
        game.advanceTurn();

        assertEquals(player3, game.getCurrentPlayer()); // the current player should be player3
        assertEquals(8, player3.getHand().size());
        assertEquals(104 - 7*4 - 1 - 1 - 1 - 1, deck.size());

        assertTrue(game.isRunning()); // the game should be running
        game.advanceTurn();

        assertEquals(player4, game.getCurrentPlayer()); // the current player should be player4
        assertEquals(8, player4.getHand().size());
        assertEquals(104 - 7*4 - 1 - 1 - 1 - 1 - 1, deck.size());

        assertNotEquals(null, game.getTopCard());
    }

    @Test
    public void testPlayingCards() {

        setUp();

        game.shuffleDeck();
        game.dealCards();

        assertTrue(game.isRunning());

        assertEquals(player1, game.getCurrentPlayer());

        // test playing first card in game
        Card p1Card = player1.getHand().get(1);
        assertNotEquals(null, game.getTopCard());

        if (game.canPlayCard(p1Card)) { // if the player can play the first card

            if (!p1Card.getSymbol().equals(Card.Colour.WILD)) { // if the card is not a wild
                assertEquals(true, game.canPlayCard(p1Card));
                game.playCard(p1Card);
                assertFalse(game.playCard(p1Card));
                assertEquals(6, player1.getHand().size());
                // check if the card was placed on the played cards pile correctly
                assertEquals(p1Card.getColour(), game.getTopCard().getColour());
            }

            else { // if the card is a wild
                assertEquals(true, game.canPlayCard(p1Card));
                game.playCard(p1Card);
                assertTrue(game.playCard(p1Card));
            }

            if (p1Card.getSymbol().equals(Card.Symbol.DRAW_ONE)) { // if the card is a draw one
                assertEquals(true, game.canPlayCard(p1Card));
                game.playCard(p1Card);
                assertFalse(game.playCard(p1Card));
                assertEquals(6, player1.getHand().size());
                assertEquals(8, player2.getHand().size());
                assertEquals(104 - 7 * 4 - 1 - 1, deck.size());
                game.advanceTurn();
                assertEquals(player2, game.getCurrentPlayer());
            }

            else if (p1Card.getSymbol().equals(Card.Symbol.SKIP)) { // if the card is a skip
                assertEquals(true, game.canPlayCard(p1Card));
                game.playCard(p1Card);
                assertFalse(game.playCard(p1Card));
                assertEquals(6, player1.getHand().size());
                assertEquals(1, game.nextPlayer());
                game.advanceTurn();
                assertEquals(player3, game.getCurrentPlayer());
            }

            else if (p1Card.getSymbol().equals(Card.Symbol.REVERSE)) { // if the card is a reverse
                assertEquals(true, game.canPlayCard(p1Card));
                game.playCard(p1Card);
                assertFalse(game.playCard(p1Card));
                assertEquals(6, player1.getHand().size());
                assertEquals(2, game.nextPlayer());
                game.advanceTurn();
                assertEquals(player4, game.getCurrentPlayer());
            }

            else if (p1Card.getSymbol().equals(Card.Symbol.WILD_DRAW_TWO)) { // if the card is a wild draw two
                assertEquals(true, game.canPlayCard(p1Card));
                game.playCard(p1Card);
                assertFalse(game.playCard(p1Card));
                assertEquals(6, player1.getHand().size());
                assertEquals(9, player3.getHand().size());
                assertEquals(104 - 7 * 4 - 1 - 2, deck.size());
                assertEquals(1, game.nextPlayer());
                game.advanceTurn();
                assertEquals(player3, game.getCurrentPlayer());
            }
        }
        assertNotEquals(null, game.getTopCard());
        assertNotEquals(0, game.getCurrentScore());
    }

    @Test
    public void testEndGame() {

        setUp();

        game.shuffleDeck();
        game.dealCards();

        assertEquals(player1, game.getCurrentPlayer());
        game.assignScore();
        assertNotEquals(500, game.getCurrentScore());

        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        game.assignScore();
        assertNotEquals(500, game.getCurrentScore());

        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        game.assignScore();
        assertNotEquals(500, game.getCurrentScore());

        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        game.assignScore();
        assertNotEquals(500, game.getCurrentScore());

        assertFalse(game.hasWonGame());
    }
}