package main;

public class GameRunner {

    private static int playerCount;

    public static void main(String[] args) {
        GameView view = new GameView();
        GameController controller = new GameController();
        playerCount = controller.requestPlayerCount();

        Game game = new Game();
        Deck deck = new Deck();

        for (int i = 0; i < playerCount; i++) {

        }

        view.setGame(game);

    }
}
