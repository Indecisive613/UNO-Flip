package main;

import main.cards.Card;

import javax.swing.*;

public class WildView extends JOptionPane implements GameView{
    private Game game;
    private WildController controller;
    private GameViewFrame superFrame;

    public WildView(GameViewFrame superFrame, Game game){
        this.game = game;
        this.superFrame = superFrame;
        controller = new WildController(game, this);
    }

    public void showErrorMessage(String message){
        this.showMessageDialog(superFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public int getColour(String strOptions[]){
        int num = this.showOptionDialog(superFrame, "Choose one of the buttons below:", "Custom Option Pane",
                this.DEFAULT_OPTION, this.PLAIN_MESSAGE, null, strOptions, strOptions[0]);
        System.out.println(num);
        return num;
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
        if (additionalMessage.equals("WILD")){
            game.setCurrentColour(controller.requestWildColour());
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
