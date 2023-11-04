package main;

import main.cards.Card;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Gathers player count and player names.
 *
 * @author Fiona Cheng
 */

public class NewGameView extends JPanel implements GameView{
    private GameViewFrame superFrame;
    private Game game;

    public NewGameView(GameViewFrame superFrame){
        this.superFrame = superFrame;
    }

    /**
     * @param number The number associated with the player
     *
     * @return the name of the player
     */
    public String requestPlayerName(int number){
        String name = "";
        name = JOptionPane.showInputDialog("Enter a name for player " + number + ":");
        while(name == null || name.length() == 0) {
            JOptionPane.showMessageDialog(superFrame, "Please enter a valid name", "Error", JOptionPane.ERROR_MESSAGE);
            name = JOptionPane.showInputDialog("Enter a name for player " + number + ":");
        }
        return name;
    }

    /**
     * @param min The minimum player count
     * @param max The maximum player count
     *
     * @return the number of players in the game
     */
    public int requestPlayerCount(int min, int max){
        ArrayList<Integer> options = new ArrayList<Integer>();
        for(int i = min; i <= max; i++) {
            options.add((Integer)i);
        }
        Integer[] optionsArr = options.toArray(new Integer[0]);
        int input = -1;
        while(input == -1) {
            input = JOptionPane.showOptionDialog(null, "Choose the number of players", "Input Panel", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsArr, optionsArr[0]);
        }
        return input + min;
    }
    @Override
    public void setGame(Game game) {
        this.game = game;

    }

    @Override
    public void handleNewGame() {
        int playerCount = requestPlayerCount(Game.PLAYER_MIN, Game.PLAYER_MAX);
        // TODO: Move to controller
        for(int i = 0; i < playerCount; i++){
            game.addPlayer(new Player(requestPlayerName(i+1), new ArrayList<>()));
        }
        game.setRunning(true);
    }

    @Override
    public void handleNewTurn(Player player) {

    }

    @Override
    public void handlePlayCard(Card playedCard, String additionalMessage) {

    }

    @Override
    public void handleDrawCard(Card drawnCard) {

    }

    @Override
    public void handleGetColour() {

    }
}
