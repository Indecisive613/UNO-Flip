package test;

import main.models.AiHelper;
import main.models.Game;
import main.models.GameRunner;
import main.models.cards.*;
import main.models.Player;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Player class
 *
 * @author Jackie Smolkin-Lerner, Fiona Cheng
 */
public class AiHelperTest {
    Game game;
    Player player;
    @Before
    public void setup(){
        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(GameRunner.createDoubleSidedDeck());
        game.addPlayer(player);
        game.dealCards();
    }
    @Test
    public void testCreateAiHelper() {
        player = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(GameRunner.createDoubleSidedDeck());
        game.addPlayer(player);
        game.dealCards();

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
    }
}