package main;

import java.util.Scanner;

public class GameController {

    private final Scanner input;

    public GameController() {
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
        while (true) {
            System.out.print("Enter card index to play or 0 to draw a card: "); // TODO: Make method call in view
            if (!input.hasNextInt()) {
                input.next();
                continue;
            }
            action = input.nextInt();
            if (action >= 0 && action <= handSize) {
                return action;
            }
        }
    }
}
