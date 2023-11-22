package main.views;

import main.models.cards.Card;
import main.models.Game;
import main.models.Player;
import main.models.cards.DoubleSidedCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Display the current scores of all of the players in the current UNO game
 *
 * @author Anand Balaram, Jackie Smolkin-Lerner
 */
public class InfoViewPanel extends JLabel implements GameView {
    ArrayList<Player> players;
    Game game;
    JLabel scores;

    public InfoViewPanel(){
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font
        this.setHorizontalAlignment(JLabel.CENTER); // Set horizontal alignment
    }
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void handleNewGame() {}

    public void handleNewTurn(Player player) {
        this.players = game.getPlayers();
        String display = "";
        int numPlayers = players.size();
        for(int i = 0; i < numPlayers; i++){
            Player iterplayer = players.get(i);
            display += iterplayer.getName() + ": " + iterplayer.getScore();
            if (i != numPlayers - 1) {
                display += ", ";
            }
        }
        this.setText(display);
    }

    public void handlePlayCard(Card playedCard, String additionalMessage) {

    }

    public void handleDrawCard(Card drawnCard) {

    }

    public void handleGetColour() {

    }

    @Override
    public void handleUpdateTurnOrder(boolean turnReversed) {

    }

    @Override
    public void handleAiPlayerTurn(Player currentPlayer, DoubleSidedCard playedCard, Card.Colour currentColour) {

    }
}