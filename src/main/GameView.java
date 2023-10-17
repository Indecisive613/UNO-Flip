package main;

import java.util.ArrayList;

public class GameView {

    private Game game; // TODO: Do we need this? Doesn't Game store GameView? This seems like a circular dependency.

    public void setGame(Game game) {
        this.game = game;
    }

    public void updateGetPlayerName(int index) {
        System.out.print("Enter name for Player " + (index + 1) + ": ");
    }

    public void updateCardDoesntMatch() {
        System.out.println("Card doesn't match the top card. Try again.");
    }

    public void updateInvalidInput(String message) {
        System.out.println(message);
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
        System.out.printf("Top card %s.\n", game.getTopCard());
    }

    public void updatePlayCard(Card playedCard) {
        System.out.println("Played: " + playedCard);
    }

    public void updateDrawCard(Card drawnCard) {
        System.out.println("Drew a card: " + drawnCard);
    }

    public void updateGetCard() {
        System.out.println("Enter card index to play or 0 to draw a card:"); // TODO: Make method call in view
    }
}