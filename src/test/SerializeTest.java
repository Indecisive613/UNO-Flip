package test;

import main.models.Game;
import main.models.GameRunner;
import main.models.Player;
import main.models.cards.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

import static main.models.cards.Card.Colour.*;
import static main.models.cards.Card.Symbol.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Test class for the serializable portion of the UNO game
 *
 * @author Fiona Cheng
 */
public class SerializeTest {
    @org.junit.Test
    public void exportGame(){
        Stack<DoubleSidedCard> customDeck = new Stack<DoubleSidedCard>();

        customDeck.push(new DoubleSidedCard(new ReverseCard(RED), new SkipEveryoneCard(PINK))); // Only one card in the deck, otherwise shuffling messes things up

        Game game = new Game(customDeck);

        ArrayList<DoubleSidedCard> hand1 = new ArrayList<DoubleSidedCard>();
        ArrayList<DoubleSidedCard> hand2 = new ArrayList<DoubleSidedCard>();
        ArrayList<DoubleSidedCard> hand3 = new ArrayList<DoubleSidedCard>();
        ArrayList<DoubleSidedCard> hand4 = new ArrayList<DoubleSidedCard>();

        hand1.add(new DoubleSidedCard(new NormalCard(RED, ONE), new NormalCard(TEAL, TWO)));
        hand1.add(new DoubleSidedCard(new NormalCard(YELLOW, FIVE), new SkipEveryoneCard(PINK)));
        hand1.add(new DoubleSidedCard(new NormalCard(GREEN, TWO), new WildCard(Card.Side.DARK)));
        hand1.add(new DoubleSidedCard(new NormalCard(BLUE, ONE), new ReverseCard(PURPLE)));
        hand1.add(new DoubleSidedCard(new DrawOneCard(RED), new FlipCard(TEAL)));
        hand1.add(new DoubleSidedCard(new SkipCard(GREEN), new NormalCard(PINK, THREE)));
        hand1.add(new DoubleSidedCard(new ReverseCard(RED), new NormalCard(ORANGE, ONE)));

        hand2.add(new DoubleSidedCard(new NormalCard(YELLOW, NINE), new NormalCard(TEAL, ONE)));
        hand2.add(new DoubleSidedCard(new NormalCard(RED, FIVE), new NormalCard(TEAL, TWO)));
        hand2.add(new DoubleSidedCard(new NormalCard(BLUE, EIGHT), new NormalCard(TEAL, THREE)));
        hand2.add(new DoubleSidedCard(new NormalCard(GREEN, SIX), new NormalCard(TEAL, FOUR)));
        hand2.add(new DoubleSidedCard(new DrawOneCard(BLUE), new NormalCard(TEAL, FIVE)));
        hand2.add(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(TEAL, SIX)));
        hand2.add(new DoubleSidedCard(new WildDrawTwoCard(), new NormalCard(TEAL, SEVEN)));

        hand3.add(new DoubleSidedCard(new NormalCard(GREEN, FIVE), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(YELLOW, TWO), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(BLUE, THREE), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(RED, FOUR), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(RED, SIX), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(GREEN, EIGHT), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(GREEN, ONE), new WildCard(Card.Side.DARK)));

