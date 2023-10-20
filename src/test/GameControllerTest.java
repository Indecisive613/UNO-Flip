package test;

import main.GameController;
import main.GameView;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class to test GameController
 *
 * @authors Fiona Jackie
 */

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
        testInput = new ByteArrayInputStream("-1 0 1 2 a * Hello 3 6 5 4".getBytes());
        System.setIn(testInput);
        controller = new GameController(view);

        assertEquals(2, controller.requestPlayerCount()); //corresponds to test values -1 0 1 2
        assertEquals(3, controller.requestPlayerCount()); //corresponds to test values a * Hello 3
        assertEquals(4, controller.requestPlayerCount()); //corresponds to test values 6 5 4

        System.out.println();
    }

    @Test
    public void testPlayerActionEmptyHand() {
        testInput = new ByteArrayInputStream("* Hi -1 1 0 -1 2 1 -1 2 0 8 7 6 5 4 3 2 1 0".getBytes());
        System.setIn(testInput);
        controller = new GameController(view);

        assertEquals(0, controller.requestPlayerAction(0)); //corresponds to test values * Hi -1 1 0
        assertEquals(1, controller.requestPlayerAction(1)); //corresponds to test values -1 2 1
        assertEquals(0, controller.requestPlayerAction(1)); //corresponds to test values -1 2 0

        assertEquals(7, controller.requestPlayerAction(7)); //corresponds to test values 8 7
        assertEquals(6, controller.requestPlayerAction(7)); //corresponds to test values 6
        assertEquals(5, controller.requestPlayerAction(7)); //corresponds to test values 5
        assertEquals(4, controller.requestPlayerAction(7)); //corresponds to test values 4
        assertEquals(3, controller.requestPlayerAction(7)); //corresponds to test values 3
        assertEquals(2, controller.requestPlayerAction(7)); //corresponds to test values 2
        assertEquals(1, controller.requestPlayerAction(7)); //corresponds to test values 1
        assertEquals(0, controller.requestPlayerAction(7)); //corresponds to test values 0

        System.out.println();
    }

    @Test
    public void testPlayerName() {
        testInput = new ByteArrayInputStream("Fiona Jake Anand Jackie 1 Jak \tAna\n **Jac**".getBytes());
        System.setIn(testInput);
        controller = new GameController(view);

        assertEquals("Fiona", controller.requestPlayerName(0));
        assertEquals("Jake", controller.requestPlayerName(1));
        assertEquals("Anand", controller.requestPlayerName(2));
        assertEquals("Jackie", controller.requestPlayerName(3));

        assertEquals("1", controller.requestPlayerName(1));
        assertEquals("Jak", controller.requestPlayerName(3));
        assertEquals("Ana", controller.requestPlayerName(0));
        assertEquals("**Jac**", controller.requestPlayerName(2));

        System.out.println();
    }
}
