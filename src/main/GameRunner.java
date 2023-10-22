package main;

import java.util.ArrayList;
import java.util.Stack;

/**
 * A class to initialize and run an UNO game.
 *
 * @authors Fiona Cheng, Anand Balaram
 */
public class GameRunner {

    private final Game game;
    private final GameController controller;

    /**
     * Create a GameRunner with a specific Game and GameController
     *
     * @param game The Game to run
     * @param controller The GameController to get user input with
     */
    public GameRunner(Game game, GameController controller) {
        this.game = game;
        this.controller = controller;
    }

    /**
     * The Main method to create a new UNO game
     *
     * @param args Main args - not used
     */
    public static void main(String[] args) {
        GameView view = new GameView();
        GameController controller = new GameController(view);
        Game game = new Game(createDeck());
        game.addView(view);
        view.setGame(game);

        GameRunner runner = new GameRunner(game, controller);
        while(!game.hasWonGame()){
            runner.startGame();
        }
        System.out.println(game.getCurrentPlayer().getName() + " has won!");
        System.out.println(game.getCurrentPlayer().getName() + " scored " + game.getCurrentScore() + " points this round.");
    }

    /**
     * Start the UNO game
     */
    public void startGame() {
        int playerCount = controller.requestPlayerCount();
        for (int i = 0; i < playerCount; i++) {
            ArrayList<Card> hand = new ArrayList<>();
            String name = controller.requestPlayerName(i);
            Player player = new Player(name, hand);
            game.addPlayer(player);
        }
        game.shuffleDeck();
        game.dealCards();

        while (game.isRunning()) {
            game.advanceTurn();
            Player currentPlayer = game.getCurrentPlayer();

            Card card = null;
            while (true) {
                int action = controller.requestPlayerAction(currentPlayer.getHand().size());

                if (action == Game.DRAW_CARD_ACTION) {
                    game.drawCard(currentPlayer);
                    break;
                }

                card = currentPlayer.getHand().get(action - 1);
                if (game.canPlayCard(card)) {
                    boolean wild = game.playCard(currentPlayer.playCard(action - 1));
                    if (wild) {
                        game.setCurrentColour(controller.requestColour());
                    }
                    break;
                }
                System.out.println("Card doesn't match the top card. Try again.");
            }

            if (currentPlayer.getHand().isEmpty()) {
                // game.announceWinner();
                System.out.println(currentPlayer.getName() + " has won!");
                System.out.println(currentPlayer.getName() + " scored " + game.getCurrentScore() + " points this round.");
                game.assignScore();
            }
        }
    }

    /**
     * Create and return a deck of UNO cards
     *
     * @return A deck of UNO cards
     */
    public static Stack<Card> createDeck() {
        Stack<Card> cards = new Stack<Card>();

        for(Card.Colour colour : Card.nonWildColours){
            for(Card.Symbol symbol : Card.nonWildSymbols) {
                Card card1, card2;
                try {
                    card1 = new Card(colour, symbol);
                    card2 = new Card(colour, symbol);
                } catch(IllegalArgumentException e){
                    System.out.println(e);
                    continue;
                }
                cards.push(card1);
                cards.push(card2);
            }
        }

        for(Card.Colour colour : Card.wildColours){
            for(Card.Symbol symbol : Card.wildSymbols) {
                Card card1, card2, card3, card4;
                try {
                    card1 = new Card(colour, symbol);
                    card2 = new Card(colour, symbol);
                    card3 = new Card(colour, symbol);
                    card4 = new Card(colour, symbol);
                } catch(IllegalArgumentException e){
                    System.out.println(e);
                    continue;
                }
                cards.push(card1);
                cards.push(card2);
                cards.push(card3);
                cards.push(card4);
            }
        }
        return cards;
    }
}