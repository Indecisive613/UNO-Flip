package test;

import static main.models.cards.Card.Symbol.*;
import static main.models.cards.Card.Colour.*;

import java.util.ArrayList;
import java.util.Stack;

import main.models.DeckBuilder;
import main.models.Game;
import main.models.Player;
import main.models.cards.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A class to test Game
 *
 * @author Jackie Smolkin-Lerner, Jake Siushansian, Fiona Cheng
 */
public class GameTest {
    Game game;
    Stack<DoubleSidedCard> deck;
    Player player1, player2,  player3, player4;

    @Before
    public void setUp() {

        ArrayList<DoubleSidedCard> hand1 = new ArrayList<DoubleSidedCard>(); // create empty hand for player1
        ArrayList<DoubleSidedCard> hand2 = new ArrayList<DoubleSidedCard>(); // create empty hand for player2
        ArrayList<DoubleSidedCard> hand3 = new ArrayList<DoubleSidedCard>(); // create empty hand for player3
        ArrayList<DoubleSidedCard> hand4 = new ArrayList<DoubleSidedCard>(); // create empty hand for player4
        player1 = new Player("A", hand1, false); // create player1
        player2 = new Player("B", hand2, false); // create player2
        player3 = new Player("C", hand3, false); // create player3
        player4 = new Player("D", hand4, false); // create player4

        deck = DeckBuilder.createDoubleSidedDeck(); // create the deck
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
        assertEquals(112, deck.size());

        game.shuffleDeck(); // shuffle the deck
        game.dealCards(Game.STARTING_HAND_SIZE); // deal the cards

        // test that the players in the game have been dealt their 7 cards at the start of the game
        assertEquals(7, player1.getHand().size());
        assertEquals(7, player2.getHand().size());
        assertEquals(7, player3.getHand().size());
        assertEquals(7, player4.getHand().size());

        // all 4 players are dealt 7 cards + 1 card placed on the table
        assertEquals(112 - 7*4 - 1, deck.size());

        assertFalse(game.hasWonGame()); // the game has just started so there should not be a winner

        // there should be a top card on the table
        assertNotEquals(null, game.getTopCard());

        // the game should start on the light side
        assertEquals(Card.Side.LIGHT, game.getTopCard().getSide());
    }
    @Test
    public void testDrawingCards() {

        game.shuffleDeck(); // shuffle the deck
        game.dealCards(Game.STARTING_HAND_SIZE); // deal the cards

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer()); // the current player should be player1
        game.drawCard(game.getCurrentPlayer(), true); // player1 draws a card from the deck
        assertEquals(8, player1.getHand().size()); // player1 should have 8 cards
        assertEquals(112 - 7*4 - 1 - 1, deck.size()); // the deck should have 1 less card

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer()); // the current player should be player2
        game.drawCard(game.getCurrentPlayer(), true); // player2 draws a card from the deck
        assertEquals(8, player2.getHand().size()); // player2 should have 8 cards
        assertEquals(112 - 7*4 - 1 - 1 - 1, deck.size()); // the deck should have 1 less card

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer()); // the current player should be player3
        game.drawCard(game.getCurrentPlayer(), true); // player3 draws a card from the deck
        assertEquals(8, player3.getHand().size()); // player3 should have 8 cards
        assertEquals(112 - 7*4 - 1 - 1 - 1 - 1, deck.size()); // the deck should have 1 less card

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer()); // the current player should be player4
        game.drawCard(game.getCurrentPlayer(), true); // player4 draws a card from the deck
        assertEquals(8, player4.getHand().size()); // player4 should have 8 cards
        assertEquals(112 - 7*4 - 1 - 1 - 1 - 1 - 1, deck.size()); // the deck should have 1 less card

        assertNotEquals(null, game.getTopCard());
    }

    @Test
    public void testAddingCards() {

        game.shuffleDeck(); // shuffle the deck
        game.dealCards(Game.STARTING_HAND_SIZE); // deal the cards

        assertTrue(game.isRunning()); // the game should be running

        assertEquals(112 - 7*4 - 1, deck.size());

        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer()); // the current player should be player1
        assertEquals(7, player1.getHand().size());
        game.addToDeck(player1.getHand()); // put all the cards in player1's hand back in the deck
        player1.clearHand(); // clear player1's hand
        assertEquals(0, player1.getHand().size());
        assertEquals(112 - 7*3 - 1, deck.size());

        assertTrue(game.isRunning()); // the game should be running

        player1.dealCard(deck.pop());

        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer()); // the current player should be player2
        assertEquals(7, player2.getHand().size());
        game.addToDeck(player2.getHand()); // put all the cards in player2's hand back in the deck
        player2.clearHand(); // clear player2's hand
        assertEquals(0, player2.getHand().size());
        assertEquals(112 - 7*2 - 2*1, deck.size());

        player2.dealCard(deck.pop());

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer()); // the current player should be player3
        assertEquals(7, player3.getHand().size());
        game.addToDeck(player3.getHand()); // put all the cards in player3's hand back in the deck
        player3.clearHand(); // clear player3's hand
        assertEquals(0, player3.getHand().size());
        assertEquals(112 - 7 - 3*1, deck.size());

        player3.dealCard(deck.pop());

        assertTrue(game.isRunning()); // the game should be running

        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer()); // the current player should be player4
        assertEquals(7, player4.getHand().size());
        game.addToDeck(player4.getHand()); // put all the cards in player4's hand back in the deck
        player4.clearHand(); // clear player4's hand
        assertEquals(0, player4.getHand().size());
        assertEquals(112 - 4*1, deck.size());

        assertNotEquals(null, game.getTopCard());
    }

    @Test
    public void testPlayingLightCards() {

        // the cards must be dealt and then returned to the deck ...
        // ... to ensure that there is a top card in the played cards pile
        game.shuffleDeck();
        game.dealCards(0);

        assertNotEquals(null, game.getTopCard());

        // create hand for player1 with some number and action cards
        player1.dealCard(new DoubleSidedCard(new NormalCard(RED, ONE), new NormalCard(TEAL, TWO)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, FIVE), new SkipEveryoneCard(PINK)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(GREEN, TWO), new WildCard(Card.Side.DARK)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(BLUE, ONE), new ReverseCard(PURPLE)));
        player1.dealCard(new DoubleSidedCard(new DrawOneCard(RED), new FlipCard(TEAL)));
        player1.dealCard(new DoubleSidedCard(new SkipCard(GREEN), new NormalCard(PINK, THREE)));
        player1.dealCard(new DoubleSidedCard(new ReverseCard(RED), new NormalCard(ORANGE, ONE)));
        assertEquals(7, player1.getHand().size());

        // create hand for player2 with some number, action, and wild cards
        player2.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, NINE), new NormalCard(TEAL, ONE)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(RED, FIVE), new NormalCard(TEAL, TWO)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(BLUE, EIGHT), new NormalCard(TEAL, THREE)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(GREEN, SIX), new NormalCard(TEAL, FOUR)));
        player2.dealCard(new DoubleSidedCard(new DrawOneCard(BLUE), new NormalCard(TEAL, FIVE)));
        player2.dealCard(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(TEAL, SIX)));
        player2.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new NormalCard(TEAL, SEVEN)));
        assertEquals(7, player2.getHand().size());

        // create hand for player3 with only number cards
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, FIVE), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, TWO), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(BLUE, THREE), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(RED, FOUR), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(RED, SIX), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, EIGHT), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, ONE), new WildCard(Card.Side.DARK)));
        assertEquals(7, player3.getHand().size());

        // create hand for player4 with only action and wild cards
        player4.dealCard(new DoubleSidedCard(new SkipCard(BLUE), new DrawFiveCard(PINK)));
        player4.dealCard(new DoubleSidedCard(new ReverseCard(YELLOW), new WildDrawColourCard()));
        player4.dealCard(new DoubleSidedCard(new ReverseCard(GREEN), new WildCard(Card.Side.DARK)));
        player4.dealCard(new DoubleSidedCard(new DrawOneCard(GREEN), new NormalCard(ORANGE, ONE)));
        player4.dealCard(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new SkipEveryoneCard(TEAL)));
        player4.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new ReverseCard(PINK)));
        player4.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new FlipCard(PINK)));
        assertEquals(7, player4.getHand().size());

        // sets the current colour to RED so that the first card can be played ...
        // ... despite not knowing anything about the current top card
        game.setCurrentColour(RED);

        // test playing RED ONE from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(0).getActiveSide()));
        game.playCard(player1.playCard(0));
        assertEquals(6, player1.getHand().size());
        assertEquals(new NormalCard(RED, ONE), game.getTopCard());

        // test playing RED FIVE from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(1).getActiveSide()));
        game.playCard(player2.playCard(1));
        assertEquals(6, player2.getHand().size());
        assertEquals(new NormalCard(RED, FIVE), game.getTopCard());

        // test playing GREEN FIVE from the hand of player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(0).getActiveSide()));
        game.playCard(player3.playCard(0));
        assertEquals(6, player3.getHand().size());
        assertEquals(new NormalCard(GREEN, FIVE), game.getTopCard());

        // test playing GREEN DRAW_ONE from the hand of player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player4.getHand().get(3).getActiveSide()));
        game.playCard(player4.playCard(3));
        assertEquals(6, player4.getHand().size());
        assertEquals(new DrawOneCard(GREEN), game.getTopCard());

        // test playing GREEN SKIP from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(7, player1.getHand().size());
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(4).getActiveSide()));
        game.playCard(player1.playCard(4));
        assertEquals(6, player1.getHand().size());
        assertEquals(new SkipCard(GREEN), game.getTopCard());

        // skip player2

        // test playing GREEN ONE from the hand of player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(5).getActiveSide()));
        game.playCard(player3.playCard(5));
        assertEquals(5, player3.getHand().size());
        assertEquals(new NormalCard(GREEN, ONE), game.getTopCard());

        // test playing GREEN REVERSE from the hand of player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player4.getHand().get(2).getActiveSide()));
        game.playCard(player4.playCard(2));
        assertEquals(5, player4.getHand().size());
        assertEquals(new ReverseCard(GREEN), game.getTopCard());

        // test playing GREEN EIGHT from the hand of player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(4).getActiveSide()));
        game.playCard(player3.playCard(4));
        assertEquals(4, player3.getHand().size());
        assertEquals(new NormalCard(GREEN, EIGHT), game.getTopCard());

        // test playing BLUE EIGHT from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(1).getActiveSide()));
        game.playCard(player2.playCard(1));
        assertEquals(5, player2.getHand().size());
        assertEquals(new NormalCard(BLUE, EIGHT), game.getTopCard());

        // test playing BLUE ONE from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(2).getActiveSide()));
        game.playCard(player1.playCard(2));
        assertEquals(5, player1.getHand().size());
        assertEquals(new NormalCard(BLUE, ONE), game.getTopCard());

        // test playing WILD WILD_DRAW_TWO from the hand of player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player4.getHand().get(4).getActiveSide()));
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
        assertTrue(game.canPlayCard(player2.getHand().get(4).getActiveSide()));
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
        assertTrue(game.canPlayCard(player4.getHand().get(0).getActiveSide()));
        game.playCard(player4.playCard(0));
        assertEquals(3, player4.getHand().size());
        assertEquals(new SkipCard(BLUE), game.getTopCard());

        // skip player3

        // test playing BLUE DRAW_ONE from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(2).getActiveSide()));
        game.playCard(player2.playCard(2));
        assertEquals(3, player2.getHand().size());
        assertEquals(new DrawOneCard(BLUE), game.getTopCard());
    }

    @Test
    public void testPlayingDarkCards() {

        // the cards must be dealt and then returned to the deck ...
        // ... to ensure that there is a top card in the played cards pile
        game.shuffleDeck();
        game.dealCards(0);

        assertNotEquals(null, game.getTopCard());

        // create hand for player1 with some number and action cards
        player1.dealCard(new DoubleSidedCard(new NormalCard(RED, ONE), new SkipEveryoneCard(TEAL)));
        player1.dealCard(new DoubleSidedCard(new FlipCard(YELLOW), new SkipEveryoneCard(PINK)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(GREEN, TWO), new WildCard(Card.Side.DARK)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(BLUE, ONE), new ReverseCard(PURPLE)));
        player1.dealCard(new DoubleSidedCard(new DrawOneCard(RED), new FlipCard(TEAL)));
        player1.dealCard(new DoubleSidedCard(new SkipCard(GREEN), new NormalCard(PINK, THREE)));
        player1.dealCard(new DoubleSidedCard(new ReverseCard(RED), new NormalCard(ORANGE, ONE)));
        assertEquals(7, player1.getHand().size());

        // create hand for player2 with some number, action, and wild cards
        player2.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, NINE), new NormalCard(TEAL, ONE)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(RED, FIVE), new NormalCard(TEAL, TWO)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(BLUE, EIGHT), new NormalCard(TEAL, THREE)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(GREEN, SIX), new NormalCard(TEAL, FOUR)));
        player2.dealCard(new DoubleSidedCard(new DrawOneCard(BLUE), new NormalCard(TEAL, FIVE)));
        player2.dealCard(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(TEAL, SIX)));
        player2.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new NormalCard(TEAL, SEVEN)));
        assertEquals(7, player2.getHand().size());

        // create hand for player3 with only number cards
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, FIVE), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, TWO), new WildDrawColourCard()));
        player3.dealCard(new DoubleSidedCard(new NormalCard(BLUE, THREE), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(RED, FOUR), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(RED, SIX), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, EIGHT), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, ONE), new WildCard(Card.Side.DARK)));
        assertEquals(7, player3.getHand().size());

        // create hand for player4 with only action and wild cards
        player4.dealCard(new DoubleSidedCard(new SkipCard(BLUE), new DrawFiveCard(PINK)));
        player4.dealCard(new DoubleSidedCard(new ReverseCard(YELLOW), new WildDrawColourCard()));
        player4.dealCard(new DoubleSidedCard(new ReverseCard(GREEN), new WildCard(Card.Side.DARK)));
        player4.dealCard(new DoubleSidedCard(new DrawOneCard(GREEN), new NormalCard(ORANGE, ONE)));
        player4.dealCard(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new SkipEveryoneCard(TEAL)));
        player4.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new ReverseCard(PINK)));
        player4.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new FlipCard(PINK)));
        assertEquals(7, player4.getHand().size());

        // sets the current colour to YELLOW so that the first card can be played
        game.setCurrentColour(YELLOW);

        // test playing YELLOW FLIP from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(1).getActiveSide()));
        game.playCard(player1.playCard(1));
        assertEquals(6, player1.getHand().size());
        assertEquals(Card.Side.DARK, game.getTopCard().getSide());

        // sets the current colour to RED so that the first dark card can be played
        game.setCurrentColour(TEAL);

        // test playing TEAL SEVEN from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(6).getActiveSide()));
        game.playCard(player2.playCard(6));
        assertEquals(6, player2.getHand().size());
        assertEquals(new NormalCard(TEAL, SEVEN), game.getTopCard());

        // test playing WILD from the hand of player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(0).getActiveSide()));
        game.playCard(player3.playCard(0));
        assertEquals(6, player3.getHand().size());
        assertEquals(new WildCard(Card.Side.DARK), game.getTopCard());

        // Simulates the player picking a colour
        game.setCurrentColour(PINK);
        assertEquals(game.getCurrentColour(), PINK);

        // test playing PINK DRAW FIVE from the hand of player4
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(0).getActiveSide()));
        game.playCard(player4.playCard(0));
        assertEquals(6, player4.getHand().size());
        assertEquals(new DrawFiveCard(PINK), game.getTopCard());
        assertEquals(11, player1.getHand().size());

        // test playing PINK THREE from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(4).getActiveSide()));
        game.playCard(player1.playCard(4));
        assertEquals(10, player1.getHand().size());
        assertEquals(new NormalCard(PINK, THREE), game.getTopCard());

        // test playing TEAL THREE from the hand of player2
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player2.getHand().get(2).getActiveSide()));
        game.playCard(player2.playCard(2));
        assertEquals(5, player2.getHand().size());
        assertEquals(new NormalCard(TEAL, THREE), game.getTopCard());

        // test playing WILD DRAW COLOUR from the hand of player3
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player3.getHand().get(0).getActiveSide()));
        game.playCard(player3.playCard(0));
        assertEquals(5, player3.getHand().size());
        assertEquals(new WildDrawColourCard(), game.getTopCard());

        // player3 chose teal, and player4 must draw cards until a TEAL is reached
        assertEquals(game.getCurrentColour(), TEAL);
        assertTrue(player4.getHand().size() > 6);
        assertEquals(TEAL, player4.getHand().get(player4.getHand().size()-1).getActiveSide().getColour());

        //Player 4 is skipped

        // test playing SKIP EVERYONE from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(0).getActiveSide()));
        game.playCard(player1.playCard(0));
        assertEquals(9, player1.getHand().size());
        assertEquals(new SkipEveryoneCard(TEAL), game.getTopCard());

        // players 2-4 are skipped

        // test playing FLIP from the hand of player1
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        assertTrue(game.canPlayCard(player1.getHand().get(2).getActiveSide()));
        game.playCard(player1.playCard(2));
        assertEquals(8, player1.getHand().size());

        // Should flip back to the light side
        assertEquals(Card.Side.LIGHT, game.getTopCard().getSide());
        assertEquals(new FlipCard(YELLOW), game.getTopCard());
    }

    @Test
    public void testScore() {
        game.shuffleDeck();
        game.dealCards(0);

        assertNotEquals(null, game.getTopCard());

        // Light: 59 Dark: 116
        player1.dealCard(new DoubleSidedCard(new NormalCard(RED, ONE), new NormalCard(TEAL, TWO)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, FIVE), new SkipEveryoneCard(PINK)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(GREEN, TWO), new WildCard(Card.Side.DARK)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(BLUE, ONE), new ReverseCard(PURPLE)));
        player1.dealCard(new DoubleSidedCard(new DrawOneCard(RED), new FlipCard(TEAL)));
        player1.dealCard(new DoubleSidedCard(new SkipCard(GREEN), new NormalCard(PINK, THREE)));
        player1.dealCard(new DoubleSidedCard(new ReverseCard(RED), new NormalCard(ORANGE, ONE)));
        assertEquals(7, player1.getHand().size());

        // Light: 128 Dark: 28
        player2.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, NINE), new NormalCard(TEAL, ONE)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(RED, FIVE), new NormalCard(TEAL, TWO)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(BLUE, EIGHT), new NormalCard(TEAL, THREE)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(GREEN, SIX), new NormalCard(TEAL, FOUR)));
        player2.dealCard(new DoubleSidedCard(new DrawOneCard(BLUE), new NormalCard(TEAL, FIVE)));
        player2.dealCard(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(TEAL, SIX)));
        player2.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new NormalCard(TEAL, SEVEN)));
        assertEquals(7, player2.getHand().size());

        // Light: 29 Dark: 280
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, FIVE), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, TWO), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(BLUE, THREE), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(RED, FOUR), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(RED, SIX), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, EIGHT), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, ONE), new WildCard(Card.Side.DARK)));
        assertEquals(7, player3.getHand().size());

        // Light: 210 Dark: 191
        player4.dealCard(new DoubleSidedCard(new SkipCard(BLUE), new DrawFiveCard(PINK)));
        player4.dealCard(new DoubleSidedCard(new ReverseCard(YELLOW), new WildDrawColourCard()));
        player4.dealCard(new DoubleSidedCard(new ReverseCard(GREEN), new WildCard(Card.Side.DARK)));
        player4.dealCard(new DoubleSidedCard(new DrawOneCard(GREEN), new NormalCard(ORANGE, ONE)));
        player4.dealCard(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new SkipEveryoneCard(TEAL)));
        player4.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new ReverseCard(PINK)));
        player4.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new FlipCard(PINK)));
        assertEquals(7, player4.getHand().size());

        //Total of all light: 426
        //Total of all dark: 615

        // check the scores before calculations
        assertEquals(0, player1.getScore());
        assertEquals(0, player2.getScore());
        assertEquals(0, player3.getScore());
        assertEquals(0, player4.getScore());

        // Test light side scoring
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player1, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(367, game.getCurrentScore() - 59);
        assertEquals(367, game.getCurrentPlayer().getScore() - 59);

        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player2, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(298, game.getCurrentScore() - 128);
        assertEquals(298, game.getCurrentPlayer().getScore() - 128);

        // Test dark side scoring
        game.flip();

        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player3, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(335, game.getCurrentScore() - 280);
        assertEquals(335, game.getCurrentPlayer().getScore() - 280);

        assertTrue(game.isRunning());
        game.advanceTurn();
        assertEquals(player4, game.getCurrentPlayer());
        game.assignScore();
        assertEquals(424, game.getCurrentScore() - 191);
        assertEquals(424, game.getCurrentPlayer().getScore() - 191);

        assertEquals(true, game.hasWonGame()); // Round ends because the way Player C scored 615 points (the scoring counts their own cards, but this is okay because in a real game only the player with no cards would score)

        game.flip();
    }

    @Test
    public void testEndGame() {
        game.shuffleDeck();
        game.dealCards(0);

        assertNotEquals(null, game.getTopCard());

        // Light: 59 Dark: 116
        player1.dealCard(new DoubleSidedCard(new NormalCard(RED, ONE), new NormalCard(TEAL, TWO)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, FIVE), new SkipEveryoneCard(PINK)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(GREEN, TWO), new WildCard(Card.Side.DARK)));
        player1.dealCard(new DoubleSidedCard(new NormalCard(BLUE, ONE), new ReverseCard(PURPLE)));
        player1.dealCard(new DoubleSidedCard(new DrawOneCard(RED), new FlipCard(TEAL)));
        player1.dealCard(new DoubleSidedCard(new SkipCard(GREEN), new NormalCard(PINK, THREE)));
        player1.dealCard(new DoubleSidedCard(new ReverseCard(RED), new NormalCard(ORANGE, ONE)));
        assertEquals(7, player1.getHand().size());

        // Light: 128 Dark: 28
        player2.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, NINE), new NormalCard(TEAL, ONE)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(RED, FIVE), new NormalCard(TEAL, TWO)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(BLUE, EIGHT), new NormalCard(TEAL, THREE)));
        player2.dealCard(new DoubleSidedCard(new NormalCard(GREEN, SIX), new NormalCard(TEAL, FOUR)));
        player2.dealCard(new DoubleSidedCard(new DrawOneCard(BLUE), new NormalCard(TEAL, FIVE)));
        player2.dealCard(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(TEAL, SIX)));
        player2.dealCard(new DoubleSidedCard(new WildDrawTwoCard(), new NormalCard(TEAL, SEVEN)));
        assertEquals(7, player2.getHand().size());

        // Light: 29 Dark: 280
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, FIVE), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(YELLOW, TWO), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(BLUE, THREE), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(RED, FOUR), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(RED, SIX), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, EIGHT), new WildCard(Card.Side.DARK)));
        player3.dealCard(new DoubleSidedCard(new NormalCard(GREEN, ONE), new WildCard(Card.Side.DARK)));
        assertEquals(7, player3.getHand().size());

        // player4 has no cards
        // points: 0

        game.advanceTurn();

        // Player 1 takes their turn
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertFalse(game.hasWonRound());

        // Player 2 takes their turn
        assertTrue(game.isRunning());
        game.advanceTurn();
        assertFalse(game.hasWonRound());

        // Player 3 takes their turn
        assertTrue(game.isRunning());
        game.advanceTurn();

        // Player 4 has 0 cards, so should trigger end of the round
        assertTrue(game.isRunning());
        assertTrue(game.hasWonRound());
        game.assignScore();
        assertFalse(game.hasWonGame());
        game.startNewRound(); // the game should now be reset

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
}