package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The controller for the portion of the UNO interface that gathers player count and player names.
 *
 * @author Fiona Cheng
 */
public class NewGameController implements ActionListener {

    private Game game;
    private NewGameView view;

    /**
     * Create a NewGameController
     *
     * @param game The UNO game
     * @param view The corresponding view
     */
    public NewGameController(Game game, NewGameView view){
        this.game = game;
        this.view = view;
    }

    /**
     * Gets the name of the current player
     *
     * @param number The number associated with the current player
     * @return the name of the player
     */
    public String requestPlayerName(int number){
        String name = view.showInputPanel(number);
        while(name == null || name.length() == 0) {
            view.showErrorMessage("Entering a name is mandatory");
            name = view.showInputPanel(number);
        }
        return name;
    }

    /**
     * Gets the number of players in the game
     *
     * @param min The minimum player count
     * @param max The maximum player count
     * @return the number of players in the game
     */
    public int requestPlayerCount(int min, int max){
        ArrayList<Integer> options = new ArrayList<Integer>();
        for(int i = min; i <= max; i++) {
            options.add((Integer)i);
        }
        Integer[] optionsArr = options.toArray(new Integer[0]);
        int input = view.getPlayerCountInput(optionsArr);
        while(input == -1) {
            view.showErrorMessage("Entering a player count is mandatory.");
            input = view.getPlayerCountInput(optionsArr);
        }
        return input + min;
    }

    /**
     * Asks for the player count and the names of the players. Adds the players to the UNO game.
     */
    public void handleNewGame() {
        if (game.getRoundNumber() == 0) {
            int playerCount = requestPlayerCount(Game.PLAYER_MIN, Game.PLAYER_MAX);
            ArrayList<String> allNames = new ArrayList<String>();
            for (int i = 0; i < playerCount; i++) {
                String name = requestPlayerName(i + 1);
                while (allNames.contains(name)) {
                    view.showErrorMessage("That name has already been taken.");
                    name = requestPlayerName(i + 1);
                }
                allNames.add(name);
                game.addPlayer(new Player(name, new ArrayList<>()));
            }
            game.setRunning(true);
        }
    }
    public void actionPerformed(ActionEvent e){
    }
}
