package test;

import static main.models.cards.Card.Symbol.*;
import static main.models.cards.Card.Colour.*;

import main.models.cards.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Card clas
 *
 * @author Jackie Smolkin-Lerner, Fiona Cheng
 */
public class CardTest {
    @Test
    public void testCreateNormalCardLight() {
        Card c = new NormalCard(RED, ONE);
        assertEquals(1, c.getPointValue());
        assertEquals(RED, c.getColour());
        assertEquals(ONE, c.getSymbol());
        assertEquals(Card.Side.LIGHT, c.getSide());
    }

    @Test
    public void testCreateNormalCardDark() {
        Card c = new NormalCard(TEAL, ONE);
        assertEquals(1, c.getPointValue());
        assertEquals(TEAL, c.getColour());
        assertEquals(ONE, c.getSymbol());
        assertEquals(Card.Side.DARK, c.getSide());
    }

    @Test
    public void testCreateWildCardLight() {
        Card c = new WildCard(Card.Side.LIGHT);
        assertEquals(40, c.getPointValue());
        assertEquals(Card.Colour.WILD, c.getColour());
        assertEquals(Card.Symbol.WILD, c.getSymbol());
        assertEquals(Card.Side.LIGHT, c.getSide());
    }

    @Test
    public void testCreateWildCardDark() {
        Card c = new WildCard(Card.Side.DARK);
        assertEquals(40, c.getPointValue());
        assertEquals(Card.Colour.WILD, c.getColour());
        assertEquals(Card.Symbol.WILD, c.getSymbol());
        assertEquals(Card.Side.DARK, c.getSide());
    }

    @Test
    public void testCreateWildDrawTwo() {
        Card c = new WildDrawTwoCard();
        assertEquals(50, c.getPointValue());
        assertEquals(Card.Colour.WILD, c.getColour());
        assertEquals(WILD_DRAW_TWO, c.getSymbol());
        assertEquals(Card.Side.LIGHT, c.getSide());
    }

    @Test
    public void testCreateWildDrawColour() {
        Card c = new WildDrawColourCard();
        assertEquals(60, c.getPointValue());
        assertEquals(Card.Colour.WILD, c.getColour());
        assertEquals(WILD_DRAW_COLOUR, c.getSymbol());
        assertEquals(Card.Side.DARK, c.getSide());
    }

    @Test
    public void testCreateSkip() {
        Card c = new SkipCard(GREEN);
        assertEquals(20, c.getPointValue());
        assertEquals(GREEN, c.getColour());
        assertEquals(SKIP, c.getSymbol());
        assertEquals(Card.Side.LIGHT, c.getSide());
    }

    @Test
    public void testSkipEveryone() {
        Card c = new SkipEveryoneCard(PURPLE);
        assertEquals(30, c.getPointValue());
        assertEquals(PURPLE, c.getColour());
        assertEquals(SKIP_EVERYONE, c.getSymbol());
        assertEquals(Card.Side.DARK, c.getSide());
    }

    @Test
    public void testCreateReverseLight() {
        Card c = new ReverseCard(BLUE);
        assertEquals(20, c.getPointValue());
        assertEquals(BLUE, c.getColour());
        assertEquals(REVERSE, c.getSymbol());
        assertEquals(Card.Side.LIGHT, c.getSide());
    }

    @Test
    public void testCreateReverseDark() {
        Card c = new ReverseCard(ORANGE);
        assertEquals(20, c.getPointValue());
        assertEquals(ORANGE, c.getColour());
        assertEquals(REVERSE, c.getSymbol());
        assertEquals(Card.Side.DARK, c.getSide());
    }

    @Test
    public void testCreateDrawOne() {
        Card c = new DrawOneCard(BLUE);
        assertEquals(10, c.getPointValue());
        assertEquals(BLUE, c.getColour());
        assertEquals(DRAW_ONE, c.getSymbol());
        assertEquals(Card.Side.LIGHT, c.getSide());
    }

    @Test
    public void testCreateDrawFive() {
        Card c = new DrawFiveCard(PINK);
        assertEquals(20, c.getPointValue());
        assertEquals(PINK, c.getColour());
        assertEquals(DRAW_FIVE, c.getSymbol());
        assertEquals(Card.Side.DARK, c.getSide());
    }

    @Test
    public void testCreateLightFlip() {
        Card c = new FlipCard(RED);
        assertEquals(20, c.getPointValue());
        assertEquals(RED, c.getColour());
        assertEquals(FLIP, c.getSymbol());
        assertEquals(Card.Side.LIGHT, c.getSide());
    }

    @Test
    public void testCreateDarkFlip() {
        Card c = new FlipCard(PINK);
        assertEquals(20, c.getPointValue());
        assertEquals(PINK, c.getColour());
        assertEquals(FLIP, c.getSymbol());
        assertEquals(Card.Side.DARK, c.getSide());
    }

    @Test
    public void testCardPointValuesLight() {
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
        assertEquals(20, new FlipCard(RED).getPointValue());
        assertEquals(40, new WildCard(Card.Side.LIGHT).getPointValue());
        assertEquals(50, new WildDrawTwoCard().getPointValue());
    }

    @Test
    public void testCardPointValuesDark() {
        assertEquals(1, new NormalCard(TEAL, ONE).getPointValue());
        assertEquals(2, new NormalCard(TEAL, TWO).getPointValue());
        assertEquals(3, new NormalCard(TEAL, THREE).getPointValue());
        assertEquals(4, new NormalCard(TEAL, FOUR).getPointValue());
        assertEquals(5, new NormalCard(TEAL, FIVE).getPointValue());
        assertEquals(6, new NormalCard(TEAL, SIX).getPointValue());
        assertEquals(7, new NormalCard(TEAL, SEVEN).getPointValue());
        assertEquals(8, new NormalCard(TEAL, EIGHT).getPointValue());
        assertEquals(9, new NormalCard(TEAL, NINE).getPointValue());
        assertEquals(20, new DrawFiveCard(TEAL).getPointValue());
        assertEquals(20, new ReverseCard(TEAL).getPointValue());
        assertEquals(20, new FlipCard(TEAL).getPointValue());
        assertEquals(30, new SkipEveryoneCard(TEAL).getPointValue());
        assertEquals(40, new WildCard(Card.Side.DARK).getPointValue());
        assertEquals(60, new WildDrawColourCard().getPointValue());
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

    @Test
    public void testInvalidSideCombo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Card c = new SkipEveryoneCard(RED);
        });
    }

}