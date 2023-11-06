package main;

import main.cards.Card;

import javax.swing.*;

public class WildViewPopUp extends JOptionPane implements GameView {
    public WildViewPopUp(Game game){
        String[] strOptions = {"Red", "Blue", "Green", "Yellow"};
        Card.Colour[] colours = {Card.Colour.RED, Card.Colour.BLUE, Card.Colour.GREEN, Card.Colour.YELLOW};
        int choice = -1;
        while (choice == -1) {
            choice = JOptionPane.showOptionDialog(null, "Choose one of the buttons below:", "Custom Option Pane",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, strOptions, strOptions[0]);
        }
        while(choice < 0){
            JOptionPane.showMessageDialog(null, "Choosing a colour for the wild is mandatory", "Error", JOptionPane.ERROR_MESSAGE);
            choice = JOptionPane.showOptionDialog(null, "Choose one of the buttons below:", "Custom Option Pane",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, strOptions, strOptions[0]);
        }

        game.setCurrentColour(colours[choice]);
    }
    public void setGame(Game game) {
    }

    @Override
    public void handleNewGame() {

    }

    public void updateGetPlayerName(int index) {

    }

    public void updateInvalidInput(String message) {

    }

    public void handleNewTurn(Player player) {

    }

    public void handlePlayCard(Card playedCard, String additionalMessage) {

    }

    public void handleDrawCard(Card drawnCard) {

    }

    public void updateGetCard() {

    }

    public void handleGetColour() {

    }

    public void updateConfirmColour(Card.Colour colour) {

    }
}
