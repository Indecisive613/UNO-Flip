package main.views;

import main.GameViewFrame;
import main.controllers.WildController;
import main.models.cards.Card;
import main.models.Game;
import main.models.Player;

import javax.swing.*;

/**
 * The portion of the UNO interface that pops up if a Wild card is played and selects the new colour.
 *
 * @author Anand Balaram
 */
public class WildView extends JOptionPane implements GameView {
    private Game game;
    private WildController controller;
    private GameViewFrame superFrame;

    /**
     * Create a WildView
     *
     * @param superFrame The parent frame
     * @param game The UNO game
     */
    public WildView(GameViewFrame superFrame, Game game){
        this.game = game;
        this.superFrame = superFrame;
        controller = new WildController(game, this);
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
    public int getColour(String strOptions[]){
        return this.showOptionDialog(superFrame, "Choose one of the buttons below:", "WILD card selection",
                this.DEFAULT_OPTION, this.PLAIN_MESSAGE, null, strOptions, strOptions[0]);
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
        controller = new WildController(game, this);
    }

    @Override
    public void handleNewGame() {

    }

    @Override
    public void handleNewTurn(Player player) {

    }

    @Override
    public void handlePlayCard(Card playedCard) {
    }

    @Override
    public void handleDrawCard(Card drawnCard) {

    }

    @Override
    public void handleGetColour() {
        controller.requestWildColour();
    }

    @Override
    public void handleUpdateTurnOrder(boolean turnReversed) {

    }

    @Override
    public void handleAiPlayerTurn(Player currentPlayer, Card playedCard, Card.Colour currentColour, boolean drewCard) {

    }

    @Override
    public void handleUndoAction() {

    }

    @Override
    public void handleRedoAction() {

    }

    @Override
    public void handleRestartGame() {

    }

}