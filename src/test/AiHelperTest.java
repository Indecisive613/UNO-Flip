package test;

//import jdk.swing.interop.LightweightContentWrapper;

import main.models.AiHelper;
import main.models.Game;
import main.models.GameRunner;
import main.models.Player;
import main.models.cards.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Ai Helper class
 *
 * @author Fiona Cheng
 */
public class AiHelperTest {
    Game game;
    Player player;
    @Test
    public void testCreateAiHelper() {
        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(GameRunner.createDoubleSidedDeck());
        game.addPlayer(player);
        game.dealCards(Game.STARTING_HAND_SIZE);

        // Test creating AI helper with light side hand
        ArrayList<Card> lightHand = player.getActiveHand();
        AiHelper helper1 = new AiHelper(game, player.getActiveHand());
        assertEquals(game, helper1.getCurrentGame());
        assertEquals(lightHand, helper1.getCurrentHand());

        // Test creating AI helper with dark side hand
        game.flip();

        ArrayList<Card> darkHand = player.getActiveHand();
        AiHelper helper2 = new AiHelper(game, player.getActiveHand());
        assertEquals(game, helper2.getCurrentGame());
        assertEquals(darkHand, helper2.getCurrentHand());

        game.flip();//Flip back to light side to be safe
    }

    @Test
    public void testGetAiActionNoWilds(){
        Stack<DoubleSidedCard> customDeck = new Stack<DoubleSidedCard>();
        DoubleSidedCard card1 = new DoubleSidedCard(new ReverseCard(Card.Colour.BLUE), new FlipCard(Card.Colour.TEAL)); //draw pile
        DoubleSidedCard card2 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.SEVEN), new NormalCard(Card.Colour.PURPLE, Card.Symbol.TWO)); //top card
        DoubleSidedCard card3 = new DoubleSidedCard(new SkipCard(Card.Colour.RED), new NormalCard(Card.Colour.PURPLE, Card.Symbol.THREE)); // Index 2 of player hand
        DoubleSidedCard card4 = new DoubleSidedCard(new NormalCard(Card.Colour.BLUE, Card.Symbol.NINE), new SkipEveryoneCard(Card.Colour.ORANGE));
        DoubleSidedCard card5 = new DoubleSidedCard(new ReverseCard(Card.Colour.RED), new SkipEveryoneCard(Card.Colour.PURPLE)); // Index 0 of player hand

        customDeck.push(card1);
        customDeck.push(card2);
        customDeck.push(card3);
        customDeck.push(card4);
        customDeck.push(card5);

        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(customDeck);
        game.addPlayer(player);
        game.dealCards(3);

        // Test not being able to play anything
        AiHelper helper1 = new AiHelper(game, player.getActiveHand());
        assertEquals(-1, helper1.getAiAction());

        game.flip();

        // Test having two possible options (should pick higher point value one)
        AiHelper helper2 = new AiHelper(game, player.getActiveHand());
        assertEquals(0, helper2.getAiAction());

        game.flip();//Flip back to light side to be safe
    }

    @Test
    public void testGetAiActionWilds(){
        Stack<DoubleSidedCard> customDeck = new Stack<DoubleSidedCard>();
        DoubleSidedCard card1 = new DoubleSidedCard(new ReverseCard(Card.Colour.BLUE), new FlipCard(Card.Colour.TEAL)); //draw pile
        DoubleSidedCard card2 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.SEVEN), new NormalCard(Card.Colour.ORANGE, Card.Symbol.TWO)); //top card
        DoubleSidedCard card3 = new DoubleSidedCard(new SkipCard(Card.Colour.RED), new NormalCard(Card.Colour.PURPLE, Card.Symbol.TWO)); // Index 2 of player hand
        DoubleSidedCard card4 = new DoubleSidedCard(new NormalCard(Card.Colour.BLUE, Card.Symbol.NINE), new WildDrawColourCard());
        DoubleSidedCard card5 = new DoubleSidedCard(new WildDrawTwoCard(), new SkipEveryoneCard(Card.Colour.PINK)); // Index 0 of player hand

        customDeck.push(card1);
        customDeck.push(card2);
        customDeck.push(card3);
        customDeck.push(card4);
        customDeck.push(card5);

        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(customDeck);
        game.addPlayer(player);
        game.dealCards(3);

        // Test only being able to play wild
        AiHelper helper1 = new AiHelper(game, player.getActiveHand());
        assertEquals(0, helper1.getAiAction());

        game.flip();

        // Test having two possible options (should pick the non-wild)
        AiHelper helper2 = new AiHelper(game, player.getActiveHand());
        assertEquals(2, helper2.getAiAction());

        game.flip();//Flip back to light side to be safe
    }

    @Test
    public void testGetMostCommonColourNoWilds(){
        Stack<DoubleSidedCard> customDeck = new Stack<DoubleSidedCard>();
        DoubleSidedCard card1 = new DoubleSidedCard(new ReverseCard(Card.Colour.BLUE), new FlipCard(Card.Colour.TEAL)); //draw pile
        DoubleSidedCard card2 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.SEVEN), new NormalCard(Card.Colour.ORANGE, Card.Symbol.TWO)); //top card
        DoubleSidedCard card3 = new DoubleSidedCard(new SkipCard(Card.Colour.RED), new NormalCard(Card.Colour.PURPLE, Card.Symbol.TWO)); // Index 2 of player hand
        DoubleSidedCard card4 = new DoubleSidedCard(new NormalCard(Card.Colour.BLUE, Card.Symbol.NINE), new DrawFiveCard(Card.Colour.TEAL));
        DoubleSidedCard card5 = new DoubleSidedCard(new NormalCard(Card.Colour.BLUE, Card.Symbol.ONE), new SkipEveryoneCard(Card.Colour.PINK)); // Index 0 of player hand

        customDeck.push(card1);
        customDeck.push(card2);
        customDeck.push(card3);
        customDeck.push(card4);
        customDeck.push(card5);

        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(customDeck);
        game.addPlayer(player);
        game.dealCards(3);

        // Want the most common color the colour that appears most
        AiHelper helper1 = new AiHelper(game, player.getActiveHand());
        assertEquals(Card.Colour.BLUE, helper1.getMostCommonColour());

        // Want the most common color to be one of the tied colours
        game.flip();
        AiHelper helper2 = new AiHelper(game, player.getActiveHand());

        ArrayList<Card.Colour> possibleColours = new ArrayList<>();
        possibleColours.add(Card.Colour.PURPLE);
        possibleColours.add(Card.Colour.PINK);
        possibleColours.add(Card.Colour.TEAL);

        assertTrue(possibleColours.contains(helper2.getMostCommonColour()));

        game.flip(); // Flip back to light side to be safe
    }

    @Test
    public void testGetMostCommonColourWilds(){
        Stack<DoubleSidedCard> customDeck = new Stack<DoubleSidedCard>();
        DoubleSidedCard card1 = new DoubleSidedCard(new ReverseCard(Card.Colour.BLUE), new FlipCard(Card.Colour.TEAL)); //draw pile
        DoubleSidedCard card2 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.SEVEN), new NormalCard(Card.Colour.ORANGE, Card.Symbol.TWO)); //top card
        DoubleSidedCard card3 = new DoubleSidedCard(new SkipCard(Card.Colour.RED), new WildDrawColourCard()); // Index 2 of player hand
        DoubleSidedCard card4 = new DoubleSidedCard(new WildDrawTwoCard(), new WildDrawColourCard());
        DoubleSidedCard card5 = new DoubleSidedCard(new WildDrawTwoCard(), new WildDrawColourCard()); // Index 0 of player hand

        customDeck.push(card1);
        customDeck.push(card2);
        customDeck.push(card3);
        customDeck.push(card4);
        customDeck.push(card5);

        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(customDeck);
        game.addPlayer(player);
        game.dealCards(3);

        // Want to test when the majority is WILD
        AiHelper helper1 = new AiHelper(game, player.getActiveHand());
        assertEquals(Card.Colour.RED, helper1.getMostCommonColour());

        // Want to test when there are only WILD (pick anything, except WILD)
        game.flip();
        AiHelper helper2 = new AiHelper(game, player.getActiveHand());

        ArrayList<Card.Colour> possibleColours = new ArrayList<>();
        possibleColours.add(Card.Colour.PURPLE);
        possibleColours.add(Card.Colour.PINK);
        possibleColours.add(Card.Colour.TEAL);
        possibleColours.add(Card.Colour.ORANGE);

        assertTrue(possibleColours.contains(helper2.getMostCommonColour()));

        game.flip(); // Flip back to light side to be safe
    }
}