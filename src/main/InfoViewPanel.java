package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoViewPanel extends JPanel implements GameView {
    ArrayList<Player> players;
    JTextArea score;
    JTextArea direction;

    public InfoViewPanel(ArrayList<Player> players){
        this.players = players;
        this.setSize(200, 200);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        String initialScore = "";
        String turnOrder = "";
        for (Player player: players){
            String playerName = player.getName();
            initialScore += playerName + ": 0" + "\n";
            turnOrder += playerName + "\n";
        }
        score = new JTextArea(initialScore);
        direction = new JTextArea(turnOrder);
        this.add(score, BorderLayout.WEST);
        this.add(direction, BorderLayout.EAST);
    }
    public void setGame(Game game) {

    }

    public void updateGetPlayerName(int index) {

    }

    public void updateInvalidInput(String message) {

    }

    public void handleNewTurn(Player player) {
        Player previousPlayer = players.get(0);
        players.remove(0);
        players.add(previousPlayer);
        String turnOrder = "";
        for (Player iterPlayer: players){
            String playerName = iterPlayer.getName();
            turnOrder += playerName + "\n";
        }
        score.setText(turnOrder);
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
