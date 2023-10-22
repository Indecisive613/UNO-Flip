package main;

import java.util.ArrayList;

/**
 * A View class for Game to update and display an UNO game
 *
 * @authors Jackie Smolkin-Lerner, Anand Balaram
 */
public class GameView {

    private Game game; // TODO: Do we need this? Doesn't Game store GameView? This seems like a circular dependency.

    /**
     * Set the current UNO game
     *
     * @param game the current UNO game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Display instructions for the user(s) to enter the name of a specific player
     *
     * @param index the number-1 corresponding to a specific player
     */
    public void updateGetPlayerName(int index) {
        System.out.print("Enter name for Player " + (index + 1) + ": ");
    }

    /**
     * Display a message stating that a card does not match the top card
     */
    public void updateCardDoesntMatch() {
        System.out.println("Card doesn't match the top card. Try again.");
    }

    /**
     * Display an invalid input message
     *
     * @param message the message that is displayed
     */
    public void updateInvalidInput(String message) {
        System.out.println(message);
    }

    /**
     * Display the card side, the top card, and the hand of the current player for this turn
     *
     * @param player The current player whose turn it is
     */
    public void updateNewTurn(Player player) {
        System.out.printf("\n######## %s's Turn. ########\n", player.getName()); // TODO: For debugging - remove later
        // System.out.printf("%s's Turn.\n", player.getName());
        System.out.printf("Current side: %s.\n", "Light");
        System.out.println("Your cards:");

        ArrayList<Card> playersHand = player.getHand();
        for (int i = 0; i < playersHand.size(); i++) {
            System.out.println(i + 1 + ". " + playersHand.get(i));
        }
        System.out.println();
        System.out.printf("Top card %s.\n", game.getTopCard());
    }

    /**
     * Display information about the card that was played and additional useful information
     *
     * @param playedCard the card that has been played
     * @param additionalMessage a message that provides useful information to the players
     */
    public void updatePlayCard(Card playedCard, String additionalMessage) {
        System.out.println("Played: " + playedCard);
        System.out.println(additionalMessage);
    }

    /**
     * Display information about the card that was drawn by a player
     *
     * @param drawnCard the card that was drawn by a player
     */
    public void updateDrawCard(Card drawnCard) {
        System.out.println("Drew a card: " + drawnCard);
    }

    /**
     * Display instructions to the current player about how to play or draw a card
     */
    public void updateGetCard() {
        System.out.println("Enter card index to play or 0 to draw a card:"); // TODO: Make method call in view
    }

    /**
     * Display instructions to the current player about what colour to pick
     */
    public void updateGetColor(){
        System.out.println("Choose a color (RED, YELLOW, GREEN, BLUE):");
    }

    /**
     * Display information about what colour the player has chosen
     *
     * @param color The colour that the player has chosen
     */
    public void updateConfirmColor(Card.Colour color){
        System.out.println(color + " has been chosen.");
    }
}