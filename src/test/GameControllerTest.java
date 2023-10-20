package test;

import main.GameController;
import main.GameView;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {

    ByteArrayInputStream testInput;;
    GameView view;
    GameController controller;

    @Before
    public void setUp() {
        view = new GameView();
    }

    @Test
    public void testRequestPlayerCount() {
        testInput = new ByteArrayInputStream("2".getBytes());
        System.setIn(testInput);
        controller = new GameController(view);

        assertEquals(2, controller.requestPlayerCount());
    }

    @Test
    public void testPlayerActionEmptyHand() {
        testInput = new ByteArrayInputStream("0 1".getBytes());
        System.setIn(testInput);
        controller = new GameController(view);

        assertEquals(0, controller.requestPlayerAction(0));
    }

    @Test
    public void testPlayerName() {
        testInput = new ByteArrayInputStream("Fiona Jake Anand Jackie".getBytes());
        System.setIn(testInput);
        controller = new GameController(view);

        assertEquals("Fiona", controller.requestPlayerName(0));
        assertEquals("Jake", controller.requestPlayerName(1));
        assertEquals("Anand", controller.requestPlayerName(2));
        assertEquals("Jackie", controller.requestPlayerName(3));
    }
}
