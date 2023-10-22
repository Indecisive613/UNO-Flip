package main;

import java.util.Scanner;

/**
 * A Controller class for Game to respond to user input
 *
 * @author Jake, Anand Balaram
 */
public class GameController {

    private final Scanner input;
    private final GameView view;

    /**
     * Creates a GameController which gets user input
     *
     * @param view The associated GameView to display info to
     */
    public GameController(GameView view) {
        this.view = view;
        input = new Scanner(System.in);
    }

    /**
     * Asks the user for the number of players in the game
     *
     * @return The number of players in the game
     */
    public int requestPlayerCount() {
        int playerCount = -1;
        while (true) {
            System.out.print("Enter number of players (2-4): "); // TODO: Make method call in view
            if (!input.hasNextInt()) {
                input.next();
                continue;
            }
            playerCount = input.nextInt();
            if (playerCount >= Game.PLAYER_MIN && playerCount <= Game.PLAYER_MAX) {
                return playerCount;
            }
        }
    }

    /**
     * Asks the user for an action from a specific player
     * Actions:
     * 0: Draw a card
     * 1 to handSize: Play the card at that index
     *
     * @param handSize The player's hand size
     * @return The player's action
     */
    public int requestPlayerAction(int handSize) {
        int action = -1;
        view.updateGetCard();
        while (true) {
            if (!input.hasNextInt()) {
                input.next();
                view.updateInvalidInput("Invalid input. Please enter a valid number.");
                continue;
            }
            action = input.nextInt();
            if (action >= 0 && action <= handSize) {
                return action;
            }
            view.updateInvalidInput("Invalid choice. Try again.");
        }
    }

    /**
     * Ask the user for player i's name
     *
     * @param i The turn-number of the player who will be named
     * @return The name of player i
     */
    public String requestPlayerName(int i) {
        view.updateGetPlayerName(i);
        return input.next();
    }

    /**
     * Asks the user to choose a color when they play a wild card
     *
     * @return The colour chosen
     */
    public Card.Colour requestColour(){
        view.updateGetColor();
        Card.Colour returnVal;
        while (true) {
            Scanner stringScanner = new Scanner(System.in);
            String color = stringScanner.next();
            if (color.toUpperCase().equals("GREEN")) {
                returnVal = Card.Colour.GREEN;
                break;
            } else if (color.toUpperCase().equals("BLUE")) {
                returnVal = Card.Colour.GREEN;
                break;
            } else if (color.toUpperCase().equals("YELLOW")) {
                returnVal = Card.Colour.YELLOW;
                break;
            } else if (color.toUpperCase().equals("RED")) {
                returnVal = Card.Colour.RED;
                break;
            }
            view.updateInvalidInput("Invalid choice. Try again.");
        }
        view.updateConfirmColor(returnVal);
        return returnVal;

    }
}