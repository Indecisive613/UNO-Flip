package main;

import java.util.Scanner;

public class GameController {

    private final Scanner input;
    private final GameView view;

    public GameController(GameView view) {
        this.view = view;
        input = new Scanner(System.in);
    }

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

    public String requestPlayerName(int i) {
        view.updateGetPlayerName(i);
        return input.next();
    }
}
