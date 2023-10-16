package main;

import java.util.Scanner;
import java.util.StringTokenizer;

public class GameController {

    private Game game;
    private GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void requestUserInput() {

    }

    public String getInput() {

        Scanner readInput = new Scanner(System.in); // local variable maybe change

        String inputLine;
        String word = null;

        inputLine = readInput.nextLine();

        Scanner tokenizer = new Scanner(inputLine);

        if(tokenizer.hasNext()) {
            word = tokenizer.next();
        }

        String stringWord = word.toString();

        if (this.game.getValidCommands().contains(stringWord)) { // check if the game includes the word as a valid command
            return word;
        }

        else {
            return "invalid command";
        }
    }
}
