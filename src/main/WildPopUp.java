package main;

import javax.swing.*;

public class WildPopUp extends JOptionPane implements GameView {
    Game game; // should technically create a controller to do this I guess, but it would only have 1 method...
    public WildPopUp(JFrame parentFrame){
        String[] strOptions = {"Red", "Blue", "Green", "Yellow"};
        Card.Colour[] colours = {Card.Colour.RED, Card.Colour.BLUE, Card.Colour.GREEN, Card.Colour.YELLOW};
        int choice = JOptionPane.showOptionDialog(parentFrame, "Choose one of the buttons below:", "Custom Option Pane",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, strOptions, strOptions[0]);

        if (choice >= 0) {
            game.setCurrentColour(colours[choice]);
        }
    }
    public void setGame(Game game) {

    }

    public void updateGetPlayerName(int index) {

    }

    public void updateInvalidInput(String message) {

    }

    public void handleNewTurn(Player player) {

    }

    public void updatePlayCard(Card playedCard, String additionalMessage) {

    }

    public void updateDrawCard(Card drawnCard) {

    }

    public void updateGetCard() {

    }

    public void updateGetColour() {

    }

    public void updateConfirmColour(Card.Colour colour) {

    }
}
