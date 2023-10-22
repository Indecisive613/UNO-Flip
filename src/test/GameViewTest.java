package test;

import main.Card;
import main.Game;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Tests for the GameView class
 *
 * @author Jackie Smolkin-Lerner
 */
public class GameViewTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        Stack<Card> deck = new Stack<>();
        game = new Game(deck);
        //current

        //game.addPlayer(n);
    }

    @Test
    public void setGame() {
    }

    @Test
    public void updateGetPlayerName() {
    }

    @Test
    public void updateInvalidInput() {
    }

    @Test
    public void updateNewTurn() {
    }

    @Test
    public void updatePlayCard() {
    }

    @Test
    public void updateDrawCard() {
    }

    @Test
    public void updateGetCard() {
    }

    @Test
    public void updateGetColour() {
    }

    @Test
    public void updateConfirmColour() {
    }
}