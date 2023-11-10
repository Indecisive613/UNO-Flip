package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class GameEndView extends JOptionPane implements GameView{
    private Game game;
    private GameEndController controller;
    private GameViewFrame superFrame;

    public GameEndView(GameViewFrame superFrame, Game game){
        this.game = game;
        this.superFrame = superFrame;
        controller = new GameEndController(game, this);
    }


    /**
     * Displays a custom error message
     *
     * @param message The error message to be displayed
     */
    public void showErrorMessage(String message){
        this.showMessageDialog(superFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays the chose colour pop
     *
     * @param strOptions The options for the popup
     */
    public int getNewGameConfirmation(String strOptions[], Player winner){
        String displayMessage = winner.getName() + "has won the round!\nPress the button to start a new game";
        return this.showOptionDialog(superFrame, displayMessage, "Custom Option Pane",
                this.DEFAULT_OPTION, this.PLAIN_MESSAGE, null, strOptions, strOptions[0]);
    }

    public int hasWonGame(Player winner){
        String displayMessage = winner.getName() + " has won the game!!\nPress the button to end the program";
        String[] strOptions = {"End"};
        return this.showOptionDialog(superFrame, displayMessage, "Custom Option Pane",
                this.DEFAULT_OPTION, this.PLAIN_MESSAGE, null, strOptions, strOptions[0]);

    }

    public void closeProgram(){
        superFrame.dispatchEvent(new WindowEvent(superFrame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void setGame(Game game) {

    }

    @Override
    public void handleNewGame() {

    }

    @Override
    public void handleNewTurn(Player player) {

    }

    @Override
    public void handlePlayCard(Card playedCard, String additionalMessage) {
        if (additionalMessage.equals("Done game")){
            System.out.println("reached");
            controller.requestNewGame();
        }
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
