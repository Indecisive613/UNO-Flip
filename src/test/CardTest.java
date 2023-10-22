package test;

import main.Card;
import static main.Card.Symbol.*;
import static main.Card.Colour.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Card class
 *
 * @author Jackie Smolkin-Lerner
 */
public class CardTest {

    @Test
    public void testCreateCard() {
        Card c = new Card(RED, ONE);
        assertEquals(1, c.getPointValue());
        assertEquals(RED, c.getColour());
        assertEquals(ONE, c.getSymbol());
    }

    @Test
    public void testCreateWildCard() {
        Card c = new Card(Card.Colour.WILD, Card.Symbol.WILD);
        assertEquals(40, c.getPointValue());
        assertEquals(Card.Colour.WILD, c.getColour());
        assertEquals(Card.Symbol.WILD, c.getSymbol());
    }

    @Test
    public void testCreateSkip() {
        Card c = new Card(GREEN, SKIP);
        assertEquals(20, c.getPointValue());
        assertEquals(GREEN, c.getColour());
        assertEquals(SKIP, c.getSymbol());
    }

    @Test
    public void testCreateReverse() {
        Card c = new Card(BLUE, REVERSE);
        assertEquals(20, c.getPointValue());
        assertEquals(BLUE, c.getColour());
        assertEquals(REVERSE, c.getSymbol());
    }

    @Test
    public void testCreateDrawOne() {
        Card c = new Card(BLUE, DRAW_ONE);
        assertEquals(10, c.getPointValue());
        assertEquals(BLUE, c.getColour());
        assertEquals(DRAW_ONE, c.getSymbol());
    }

    @Test
    public void testCardPointValues() {
        assertEquals(1, new Card(RED, ONE).getPointValue());
        assertEquals(2, new Card(RED, TWO).getPointValue());
        assertEquals(3, new Card(RED, THREE).getPointValue());
        assertEquals(4, new Card(RED, FOUR).getPointValue());
        assertEquals(5, new Card(RED, FIVE).getPointValue());
        assertEquals(6, new Card(RED, SIX).getPointValue());
        assertEquals(7, new Card(RED, SEVEN).getPointValue());
        assertEquals(8, new Card(RED, EIGHT).getPointValue());
        assertEquals(9, new Card(RED, NINE).getPointValue());
        assertEquals(10, new Card(RED, DRAW_ONE).getPointValue());
        assertEquals(20, new Card(RED, REVERSE).getPointValue());
        assertEquals(20, new Card(RED, SKIP).getPointValue());
        assertEquals(40, new Card(Card.Colour.WILD, Card.Symbol.WILD).getPointValue());
        assertEquals(50, new Card(Card.Colour.WILD, WILD_DRAW_TWO).getPointValue());
    }

    @Test
    public void testInvalidWildCombo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Card c = new Card(Card.Colour.WILD, ONE);
        });
    }

    @Test
    public void testInvalidColourCombo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Card c = new Card(RED, Card.Symbol.WILD);
        });
    }

}