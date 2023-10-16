package main;

import java.util.ArrayList;

public class GameView {

    private Game game; // TODO: Do we need this? Doesn't Game store GameView? This seems like a circular dependency.

    public void setGame(Game game) {
        this.game = game;
    }

    private void updateStartGame() {
    }

    private void updatePlayedCard(Card card) {
        System.out.printf("Played: %s\n", card);
    }

    private void updateInvalidInput() {
        System.out.println("Invalid input. Please enter a valid number.");
    }

    private void updateInvalidChoice() {
        System.out.println("Invalid choice. Try again.");
    }

    private void updateCardDoesntMatch(Card card) {
        System.out.println("Card doesn't match the top card. Try again.");
    }

    public void updateNewTurn(Player player) {
        System.out.printf("%s's Turn.\n", player.getName());
        System.out.printf("Current side: %s.\n", "Light");
        System.out.println("Your cards:");

        ArrayList<Card> playersHand = player.getHand();
        for (int i = 0; i < playersHand.size(); i++) {
            System.out.println(i + 1 + ". " + playersHand.get(i));
        }
        System.out.println();
        System.out.printf("Top card %s.\n", "YELLOW SKIP");
    }

    public void updatePlayCard(Card card) {
        System.out.println("Played: " + card);
    }
}
