package test;

import main.models.cards.*;
import main.models.Player;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Player class
 *
 * @author Jackie Smolkin-Lerner, Fiona Cheng
 */
public class PlayerTest {

    @Test
    public void testCreatePlayer() {
        // Create a Non-AI player
        ArrayList<DoubleSidedCard> hand1 = new ArrayList<>();
        Player p1 = new Player("Real player", hand1, false);

        assertEquals("Real player", p1.getName());
        assertEquals(0, p1.getScore());
        assertEquals(hand1, p1.getHand());
        assertEquals(false, p1.getIsAI());

        // Create an AI player
        ArrayList<DoubleSidedCard> hand2 = new ArrayList<>();
        Player p2 = new Player("Artificial Intelligence", hand2, true);

        assertEquals("Artificial Intelligence", p2.getName());
        assertEquals(0, p2.getScore());
        assertEquals(hand2, p2.getHand());
        assertEquals(true, p2.getIsAI());
    }

    @Test
    public void testDealCard() {
        ArrayList<DoubleSidedCard> hand1 = new ArrayList<>();
        Player p1 = new Player("Real player", hand1, false);
        DoubleSidedCard card1 = new DoubleSidedCard(new NormalCard(Card.Colour.RED, Card.Symbol.TWO), new ReverseCard(Card.Colour.TEAL));
        DoubleSidedCard card2 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.ONE), new DrawFiveCard(Card.Colour.PINK));

        assertEquals(0, p1.getHand().size());
        p1.dealCard(card1);
        assertEquals(1, p1.getHand().size());
        assertEquals(card1, p1.getHand().get(0));
        p1.dealCard(card2);
        assertEquals(2, p1.getHand().size());
        assertEquals(card2, p1.getHand().get(1));
    }

    @Test
    public void testPlayCard() {
        ArrayList<DoubleSidedCard> hand= new ArrayList<>();
        DoubleSidedCard card1 = new DoubleSidedCard(new NormalCard(Card.Colour.RED, Card.Symbol.TWO), new ReverseCard(Card.Colour.TEAL));
        DoubleSidedCard card2 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.ONE), new DrawFiveCard(Card.Colour.PINK));
        DoubleSidedCard card3 = new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(Card.Colour.TEAL, Card.Symbol.THREE));
        DoubleSidedCard card4 = new DoubleSidedCard(new NormalCard(Card.Colour.RED, Card.Symbol.SEVEN), new NormalCard(Card.Colour.PURPLE, Card.Symbol.EIGHT));

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);

        Player p1 = new Player("Real player", hand, false);

        // Test with invalid indices
        assertThrows(IllegalArgumentException.class, () -> {
            p1.playCard(4);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            p1.playCard(-2);
        });

        // Test with multiple cards
        assertEquals(4, p1.getHand().size());
        assertEquals(card1, p1.playCard(0));

        assertEquals(3, p1.getHand().size());
        assertEquals(card4, p1.playCard(2));

        assertEquals(2, p1.getHand().size());
        assertEquals(card3, p1.playCard(1));

        assertEquals(1, p1.getHand().size());
        assertEquals(card2, p1.playCard(0));

        assertEquals(0, p1.getHand().size());

        // Test playing card from empty hand
        assertThrows(IllegalArgumentException.class, () -> {
            p1.playCard(0);
        });
    }

    @Test
    public void testIncrementScore() {
        ArrayList<DoubleSidedCard> hand = new ArrayList<>();
        Player p = new Player("Test Name", hand, false);

        p.incrementScore(100);
        assertEquals(100, p.getScore());

        p.incrementScore(2009);
        assertEquals(100 + 2009, p.getScore());
    }

    @Test
    public void testClearHand() {
        ArrayList<DoubleSidedCard> hand= new ArrayList<>();
        DoubleSidedCard card1 = new DoubleSidedCard(new NormalCard(Card.Colour.RED, Card.Symbol.TWO), new ReverseCard(Card.Colour.TEAL));
        DoubleSidedCard card2 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.ONE), new DrawFiveCard(Card.Colour.PINK));
        DoubleSidedCard card3 = new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(Card.Colour.TEAL, Card.Symbol.THREE));
        DoubleSidedCard card4 = new DoubleSidedCard(new NormalCard(Card.Colour.RED, Card.Symbol.SEVEN), new NormalCard(Card.Colour.PURPLE, Card.Symbol.EIGHT));

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);

        Player p1 = new Player("Real player", hand, false);

        assertEquals(4, p1.getHand().size());
        p1.clearHand();
        assertEquals(0, p1.getHand().size());
    }

    @Test
    public void testGetActiveHand() {
        ArrayList<DoubleSidedCard> doubleSidedHand = new ArrayList<>();
        DoubleSidedCard doubleSidedCard1 = new DoubleSidedCard(new NormalCard(Card.Colour.RED, Card.Symbol.TWO), new ReverseCard(Card.Colour.TEAL));
        DoubleSidedCard doubleSidedCard2 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.ONE), new DrawFiveCard(Card.Colour.PINK));
        DoubleSidedCard doubleSidedCard3 = new DoubleSidedCard(new WildCard(Card.Side.LIGHT), new NormalCard(Card.Colour.TEAL, Card.Symbol.THREE));

        doubleSidedHand.add(doubleSidedCard1);
        doubleSidedHand.add(doubleSidedCard2);
        doubleSidedHand.add(doubleSidedCard3);

        ArrayList<DoubleSidedCard> hand = new ArrayList<>();
        Player p1 = new Player("P1", doubleSidedHand, false);

        // Test getting light side hand
        ArrayList<Card> lightHand = new ArrayList<>();
        Card lightCard1 = new NormalCard(Card.Colour.RED, Card.Symbol.TWO);
        Card lightCard2 = new NormalCard(Card.Colour.GREEN, Card.Symbol.ONE);
        Card lightCard3 = new WildCard(Card.Side.LIGHT);

        lightHand.add(lightCard1);
        lightHand.add(lightCard2);
        lightHand.add(lightCard3);

        for(int i = 0; i < 3; i++){
            assertEquals(lightHand.get(i), p1.getActiveHand().get(i));
        }

        // Test getting dark side hand
        ArrayList<Card> darkHand = new ArrayList<>();
        Card darkCard1 = new ReverseCard(Card.Colour.TEAL);
        Card darkCard2 = new DrawFiveCard(Card.Colour.PINK);
        Card darkCard3 = new NormalCard(Card.Colour.TEAL, Card.Symbol.THREE);

        darkHand.add(darkCard1);
        darkHand.add(darkCard2);
        darkHand.add(darkCard3);

        DoubleSidedCard.flip();

        for(int i = 0; i < 3; i++){
            assertEquals(darkHand.get(i), p1.getActiveHand().get(i));
        }
    }
}
