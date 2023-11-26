package test;

import main.models.cards.Card;
import main.models.cards.DoubleSidedCard;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Test class for GameRunner
 *
 * @author Fiona Cheng
 */
public class GameRunnerTest {

    @Test
    public void testCreateDoubleSidedDeck(){
        Stack<DoubleSidedCard> deck = main.models.GameRunner.createDoubleSidedDeck();
        assertNotNull(deck);
        assertEquals(112, deck.size());

        // Test that each card has a dark and light side
        for(DoubleSidedCard card: deck){
            assertEquals(Card.Side.LIGHT, card.getLightSideCard().getSide());
            assertEquals(Card.Side.LIGHT, card.getActiveSide().getSide());
            assertEquals(Card.Side.DARK, card.getDarkSideCard().getSide());
        }
    }
}