        hand4.add(new DoubleSidedCard(new SkipCard(BLUE), new DrawFiveCard(PINK)));
        hand4.add(new DoubleSidedCard(new ReverseCard(YELLOW), new WildDrawColourCard()));
        hand4.add(new DoubleSidedCard(new ReverseCard(GREEN), new WildCard(Card.Side.DARK)));
        hand4.add(new DoubleSidedCard(new DrawOneCard(GREEN), new NormalCard(ORANGE, ONE)));
        hand4.add(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new SkipEveryoneCard(TEAL)));
        hand4.add(new DoubleSidedCard(new WildDrawTwoCard(), new ReverseCard(PINK)));
        hand4.add(new DoubleSidedCard(new WildDrawTwoCard(), new FlipCard(PINK)));

        Player player1 = new Player("Player 1", hand1, false);
        Player player2 = new Player("Player 2", hand2, true);
        Player player3 = new Player("Player 3", hand3, true);
        Player player4 = new Player("Player 4", hand4, false);

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        game.shuffleDeck();
        game.dealCards(0);

        assertEquals(new ReverseCard(RED), game.getTopCard());

        try {
            FileOutputStream file = new FileOutputStream("Serialized.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(game);

            // Ensure file exists
            File filename = new File("Serialized.txt");
            assertTrue(filename.exists());
            assertEquals(0, filename.length());

        } catch (IOException e) {
            System.out.println("IOException is caught");
            assertEquals("No exception", "Exception found");
        }
    }

    @org.junit.Test
    public void importGame(){
        Stack<DoubleSidedCard> customDeck = new Stack<DoubleSidedCard>();

        customDeck.push(new DoubleSidedCard(new ReverseCard(RED), new SkipEveryoneCard(PINK))); // Only one card in the deck, otherwise shuffling messes things up

        Game game = new Game(customDeck);
        Game importedGame = null;

        ArrayList<DoubleSidedCard> hand1 = new ArrayList<DoubleSidedCard>();
        ArrayList<DoubleSidedCard> hand2 = new ArrayList<DoubleSidedCard>();
        ArrayList<DoubleSidedCard> hand3 = new ArrayList<DoubleSidedCard>();
        ArrayList<DoubleSidedCard> hand4 = new ArrayList<DoubleSidedCard>();

        hand1.add(new DoubleSidedCard(new NormalCard(RED, ONE), new NormalCard(TEAL, TWO)));
        hand1.add(new DoubleSidedCard(new NormalCard(YELLOW, FIVE), new SkipEveryoneCard(PINK)));
        hand1.add(new DoubleSidedCard(new NormalCard(GREEN, TWO), new WildCard(Card.Side.DARK)));
        hand1.add(new DoubleSidedCard(new NormalCard(BLUE, ONE), new ReverseCard(PURPLE)));
        hand1.add(new DoubleSidedCard(new DrawOneCard(RED), new FlipCard(TEAL)));
        hand1.add(new DoubleSidedCard(new SkipCard(GREEN), new NormalCard(PINK, THREE)));
        hand1.add(new DoubleSidedCard(new ReverseCard(RED), new NormalCard(ORANGE, ONE)));

        hand2.add(new DoubleSidedCard(new NormalCard(YELLOW, NINE), new NormalCard(TEAL, ONE)));
        hand2.add(new DoubleSidedCard(new NormalCard(RED, FIVE), new NormalCard(TEAL, TWO)));
        hand2.add(new DoubleSidedCard(new NormalCard(BLUE, EIGHT), new NormalCard(TEAL, THREE)));
        hand2.add(new DoubleSidedCard(new NormalCard(GREEN, SIX), new NormalCard(TEAL, FOUR)));
        hand2.add(new DoubleSidedCard(new DrawOneCard(BLUE), new NormalCard(TEAL, FIVE)));
        hand2.add(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(TEAL, SIX)));
        hand2.add(new DoubleSidedCard(new WildDrawTwoCard(), new NormalCard(TEAL, SEVEN)));

        hand3.add(new DoubleSidedCard(new NormalCard(GREEN, FIVE), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(YELLOW, TWO), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(BLUE, THREE), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(RED, FOUR), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(RED, SIX), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(GREEN, EIGHT), new WildCard(Card.Side.DARK)));
        hand3.add(new DoubleSidedCard(new NormalCard(GREEN, ONE), new WildCard(Card.Side.DARK)));

        hand4.add(new DoubleSidedCard(new SkipCard(BLUE), new DrawFiveCard(PINK)));
        hand4.add(new DoubleSidedCard(new ReverseCard(YELLOW), new WildDrawColourCard()));
        hand4.add(new DoubleSidedCard(new ReverseCard(GREEN), new WildCard(Card.Side.DARK)));
        hand4.add(new DoubleSidedCard(new DrawOneCard(GREEN), new NormalCard(ORANGE, ONE)));
        hand4.add(new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new SkipEveryoneCard(TEAL)));
        hand4.add(new DoubleSidedCard(new WildDrawTwoCard(), new ReverseCard(PINK)));
        hand4.add(new DoubleSidedCard(new WildDrawTwoCard(), new FlipCard(PINK)));

        Player player1 = new Player("Player 1", hand1, false);
        Player player2 = new Player("Player 2", hand2, true);
        Player player3 = new Player("Player 3", hand3, true);
        Player player4 = new Player("Player 4", hand4, false);

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);

        game.shuffleDeck();
        game.dealCards(0);

        assertEquals(new ReverseCard(RED), game.getTopCard());

        // Serialize
        try {
            FileOutputStream file = new FileOutputStream("Serialized.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(game);

            // Ensure file exists
            File filename = new File("Serialized.txt");
            assertTrue(filename.exists());
            assertEquals(0, filename.length());

        } catch (IOException e) {
            assertEquals("No exception", "Exception found");
        }

        // Deserialize
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("Serialized.txt");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            importedGame = (Game)in.readObject();

            in.close();
            file.close();
        } catch(Exception ex) {
            assertEquals("No exception", "Exception found");
        }

        assertNotNull(importedGame);

        // Check that the players are the same
        assertEquals(4, importedGame.getPlayers().size());
        assertEquals(game.getPlayers().get(0), importedGame.getPlayers().get(0));
        assertEquals(game.getPlayers().get(1), importedGame.getPlayers().get(0));
        assertEquals(game.getPlayers().get(2), importedGame.getPlayers().get(0));
        assertEquals(game.getPlayers().get(3), importedGame.getPlayers().get(0));

        // Check the played cards and the deck
        assertEquals(game.getDeck().size(), importedGame.getDeck().size());
        assertEquals(game.getTopCard(), importedGame.getTopCard());
        assertEquals(game.getCurrentColour(), importedGame.getCurrentColour());

        // Check for the correct next player
        assertEquals(game.nextPlayer(), importedGame.nextPlayer());

        // Check various game state variables
        assertEquals(game.hasWonGame(), importedGame.hasWonGame());
        assertEquals(game.isRunning(), importedGame.isRunning());
        assertEquals(game.isDark(), importedGame.isDark());
    }
}
