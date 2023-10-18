package main;

import java.util.Scanner;

/**
 * A Controller class for Game to respond to user input
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
}

/*
public int requestPlayerCount() {

        // allow the user to input the number of players
        Scanner input = new Scanner(System.in);
        System.out.println("How many players are playing this round of UNO?");
        String strNumPlayers = input.nextLine();

        // convert the user's string value to an integer value - the player count
        int intNumPlayers = Integer.parseInt(strNumPlayers);

        // check that the user has input an acceptable number of players
        while (intNumPlayers < 2 || intNumPlayers > 4) {
            System.out.println("This UNO game only permits 2, 3, or 4 players a round.");
            input = new Scanner(System.in);
            System.out.println("How many players?");
            strNumPlayers = input.nextLine();
            intNumPlayers = Integer.parseInt(strNumPlayers);
        }

        return intNumPlayers;
    }

    public String requestPlayerName() {

        // allow the user to input the name of a player
        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of this player?");
        String strPlayerName = input.nextLine();

        return strPlayerName;
    }

    public int requestPlayerAction(int handSize) {

        // TODO: Ask group about this method
        // allow the user to input their action
        Scanner input = new Scanner(System.in);
        System.out.println("What is the action for this player?");
        String strPlayerAction = input.nextLine();

        // convert the user's string value to an int value - the user action
        int intPlayerAction = Integer.parseInt(strPlayerAction);

        // check that the user has input an acceptable user action
        while (intPlayerAction < 0 || intPlayerAction > handSize) {
            input = new Scanner(System.in);
            System.out.println("What is the action for this player?");
            strPlayerAction = input.nextLine();
            intPlayerAction = Integer.parseInt(strPlayerAction);
        }

        return intPlayerAction;
    }

 */