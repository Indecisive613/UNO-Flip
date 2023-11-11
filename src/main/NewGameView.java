package main;

import main.cards.Card;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The portion of the UNO interface that gathers player count and player names.
 *
 * @author Fiona Cheng
 */
public class NewGameView extends JPanel implements GameView{
    private GameViewFrame superFrame;
    private Game game;
    private NewGameController controller;

    /**
     * Create a NewGameView
     *
     * @param superFrame The parent frame
     * @param game The UNO game
     */
    public NewGameView(GameViewFrame superFrame, Game game){
        this.superFrame = superFrame;
        this.game = game;
        controller = new NewGameController(game, this);
    }

    /**
     * Asks the user to enter a name for a player
     *
     * @param number The current player number
     * @return The name of the current player
     */
    public String showInputPanel(int number){
        return  JOptionPane.showInputDialog("Enter a name for player " + number + ":");
    }

    /**
     * Asks the user to enter the number of players in the UNO game
     *
     * @param optionsArr The possible player counts
     * @return The number of players in the UNO game
     */
    public int getPlayerCountInput(Integer[] optionsArr){
        return JOptionPane.showOptionDialog(superFrame, "Choose the number of players", "Input Panel", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsArr, optionsArr[0]);
    }

    /**
     * Displays a custom error message
     *
     * @param message The error message to be displayed
     */
    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(superFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void handleNewGame() {
        if (game.getRoundNumber() == 0) {
            controller.handleNewGame();
        }
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

    @Override
    public void handleUpdateTurnOrder(boolean turnReversed) {

    }
}