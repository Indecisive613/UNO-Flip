package main;

import java.util.ArrayList;

public class GameRunner {

    private final Game game;
    private final int playerCount;
    private final GameController controller;

    public GameRunner(Game game, int playerCount, GameController controller) {
        this.game = game;
        this.playerCount = playerCount;
        this.controller = controller;
    }

    public static void main(String[] args) {
        GameView view = new GameView();
        GameController controller = new GameController(view);
        Game game = new Game(new Deck());
        game.addView(view);
        view.setGame(game);


        int playerCount = controller.requestPlayerCount();

        GameRunner runner = new GameRunner(game, playerCount, controller);
        runner.startGame();
    }

    public void startGame() {
        for (int i = 0; i < playerCount; i++) {
            ArrayList<Card> hand = new ArrayList<>();
            String name = controller.requestPlayerName(i);
            Player player = new Player(name, hand);
            game.addPlayer(player);
        }
        game.shuffleDeck();
        game.deal();

        while (game.isRunning()) {
            game.nextTurn();
            Player currentPlayer = game.getCurrentPlayer();

            Card card = null;
            do {
                int action = controller.requestPlayerAction(currentPlayer.getHand().size());
                if (action == Game.DRAW_CARD_ACTION) {
                    // TODO: Draw a card
                    game.drawCard(currentPlayer);
                } else {
                    card = currentPlayer.getHand().get(action - 1);
                    game.playCard(currentPlayer.playCard(action - 1));
                }
            } while (!game.canPlay(card));
        }
    }
}
