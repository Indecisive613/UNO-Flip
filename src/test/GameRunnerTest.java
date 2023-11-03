package test;

import java.util.Stack;

import main.cards.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for GameRunner
 *
 * @author Anand Balaram, Fiona Cheng
 */

public class GameRunnerTest {

    @Test
    public void testCreateDeck(){
        assertTrue(main.GameRunner.createDeck() instanceof Stack);
        Stack<Card> deck = main.GameRunner.createDeck();
        assertEquals(104, deck.size());

        Stack<Card> testDeck = new Stack<Card>();

        for(Card.Colour colour : Card.nonWildColours){
            testDeck.push(new NormalCard(colour, Card.Symbol.ONE));
            testDeck.push(new NormalCard(colour, Card.Symbol.ONE));
            testDeck.push(new NormalCard(colour, Card.Symbol.TWO));
            testDeck.push(new NormalCard(colour, Card.Symbol.TWO));
            testDeck.push(new NormalCard(colour, Card.Symbol.THREE));
            testDeck.push(new NormalCard(colour, Card.Symbol.THREE));
            testDeck.push(new NormalCard(colour, Card.Symbol.FOUR));
            testDeck.push(new NormalCard(colour, Card.Symbol.FOUR));
            testDeck.push(new NormalCard(colour, Card.Symbol.FIVE));
            testDeck.push(new NormalCard(colour, Card.Symbol.FIVE));
            testDeck.push(new NormalCard(colour, Card.Symbol.SIX));
            testDeck.push(new NormalCard(colour, Card.Symbol.SIX));
            testDeck.push(new NormalCard(colour, Card.Symbol.SEVEN));
            testDeck.push(new NormalCard(colour, Card.Symbol.SEVEN));
            testDeck.push(new NormalCard(colour, Card.Symbol.EIGHT));
            testDeck.push(new NormalCard(colour, Card.Symbol.EIGHT));
            testDeck.push(new NormalCard(colour, Card.Symbol.NINE));
            testDeck.push(new NormalCard(colour, Card.Symbol.NINE));
            testDeck.push(new DrawOneCard(colour));
            testDeck.push(new DrawOneCard(colour));
            testDeck.push(new ReverseCard(colour));
            testDeck.push(new ReverseCard(colour));
            testDeck.push(new SkipCard(colour));
            testDeck.push(new SkipCard(colour));
        }

        for(int i = 0; i < 4; i++){
            testDeck.push(new WildCard());
            testDeck.push(new WildDrawTwoCard());
        }

        while(!deck.isEmpty()) {
            assertEquals(testDeck.pop(), deck.pop());
        }
        assertTrue(testDeck.isEmpty());
    }
}
