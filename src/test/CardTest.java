package test;

import main.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    public CardTest() {

    }

    @BeforeEach
    protected void setUp() {
        Card card1 = new Card(Card.Colour.RED, Card.Symbol.ONE);
        Card card2 = new Card(Card.Colour.RED, Card.Symbol.WILD);
        Card card3 = new Card(Card.Colour.RED, Card.Symbol.DRAW_ONE);
        Card card4 = new Card(Card.Colour.GREEN, Card.Symbol.ONE);
        Card card5 = new Card(Card.Colour.GREEN, Card.Symbol.WILD_DRAW_TWO);
        Card card6 = new Card(Card.Colour.BLUE, Card.Symbol.FIVE);
        Card card7 = new Card(Card.Colour.BLUE, Card.Symbol.SKIP);
        Card card8 = new Card(Card.Colour.YELLOW, Card.Symbol.NINE);
        Card card9  = new Card(Card.Colour.YELLOW, Card.Symbol.REVERSE);
    }

    @Test
    public void testColour() {
        setUp();
        assertEquals(Card.Colour.RED, card1.getColour());
        assertEquals(Card.Colour.RED, card2.getColour());
        assertEquals(Card.Colour.RED, card3.getColour());
        assertEquals(Card.Colour.GREEN, card4.getColour());
        assertEquals(Card.Colour.GREEN, card5.getColour());
        assertEquals(Card.Colour.BLUE, card6.getColour());
        assertEquals(Card.Colour.BLUE, card7.getColour());
        assertEquals(Card.Colour.YELLOW, card8.getColour());
        assertEquals(Card.Colour.YELLOW, card9.getColour());
    }

    @Test
    public void testSymbol() {
        setUp();
        assertEquals(Card.Symbol.ONE, card1.getSymbol());
        assertEquals(Card.Symbol.WILD, card2.getSymbol());
        assertEquals(Card.Symbol.DRAW_ONE, card3.getSymbol());
        assertEquals(Card.Symbol.ONE, card4.getSymbol());
        assertEquals(Card.Symbol.WILD_DRAW_TWO, card5.getSymbol());
        assertEquals(Card.Symbol.FIVE, card6.getSymbol());
        assertEquals(Card.Symbol.SKIP, card7.getSymbol());
        assertEquals(Card.Symbol.NINE, card8.getSymbol());
        assertEquals(Card.Symbol.REVERSE, card9.getSymbol());
    }

    @Test
    public void testValidCard() {

    }

    @Test
    public void testExample() {
        assertEquals(4, 2 + 2);
    }

}