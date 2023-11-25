package test;

import jdk.swing.interop.LightweightContentWrapper;
import main.models.AiHelper;
import main.models.Game;
import main.models.GameRunner;
import main.models.cards.*;
import main.models.Player;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Ai Helper class
 *
 * @author Fiona Cheng
 */
public class AiHelperTest {
    Game game;
    Player player;
    @Before
    public void setup(){
        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(GameRunner.createDoubleSidedDeck());
        game.addPlayer(player);
        game.dealCards(Game.STARTING_HAND_SIZE);
    }
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
    }

    @Test
    public void testGetAiAction(){
        // If anyone is testing with smaller hand sizes, this part of the test will not run, because it counts on there being 7 cards dealt to the AI
        if(Game.STARTING_HAND_SIZE != 7){
            return;
        }

        //Test
        Stack<DoubleSidedCard> customDeck = new Stack<DoubleSidedCard>();
        DoubleSidedCard card1 = new DoubleSidedCard(new ReverseCard(Card.Colour.BLUE, Card.Side.LIGHT), new FlipCard(Card.Colour.TEAL, Card.Side.DARK)); //draw pile
        DoubleSidedCard card4 = new DoubleSidedCard(new NormalCard(Card.Colour.GREEN, Card.Symbol.SEVEN), new NormalCard(Card.Colour.PURPLE, Card.Symbol.TWO)); //top card
        DoubleSidedCard card3 = new DoubleSidedCard(new SkipCard(Card.Colour.RED), new NormalCard(Card.Colour.PURPLE, Card.Symbol.THREE)); // Index 6 of player hand
        DoubleSidedCard card2 = new DoubleSidedCard(new WildDrawTwoCard(), new NormalCard(Card.Colour.TEAL, Card.Symbol.THREE));
        DoubleSidedCard card5 = new DoubleSidedCard(new NormalCard(Card.Colour.YELLOW, Card.Symbol.NINE), new SkipEveryoneCard(Card.Colour.ORANGE));
        DoubleSidedCard card6 = new DoubleSidedCard(new ReverseCard(Card.Colour.YELLOW, Card.Side.LIGHT), new SkipEveryoneCard(Card.Colour.TEAL));
        DoubleSidedCard card7 = new DoubleSidedCard(new NormalCard(Card.Colour.YELLOW, Card.Symbol.NINE), new WildDrawColourCard());
        DoubleSidedCard card8 = new DoubleSidedCard(new NormalCard(Card.Colour.BLUE, Card.Symbol.NINE), new SkipEveryoneCard(Card.Colour.ORANGE));
        DoubleSidedCard card9 = new DoubleSidedCard(new ReverseCard(Card.Colour.RED, Card.Side.LIGHT), new SkipEveryoneCard(Card.Colour.PURPLE)); // Index 0 of player hand

        customDeck.push(card1);
        customDeck.push(card2);
        customDeck.push(card3);
        customDeck.push(card4);
        customDeck.push(card5);
        customDeck.push(card6);
        customDeck.push(card7);
        customDeck.push(card8);
        customDeck.push(card9);

        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(customDeck);
        game.addPlayer(player);
        game.dealCards(Game.STARTING_HAND_SIZE);

        System.out.println(game.getPlayers().get(0).getHand());
        System.out.println(game.getTopCard());
    }
}