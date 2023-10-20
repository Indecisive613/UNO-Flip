package test;

import main.Card;
import main.Game;
import main.GameRunner;
import main.Player;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

import main.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


class GameTest {


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

        deck = GameRunner.createDeck();
        game = new Game(deck); // create the game with the players and the deck

        game.addPlayer(player1);
        game.addPlayer(player3);
        game.addPlayer(player2);
        game.addPlayer(player4);
    }

    @Test
    public void testStartGame() {

        assertEquals(0, player1.getHand().size());
        assertEquals(0, player2.getHand().size());
        assertEquals(0, player3.getHand().size());
        assertEquals(0, player4.getHand().size());
        assertEquals(104, deck.size());

        game.dealCards();

        assertEquals(7, player1.getHand().size());
        assertEquals(7, player2.getHand().size());
        assertEquals(7, player3.getHand().size());
        assertEquals(7, player4.getHand().size());

        // All 4 players are dealt 7 cards + 1 card placed on the table
        assertEquals(104 - 7*4 - 1, deck.size());

        assertTrue(game.isRunning()); // the game should be running

        // player 1 draws a card from the deck
        game.drawCard(player1);
        assertEquals(8, player1.getHand().size());
        assertEquals(104 - 7*4 - 1 - 1, deck.size());

    }
}