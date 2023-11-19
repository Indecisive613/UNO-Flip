/*package test;

import main.*;

import static main.cards.Card.Symbol.*;
import static main.cards.Card.Colour.*;

import java.util.ArrayList;
import java.util.Stack;

import main.cards.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test Game
 *
 * @author Jackie Smolkin-Lerner, Jake Siushansian
 *
public class GameTest {
    Game game;
    Stack<Card> deck;
    Player player1, player2,  player3, player4;

    @Before
    public void setUp() {

        ArrayList<Card> hand1 = new ArrayList<Card>(); // create empty hand for player1
        ArrayList<Card> hand2 = new ArrayList<Card>(); // create empty hand for player2
        ArrayList<Card> hand3 = new ArrayList<Card>(); // create empty hand for player3
        ArrayList<Card> hand4 = new ArrayList<Card>(); // empty create hand for player4
        player1 = new Player("Homer", hand1, false); // create player1
        player2 = new Player("Marge", hand2, false); // create player2
        player3 = new Player("Bart", hand3, false); // create player3
        player4 = new Player("Lisa", hand4, false); // create player4

        deck = GameRunner.createDeck(); // create the deck
        game = new Game(deck); // create the game with the players and the deck

        // add the players to the game
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        game.setRunning(true);
    }

    @Test
    public void testStartGame() {

        // test that the players have been added to the game
        ArrayList<Player> testPlayers = new ArrayList<Player>();
        testPlayers.add(player1);
        testPlayers.add(player2);
        testPlayers.add(player3);
        testPlayers.add(player4);
        assertEquals(testPlayers, game.getPlayers());

        // test that the players in the game do not have any cards yet
        assertEquals(0, player1.getHand().size());
        assertEquals(0, player2.getHand().size());
        assertEquals(0, player3.getHand().size());
        assertEquals(0, player4.getHand().size());
        assertEquals(104, deck.size());

        game.shuffleDeck(); // shuffle the deck
        game.dealCards(); // deal the cards

        // test that the players in the game have been dealt their 7 cards at the start of the game
        assertEquals(7, player1.getHand().size());
        assertEquals(7, player2.getHand().size());
        assertEquals(7, player3.getHand().size());
        assertEquals(7, player4.getHand().size());

        // all 4 players are dealt 7 cards + 1 card placed on the table
        assertEquals(104 - 7*4 - 1, deck.size());

        assertFalse(game.hasWonGame()); // the game has just started so there should not be a winner

        // there should be a top card on the table
        assertNotEquals(null, game.getTopCard());
    }

    @Test
    public void testDrawingCards() {

        game.shuffleDeck(); // shuffle the deck
        game.dealCards(); // deal the cards

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer()); // the current player should be player1
        game.drawCard(game.getCurrentPlayer()); // player1 draws a card from the deck
        assertEquals(8, player1.getHand().size()); // player1 should have 8 cards
        assertEquals(104 - 7*4 - 1 - 1, deck.size()); // the deck should have 1 less card

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer()); // the current player should be player2
        game.drawCard(game.getCurrentPlayer()); // player2 draws a card from the deck
        assertEquals(8, player2.getHand().size()); // player2 should have 8 cards
        assertEquals(104 - 7*4 - 1 - 1 - 1, deck.size()); // the deck should have 1 less card

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer()); // the current player should be player3
        game.drawCard(game.getCurrentPlayer()); // player3 draws a card from the deck
        assertEquals(8, player3.getHand().size()); // player3 should have 8 cards
        assertEquals(104 - 7*4 - 1 - 1 - 1 - 1, deck.size()); // the deck should have 1 less card

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer()); // the current player should be player4
        game.drawCard(game.getCurrentPlayer()); // player4 draws a card from the deck
        assertEquals(8, player4.getHand().size()); // player4 should have 8 cards
        assertEquals(104 - 7*4 - 1 - 1 - 1 - 1 - 1, deck.size()); // the deck should have 1 less card

        assertNotEquals(null, game.getTopCard());
    }

    @Test
    public void testAddingCards() {

        game.shuffleDeck(); // shuffle the deck
        game.dealCards(); // deal the cards

        assertTrue(game.isRunning()); // the game should be running

        assertEquals(104 - 7*4 - 1, deck.size());

        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer()); // the current player should be player1
        assertEquals(7, player1.getHand().size());
        game.addToDeck(player1.getHand()); // put all the cards in player1's hand back in the deck
        player1.clearHand(); // clear player1's hand
        assertEquals(0, player1.getHand().size());
        assertEquals(104 - 7*3 - 1, deck.size());

        assertTrue(game.isRunning()); // the game should be running

        player1.dealCard(deck.pop());

        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer()); // the current player should be player2
        assertEquals(7, player2.getHand().size());
        game.addToDeck(player2.getHand()); // put all the cards in player2's hand back in the deck
        player2.clearHand(); // clear player2's hand
        assertEquals(0, player2.getHand().size());
        assertEquals(104 - 7*2 - 2*1, deck.size());

        player2.dealCard(deck.pop());

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer()); // the current player should be player3
        assertEquals(7, player3.getHand().size());
        game.addToDeck(player3.getHand()); // put all the cards in player3's hand back in the deck
        player3.clearHand(); // clear player3's hand
        assertEquals(0, player3.clearHand().size());
        assertEquals(104 - 7 - 3*1, deck.size());

        player3.dealCard(deck.pop());

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer()); // the current player should be player4
        assertEquals(7, player4.getHand().size());
        game.addToDeck(player4.getHand()); // put all the cards in player4's hand back in the deck
        player4.clearHand(); // clear player4's hand
        assertEquals(0, player4.getHand().size());
        assertEquals(104 - 4*1, deck.size());

        assertNotEquals(null, game.getTopCard());
    }

    @Test
    public void testPlayingCards() {

        // the cards must be dealt and then returned to the deck ...
        // ... to ensure that there is a top card in the played cards pile
        game.shuffleDeck();
        game.dealCards();

        assertEquals(7, player1.getHand().size());
        assertEquals(7, player2.getHand().size());
        assertEquals(7, player3.getHand().size());
        assertEquals(7, player4.getHand().size());
        game.addToDeck(player1.clearHand());
        game.addToDeck(player2.clearHand());
        game.addToDeck(player3.clearHand());
        game.addToDeck(player4.clearHand());
        assertEquals(0, player1.getHand().size());
        assertEquals(0, player2.getHand().size());
        assertEquals(0, player3.getHand().size());
        assertEquals(0, player4.getHand().size());

        assertNotEquals(null, game.getTopCard());

        // create hand for player1 with some number and action cards
        player1.dealCard(new NormalCard(RED, ONE));
        player1.dealCard(new NormalCard(YELLOW, FIVE));
        player1.dealCard(new NormalCard(GREEN, TWO));
        player1.dealCard(new NormalCard(BLUE, ONE));
        player1.dealCard(new DrawOneCard(RED));
        player1.dealCard(new SkipCard(GREEN));
        player1.dealCard(new ReverseCard(RED));
        assertEquals(7, player1.getHand().size());

        // create hand for player2 with some number, action, and wild cards
        player2.dealCard(new NormalCard(YELLOW, NINE));
        player2.dealCard(new NormalCard(RED, FIVE));
        player2.dealCard(new NormalCard(BLUE, EIGHT));
        player2.dealCard(new NormalCard(GREEN, SIX));
        player2.dealCard(new DrawOneCard(BLUE));
        player2.dealCard(new WildCard());
        player2.dealCard(new WildDrawTwoCard());
        assertEquals(7, player2.getHand().size());

        // create hand for player3 with only number cards
        player3.dealCard(new NormalCard(GREEN, FIVE));
        player3.dealCard(new NormalCard(YELLOW, TWO));
        player3.dealCard(new NormalCard(BLUE, THREE));
        player3.dealCard(new NormalCard(RED, FOUR));
        player3.dealCard(new NormalCard(RED, SIX));
        player3.dealCard(new NormalCard(GREEN, EIGHT));
        player3.dealCard(new NormalCard(GREEN, ONE));
        assertEquals(7, player3.getHand().size());

        // create hand for player4 with only action and wild cards
        player4.dealCard(new SkipCard(BLUE));
        player4.dealCard(new ReverseCard(YELLOW));
        player4.dealCard(new ReverseCard(GREEN));
        player4.dealCard(new DrawOneCard(GREEN));
        player4.dealCard(new WildCard());
        player4.dealCard(new WildDrawTwoCard());
        player4.dealCard(new WildDrawTwoCard());
        assertEquals(7, player4.getHand().size());

        // sets the current colour to RED so that the first card can be played ...
        // ... despite not knowing anything about the current top card
        game.setCurrentColour(RED);

        // test playing RED ONE from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(0)));
        game.playCard(player1.playCard(0));
        assertEquals(6, player1.getHand().size());
        assertEquals(new NormalCard(RED, ONE), game.getTopCard());

        // test playing RED FIVE from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(1)));
        game.playCard(player2.playCard(1));
        assertEquals(6, player2.getHand().size());
        assertEquals(new NormalCard(RED, FIVE), game.getTopCard());

        // test playing GREEN FIVE from the hand of player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(0)));
        game.playCard(player3.playCard(0));
        assertEquals(6, player3.getHand().size());
        assertEquals(new NormalCard(GREEN, FIVE), game.getTopCard());

        // test playing GREEN DRAW_ONE from the hand of player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player4.getHand().get(3)));
        game.playCard(player4.playCard(3));
        assertEquals(6, player4.getHand().size());
        assertEquals(new DrawOneCard(GREEN), game.getTopCard());

        // test playing GREEN SKIP from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(7, player1.getHand().size());
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(4)));
        game.playCard(player1.playCard(4));
        assertEquals(6, player1.getHand().size());
        assertEquals(new SkipCard(GREEN), game.getTopCard());

        // skip player2

        // test playing GREEN ONE from the hand of player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(5)));
        game.playCard(player3.playCard(5));
        assertEquals(5, player3.getHand().size());
        assertEquals(new NormalCard(GREEN, ONE), game.getTopCard());

        // test playing GREEN REVERSE from the hand of player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player4.getHand().get(2)));
        game.playCard(player4.playCard(2));
        assertEquals(5, player4.getHand().size());
        assertEquals(new ReverseCard(GREEN), game.getTopCard());

        // test playing GREEN EIGHT from the hand of player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(4)));
        game.playCard(player3.playCard(4));
        assertEquals(4, player3.getHand().size());
        assertEquals(new NormalCard(GREEN, EIGHT), game.getTopCard());

        // test playing BLUE EIGHT from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(1)));
        game.playCard(player2.playCard(1));
        assertEquals(5, player2.getHand().size());
        assertEquals(new NormalCard(BLUE, EIGHT), game.getTopCard());

        // test playing BLUE ONE from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(2)));
        game.playCard(player1.playCard(2));
        assertEquals(5, player1.getHand().size());
        assertEquals(new NormalCard(BLUE, ONE), game.getTopCard());

        // test playing WILD WILD_DRAW_TWO from the hand of player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player4.getHand().get(4)));
        game.playCard(player4.playCard(4));
        assertEquals(4, player4.getHand().size());
        assertEquals(new WildDrawTwoCard(), game.getTopCard());
        assertEquals(6, player3.getHand().size());

        // sets the current colour to GREEN
        game.setCurrentColour(GREEN);
        // checks that the current colour was correctly changed
        assertEquals(game.getCurrentColour(), GREEN);

        // skip player3

        // test playing WILD WILD_DRAW_TWO from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(4)));
        game.playCard(player2.playCard(4));
        assertEquals(4, player2.getHand().size());
        assertEquals(new WildDrawTwoCard(), game.getTopCard());
        assertEquals(7, player1.getHand().size());

        // sets the current colour to BLUE
        game.setCurrentColour(BLUE);
        // checks that the current colour was correctly changed
        assertEquals(game.getCurrentColour(), BLUE);

        // skip player1

        // test playing BLUE SKIP from the hand of player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player4.getHand().get(0)));
        game.playCard(player4.playCard(0));
        assertEquals(3, player4.getHand().size());
        assertEquals(new SkipCard(BLUE), game.getTopCard());

        // skip player3

        // test playing BLUE DRAW_ONE from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(2)));
        game.playCard(player2.playCard(2));
        assertEquals(3, player2.getHand().size());
        assertEquals(new DrawOneCard(BLUE), game.getTopCard());
    }

    @Test
    public void testScore() {


        // create hand for player1 with some number and action cards
        player1.dealCard(new NormalCard(RED, ONE));
        player1.dealCard(new NormalCard(YELLOW, FIVE));
        player1.dealCard(new NormalCard(GREEN, TWO));
        player1.dealCard(new NormalCard(BLUE, ONE));
        player1.dealCard(new DrawOneCard(RED));
        player1.dealCard(new SkipCard(GREEN));
        player1.dealCard(new ReverseCard(RED));
        assertEquals(7, player1.getHand().size()); // 59

        // create hand for player2 with some number, action, and wild cards
        player2.dealCard(new NormalCard(YELLOW, NINE));
        player2.dealCard(new NormalCard(RED, FIVE));
        player2.dealCard(new NormalCard(BLUE, NINE));
        player2.dealCard(new NormalCard(GREEN, SIX));
        player2.dealCard(new DrawOneCard(BLUE));
        player2.dealCard(new WildCard());
        player2.dealCard(new WildDrawTwoCard());
        assertEquals(7, player2.getHand().size()); // 129

        // create hand for player3 with only number cards
        player3.dealCard(new NormalCard(GREEN, ONE));
        player3.dealCard(new NormalCard(YELLOW, TWO));
        player3.dealCard(new NormalCard(BLUE, THREE));
        player3.dealCard(new NormalCard(RED, FOUR));
        player3.dealCard(new NormalCard(RED, SIX));
        player3.dealCard(new NormalCard(BLUE, EIGHT));
        player3.dealCard(new NormalCard(GREEN, TWO));
        assertEquals(7, player3.getHand().size()); // 26

        // create hand for player4 with only action and wild cards
        player4.dealCard(new SkipCard(BLUE));
        player4.dealCard(new ReverseCard(YELLOW));
        player4.dealCard(new ReverseCard(GREEN));
        player4.dealCard(new DrawOneCard(GREEN));
        player4.dealCard(new WildCard());
        player4.dealCard(new WildDrawTwoCard());
        player4.dealCard(new WildDrawTwoCard());
        assertEquals(7, player4.getHand().size()); // 210

        // check the scores before calculations
        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
        assertEquals(0, player3.getScore());
        assertEquals(0, player4.getScore());

        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(365, game.getCurrentScore() - 59);
        assertEquals(365, game.getCurrentPlayer().getScore() - 59);

        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(295, game.getCurrentScore() - 129);
        assertEquals(295, game.getCurrentPlayer().getScore() - 129);

        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(398, game.getCurrentScore() - 26);
        assertEquals(398, game.getCurrentPlayer().getScore() - 26);

        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(214, game.getCurrentScore() - 210);
        assertEquals(214, game.getCurrentPlayer().getScore() - 210);

        assertFalse(game.hasWonGame());

        // check the scores after calculations
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(365, game.getCurrentPlayer().getScore() - 59);
        game.advanceTurn();
        assertEquals(295, game.getCurrentPlayer().getScore() - 129);
        game.advanceTurn();
        assertEquals(398, game.getCurrentPlayer().getScore() - 26);
        game.advanceTurn();
        assertEquals(214, game.getCurrentPlayer().getScore() - 210);
    }

    @Test
    public void testEndGame() {

        // create hand for player1
        // points: 240
        player1.dealCard(new ReverseCard(RED));
        player1.dealCard(new ReverseCard(YELLOW));
        player1.dealCard(new ReverseCard(BLUE));
        player1.dealCard(new WildCard());
        player1.dealCard(new WildCard());
        player1.dealCard(new WildDrawTwoCard());
        player1.dealCard(new WildDrawTwoCard());
        assertEquals(7, player1.getHand().size());

        // Create hand for player2
        // points: 240
        player2.dealCard(new ReverseCard(GREEN));
        player2.dealCard(new SkipCard(RED));
        player2.dealCard(new SkipCard(YELLOW));
        player2.dealCard(new WildCard());
        player2.dealCard(new WildCard());
        player2.dealCard(new WildDrawTwoCard());
        player2.dealCard(new WildDrawTwoCard());
        assertEquals(7, player2.getHand().size());

        // create hand for player3
        // points: 84
        player3.dealCard(new NormalCard(RED, NINE));
        player3.dealCard(new NormalCard(YELLOW, NINE));
        player3.dealCard(new NormalCard(BLUE, NINE));
        player3.dealCard(new NormalCard(GREEN, NINE));
        player3.dealCard(new NormalCard(RED, EIGHT));
        player3.dealCard(new SkipCard(BLUE));
        player3.dealCard(new SkipCard(GREEN));
        assertEquals(7, player3.getHand().size());

        // player4 has no cards
        // points: 0

        // update the score for player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(324, game.getCurrentScore() - 240);
        assertEquals(324, game.getCurrentPlayer().getScore() - 240);
        assertTrue(game.hasWonGame());

        // update the score for player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(324, game.getCurrentScore() - 240);
        assertEquals(324, game.getCurrentPlayer().getScore() - 240);
        assertTrue(game.hasWonGame());

        // update the score for player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(480, game.getCurrentScore() - 84);
        assertEquals(480, game.getCurrentPlayer().getScore() - 84);
        assertTrue(game.hasWonGame());

        // update the score for player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(564, game.getCurrentScore() - 0);
        assertEquals(564, game.getCurrentPlayer().getScore() - 0);
        game.assignScore();

        // player4 should have won
        assertTrue(game.hasWonGame());
        game.resetGame(); // the game should now be reset

        // test that the game reset properly and the
        assertTrue(game.isRunning());
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
    }
}*/