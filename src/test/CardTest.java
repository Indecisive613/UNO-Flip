/*package test;

import static main.models.cards.Card.Symbol.*;
import static main.models.cards.Card.Colour.*;

import main.models.cards.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Card class //TODO Bring back tests, perhaps add some to test dark side (need to wait until logic in Game is implemented)
 *
 * @author Jackie Smolkin-Lerner
 *
public class CardTest {
    @Test
    public void testCreateCard() {
        Card c = new NormalCard(RED, ONE);
        assertEquals(1, c.getPointValue());
        assertEquals(RED, c.getColour());
        assertEquals(ONE, c.getSymbol());
    }

    @Test
    public void testCreateWildCard() {
        Card c = new WildCard();
        assertEquals(40, c.getPointValue());
        assertEquals(Card.Colour.WILD, c.getColour());
        assertEquals(Card.Symbol.WILD, c.getSymbol());
    }

    @Test
    public void testCreateSkip() {
        Card c = new SkipCard(GREEN);
        assertEquals(20, c.getPointValue());
        assertEquals(GREEN, c.getColour());
        assertEquals(SKIP, c.getSymbol());
    }

    @Test
    public void testCreateReverse() {
        Card c = new ReverseCard(BLUE);
        assertEquals(20, c.getPointValue());
        assertEquals(BLUE, c.getColour());
        assertEquals(REVERSE, c.getSymbol());
    }

    @Test
    public void testCreateDrawOne() {
        Card c = new DrawOneCard(BLUE);
        assertEquals(10, c.getPointValue());
        assertEquals(BLUE, c.getColour());
        assertEquals(DRAW_ONE, c.getSymbol());
    }

    @Test
    public void testCardPointValues() {
        assertEquals(1, new NormalCard(RED, ONE).getPointValue());
        assertEquals(2, new NormalCard(RED, TWO).getPointValue());
        assertEquals(3, new NormalCard(RED, THREE).getPointValue());
        assertEquals(4, new NormalCard(RED, FOUR).getPointValue());
        assertEquals(5, new NormalCard(RED, FIVE).getPointValue());
        assertEquals(6, new NormalCard(RED, SIX).getPointValue());
        assertEquals(7, new NormalCard(RED, SEVEN).getPointValue());
        assertEquals(8, new NormalCard(RED, EIGHT).getPointValue());
        assertEquals(9, new NormalCard(RED, NINE).getPointValue());
        assertEquals(10, new DrawOneCard(RED).getPointValue());
        assertEquals(20, new ReverseCard(RED).getPointValue());
        assertEquals(20, new SkipCard(RED).getPointValue());
        assertEquals(40, new WildCard().getPointValue());
        assertEquals(50, new WildDrawTwoCard().getPointValue());
    }

    @Test
    public void testInvalidWildCombo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Card c = new NormalCard(Card.Colour.WILD, ONE);
        });
    }

    @Test
    public void testInvalidColourCombo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Card c = new NormalCard(RED, Card.Symbol.WILD);
        });
    }

}*/