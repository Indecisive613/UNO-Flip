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
        Player p1 = new Player("Player", new ArrayList<DoubleSidedCard>(), true);
        game = new Game(GameRunner.createDoubleSidedDeck());
        game.addPlayer(p1);
        game.dealCards();
    }
    @Test
    public void testCreateAiHelper() {
        // Create a Non-AI player with light cards
        ArrayList<Card> lightHand = player.getActiveHand();
        AiHelper helper1 = new AiHelper(game, game.getTopCard(), player.getActiveHand(), game.getCurrentColour(), game.getCurrentSymbol());


    }

    //test getAiAction
}