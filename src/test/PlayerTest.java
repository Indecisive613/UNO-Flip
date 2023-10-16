package test;

import main.Card;
import main.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    public PlayerTest() {

    }

    @BeforeEach
    protected void setUp() {
        Player player1 = new Player("Buffy", );
        Player player2 = new Player("Xander", );
        Player player3 = new Player("Willow", );
        Player player4 = new Player("Giles", );
    }

    @Test
    public void testGetName() {

    }

    @Test
    public void testGetScore() {

    }

    @Test
    public void testGetHand() {

    }

}
