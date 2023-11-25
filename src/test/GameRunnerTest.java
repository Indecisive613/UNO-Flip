package test;

import java.util.Stack;

import main.models.cards.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for GameRunner
 *
 * @author Fiona Cheng
 */
public class GameRunnerTest {

    @Test
    public void testCreateDoubleSidedDeck(){
        assertTrue(main.models.GameRunner.createDoubleSidedDeck() instanceof Stack<DoubleSidedCard>);
        Stack<DoubleSidedCard> deck = main.models.GameRunner.createDoubleSidedDeck();
        assertEquals(112, deck.size());

        // Test that each card has a dark and light side
        for(DoubleSidedCard card: deck){
            assertEquals(Card.Side.LIGHT, card.getLightSideCard().getSide());
            assertEquals(Card.Side.LIGHT, card.getActiveSide().getSide());
            assertEquals(Card.Side.DARK, card.getDarkSideCard().getSide());
        }
    }
}
