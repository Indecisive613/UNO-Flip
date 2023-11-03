package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TableViewPanel extends JPanel implements GameView {

    private static final int CARD_WIDTH = 100;
    private Game game;
    private int deckSize;
    private JButton deck;
    private JButton topCard;
    private JPanel cardPanel;
    private JPanel playerPanel;
    private ArrayList<JButton> playerButtons;
    private static final Font BUTTON_FONT = new Font("Mono", Font.BOLD, 40);
    public TableViewPanel(){

        this.setSize(100, 100);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1, 100, 100));

        cardPanel = new JPanel();
        cardPanel.setSize(50,50);
        cardPanel.setVisible(true);
        cardPanel.setLayout(new GridLayout(1, 4, 200, 100));

        deck = new JCardButton(null);
        deck.setFocusPainted(false);
        deck.setBackground(Color.DARK_GRAY);
        deck.setFont(BUTTON_FONT);
        deck.setText("REMAINING CARDS");
        deck.setEnabled(false);
        cardPanel.add(deck);

        topCard = new JCardButton(null);
        topCard.setFocusPainted(false);
        topCard.setBackground(Color.GRAY);
        topCard.setFont(BUTTON_FONT);
        topCard.setEnabled(false);
        cardPanel.add(topCard);

        playerPanel = new JPanel();
        playerPanel.setSize(50,50);
        playerPanel.setVisible(true);
        playerPanel.setLayout(new GridLayout(1, 4, 100, 100));

        this.add(cardPanel);
        this.add(playerPanel);

        playerButtons = new ArrayList<JButton>();
    }
    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void handleNewGame() {

    }

    @Override
    public void updateGetPlayerName(int index) {

    }

    @Override
    public void updateInvalidInput(String message) {

    }

    @Override
    public void handleNewTurn(Player player) {

        Card startTopCard = game.getTopCard();

        cardPanel.remove(1);
        topCard = new JCardButton(startTopCard);
        cardPanel.add(topCard);
        topCard.setText(startTopCard.getSymbol().toString());
        topCard.setFont(BUTTON_FONT);
        topCard.setEnabled(false);

        deckSize = game.getDeck().size();
        deck.setText("REMAINING CARDS:" + deckSize);

        ArrayList<Player> players = game.getPlayers();

        for(Player selectedPlayer : players) {
            JButton playerButton = new JButton(selectedPlayer.getName());
            playerButton.setFocusPainted(false);
            playerButton.setBackground(Color.GRAY);
            playerButton.setFont(BUTTON_FONT);
            playerButton.setEnabled(false);

            if (playerButtons.size() < players.size()) {
                playerButtons.add(playerButton);
            }
        }

        for(JButton playerButton : playerButtons) {
            playerButton.setBackground(Color.LIGHT_GRAY);
            playerPanel.add(playerButton);

            if (playerButton.getText().equals(player.getName())) {
                playerButton.setBackground(Color.MAGENTA);
            }
        }
    }

    @Override
    public void handlePlayCard(Card playedCard, String additionalMessage) {
        cardPanel.remove(1);
        topCard = new JCardButton(playedCard);
        cardPanel.add(topCard);
        topCard.setText(playedCard.getSymbol().toString());
        topCard.setFont(BUTTON_FONT);
        topCard.setEnabled(false);

        deckSize = game.getDeck().size();
        deck.setText("REMAINING CARDS:" + deckSize);
    }

    @Override
    public void handleDrawCard(Card drawnCard) {
        deckSize = game.getDeck().size();
        deck.setText("REMAINING CARDS:" + deckSize);
    }

    @Override
    public void updateGetCard() {

    }

    @Override
    public void handleGetColour() {

    }

    @Override
    public void updateConfirmColour(Card.Colour colour) {

    }
}
