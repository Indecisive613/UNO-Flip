package main;

import java.util.Scanner;

public class GameController {

    private GameView gameView;

    public GameController(GameView gameView) {
        this.gameView = gameView;
    }

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


    public void requestUserInput(String command) {

    }
}
