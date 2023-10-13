package main;

public class GameView {

    private Game game;

    public GameView(Game game) {
        this.game = game;
    }

    private void printStartOfTurn() {
        System.out.printf("%s's turn.\n", "Player 1");
        System.out.printf("Current side: %s.\n", "Light");
    }
    private void printPlayerCards() {
        System.out.println("Your cards:");
    }

    private void printPlayerSelect() {
        System.out.println("Enter number of players: ");
    }

    public void update() {
        printStartOfTurn();
        printPlayerCards();
        System.out.printf("Top card %s.", "YELLOW SKIP");
    }

    public static void main(String[] args) {
        Game g = new Game();
        GameView v = new GameView(g);
        v.update();
    }
}
