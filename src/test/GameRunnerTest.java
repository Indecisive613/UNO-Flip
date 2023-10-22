package test;

import main.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Test class for GameRunner
 *
 * @author Anand Balaram
 */

public class GameRunnerTest {

    @Test
    public void testCreateDeck(){
        assertTrue(main.GameRunner.createDeck() instanceof Stack);
        Stack<Card> deck = main.GameRunner.createDeck();
        assertEquals(104, deck.size());

        Stack<Card> testDeck = new Stack<Card>();

        for(Card.Colour colour : Card.nonWildColours){
            for(Card.Symbol symbol : Card.nonWildSymbols) {
                Card card = new Card(colour, symbol);
                testDeck.push(card);
                testDeck.push(card);
            }
        }

        Card.Colour colour = Card.Colour.WILD;

        for(Card.Symbol symbol : Card.wildSymbols) {
            Card card = new Card(colour, symbol);
            for(int i = 0; i < 4; i++){
                testDeck.push(card);
            }
        }

        while(!deck.isEmpty()) {
            assertEquals(testDeck.pop(), deck.pop());
        }
        assertTrue(testDeck.isEmpty());
    }
}
