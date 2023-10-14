package test;

import main.Card;
import static main.Card.Symbol.*;
import static main.Card.Colour.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    public void testCreateCard() {
        Card c = new Card(RED, ONE);
        assertEquals(1, c.getPointValue());
    }

    @Test
    public void testCreateWildCard() {
        Card c = new Card(Card.Colour.WILD, Card.Symbol.WILD);
        assertEquals(40, c.getPointValue());
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