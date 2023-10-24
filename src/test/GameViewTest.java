package test;

import main.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Tests for the GameView class
 *
 * @author Jackie Smolkin-Lerner
 */
public class GameViewTest {
    private ByteArrayOutputStream outContent;
    private GameView view;
    private Game game;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Stack<Card> deck = new Stack<>();
        deck.push(new Card(Card.Colour.WILD, Card.Symbol.WILD));
        deck.push(new Card(Card.Colour.RED, Card.Symbol.ONE));
        deck.push(new Card(Card.Colour.RED, Card.Symbol.TWO));
        deck.push(new Card(Card.Colour.BLUE, Card.Symbol.THREE));
        deck.push(new Card(Card.Colour.BLUE, Card.Symbol.FOUR));
        deck.push(new Card(Card.Colour.GREEN, Card.Symbol.FIVE));
        deck.push(new Card(Card.Colour.GREEN, Card.Symbol.SIX));
        deck.push(new Card(Card.Colour.WILD, Card.Symbol.WILD_DRAW_TWO));
        game = new Game(deck);

        ArrayList<Card> hand = new ArrayList<>();
        Player currentPlayer = new Player("TEST NAME", hand);

        game.addPlayer(currentPlayer);

        view = new GameDebugView();
        view.setGame(game);
        game.addView(view);
    }

    @Test
    public void updateNewTurn() {
        game.dealCards();
        game.advanceTurn();
        view.updateNewTurn(game.getCurrentPlayer());

        assertTrue(outContent.toString().contains("TEST NAME"));

        assertTrue(outContent.toString().contains("WILD WILD"));
        assertTrue(outContent.toString().contains("RED ONE"));
        assertTrue(outContent.toString().contains("RED TWO"));
        assertTrue(outContent.toString().contains("BLUE THREE"));
        assertTrue(outContent.toString().contains("BLUE FOUR"));
        assertTrue(outContent.toString().contains("GREEN FIVE"));
        assertTrue(outContent.toString().contains("GREEN SIX"));
        assertTrue(outContent.toString().contains("WILD WILD_DRAW_TWO"));
    }
}