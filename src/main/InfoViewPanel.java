package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoViewPanel extends JLabel implements GameView {
    ArrayList<Player> players;
    Game game;
    JLabel scores;

    public InfoViewPanel(){
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font
        this.setForeground(Color.BLUE); // Set foreground color
        this.setHorizontalAlignment(JLabel.CENTER); // Set horizontal alignment
        /*
        this.players = players;
        //this.setSize(200, 200);
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
        */
    }
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void handleNewGame() {

    }

    public void updateGetPlayerName(int index) {

    }

    public void updateInvalidInput(String message) {

    }

    public void handleNewTurn(Player player) {
        this.players = game.getPlayers();
        String display = "";
        System.out.println(players.size());
        int numPlayers = players.size();
        for(int i = 0; i < numPlayers; i++){
            Player iterplayer = players.get(i);
            System.out.println(iterplayer.getName());
            display += iterplayer.getName() + ": " + iterplayer.getScore();
            if (i != numPlayers - 1) {
                display += ", ";
            }
        }
        this.setText(display);
        /*
        Player previousPlayer = players.get(0);
        players.remove(0);
        players.add(previousPlayer);
        String turnOrder = "";
        for (Player iterPlayer: players){
            String playerName = iterPlayer.getName();
            turnOrder += playerName + "\n";
        }
        score.setText(turnOrder);

         */
    }

    public void handlePlayCard(Card playedCard, String additionalMessage) {

    }

    public void handleDrawCard(Card drawnCard) {

    }

    public void updateGetCard() {

    }

    public void handleGetColour() {

    }

    @Override
    public void handleUpdateTurnOrder(boolean turnReversed) {

    }

    public void updateConfirmColour(Card.Colour colour) {

    }

    public void updateScores(){
        /*
        String scores = "";
        for (Player player: players){
            String playerName = player.getName();
            scores += playerName + ": " + player.getScore() + "\n";
        }
        score.setText(scores);

         */
    }
}
