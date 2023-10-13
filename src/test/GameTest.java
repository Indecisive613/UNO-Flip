package test;

import main.PlayedCards;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    Deck deck;
    Player player1, player2,  player3, player4;

    /*
    Sets up the test fixture.
    Called before every test case method.
     */
    @Before
    protected void setUp() {
        ArrayList<Card> hand1 = new ArrayList<Card>(); // create hand for player 1
        ArrayList<Card> hand2 = new ArrayList<Card>(); // create hand for player 2
        ArrayList<Card> hand3 = new ArrayList<Card>(); // create hand for player 3
        ArrayList<Card> hand4 = new ArrayList<Card>(); // create hand for player 4
        player1 = new Player("Homer", hand1, 0); // create player 1
        player2 = new Player("Marge", hand2, 0); // create player 2
        player3 = new Player("Bart", hand3, 0); // create player 3
        player4 = new Player("Lisa", hand4, 0); // create player 4

        ArrayList<Player> players = new ArrayList<Player>(); // create players for the game and add players
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        Stack<Card> playedCards = new Stack<Card>(); // create empty played cards pile

        game = new Gane(players, deck); // create the game with the players and the deck
    }
    // According to Google Doc:
    @Test
    public void testStartGame() {
        setUp();
        assertEquals(player1.getHand().size(), 7);
        assertEquals(player2.getHand().size(), 7);
        assertEquals(player3.getHand().size(), 7);
        assertEquals(player4.getHand().size(), 7);

        assertEquals(deck.size(), 80);
        assertEquals(playedCards.size(), 0);
    }
    @Test
    public void testPlaying() {
        setUp();
        assertEquals();
    }

    @Test
    public void testEndGame() {
        setUp();
        assertEquals();
    }

    // According to UML Diagram:
    @Test
    public void testGetValidCommands() {
        setUp();
        assertTrue(getValidCommands().contains("play"));
        //assertTrue(getValidCommands().contains("wild"));
        assertTrue(getValidCommands().contains("draw"));
    }

    @Test
    public void testReshuffle() {
        setUp();
        assertTrue(deck.shuffle());
    }


}