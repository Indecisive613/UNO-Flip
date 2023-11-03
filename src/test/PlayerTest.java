package test;

import main.cards.Card;
import main.cards.NormalCard;
import main.Player;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Player class
 *
 * @author Jackie Smolkin-Lerner
 */
public class PlayerTest {

    @Test
    public void testCreatePlayer() {
        ArrayList<Card> hand = new ArrayList<>();
        Player p = new Player("Test Name", hand);

        assertEquals("Test Name", p.getName());
        assertEquals(0, p.getScore());
        assertEquals(hand, p.getHand());
    }

    @Test
    public void testDealCard() {
        ArrayList<Card> hand = new ArrayList<>();
        Player p = new Player("Test Name", hand);

        Card card = new NormalCard(Card.Colour.RED, Card.Symbol.ONE);
        p.dealCard(card);

        assertEquals(1, p.getHand().size());
        assertEquals(card, p.getHand().get(0));
    }

    @Test
    public void testPlayCard() {
        ArrayList<Card> hand= new ArrayList<>();
        Card card = new NormalCard(Card.Colour.RED, Card.Symbol.ONE);
        hand.add(card);
        Player p = new Player("Test Name", hand);

        assertEquals(card, p.playCard(0));
        assertEquals(0, p.getHand().size());
    }

    @Test
    public void testPlayMultipleCard() {
        ArrayList<Card> hand= new ArrayList<>();
        Card c1 = new NormalCard(Card.Colour.RED, Card.Symbol.ONE);
        Card c2 = new NormalCard(Card.Colour.BLUE, Card.Symbol.TWO);
        Card c3 = new NormalCard(Card.Colour.GREEN, Card.Symbol.THREE);
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        Player p = new Player("Test Name", hand);

        System.out.println(p.getHand().size());
        assertEquals(c2, p.playCard(1));
        System.out.println(p.getHand().size());
        assertThrows(IllegalArgumentException.class, () -> {
            p.playCard(2);
        });
        assertEquals(c1, p.playCard(0));
        assertEquals(c3, p.playCard(0));
        assertEquals(0, p.getHand().size());
    }

    @Test
    public void testInvalidPlayCard() {
        ArrayList<Card> hand= new ArrayList<>();
        Card card = new NormalCard(Card.Colour.RED, Card.Symbol.ONE);
        hand.add(card);
        Player p = new Player("Test Name", hand);
        assertThrows(IllegalArgumentException.class, () -> {
            p.playCard(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            p.playCard(2);
        });
    }

    @Test
    public void testIncrementScore() {
        ArrayList<Card> hand = new ArrayList<>();
        Player p = new Player("Test Name", hand);

        p.incrementScore(100);
        //assertEquals(100, p.getScore());

        p.incrementScore(2009);
        //assertEquals(100 + 2009, p.getScore());
    }
}
