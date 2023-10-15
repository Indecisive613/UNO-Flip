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
            System.out.print("Enter number of players (2-4): ");
            if (!input.hasNextInt()) {
                input.next();
                continue;
            }
            playerCount = input.nextInt();
            if (playerCount >= 2 && playerCount <= 4) {
                return playerCount;
            }
        }
    }
}
