package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TableViewPanel extends JPanel implements GameView {

    private final JButton deck;
    private ArrayList<JButton> playerButtons;
    private static final Font BUTTON_FONT = new Font("Mono", Font.BOLD, 24);
    public TableViewPanel(){

        this.setSize(200, 200);
        this.setVisible(true);
        this.setLayout(new GridLayout(3, 3, 10, 10));

        deck = new JButton("DECK");
        deck.setFocusPainted(false);
        deck.setBackground(Color.GRAY);
        deck.setFont(BUTTON_FONT);
        deck.setEnabled(false);

        playerButtons = new ArrayList<JButton>();
    }
    @Override
    public void setGame(Game game) {
        ArrayList<Player> players = game.getPlayers();

        for(Player selectedPlayer : players) {

            JButton playerButton = new JButton(selectedPlayer.getName());
            playerButton.setFocusPainted(false);
            playerButton.setBackground(Color.GRAY);
            playerButton.setFont(BUTTON_FONT);
            playerButton.setEnabled(false);

            playerButton.add(playerButton);
        }

        if(playerButtons.size() == 2) {
            this.add(playerButtons.get(0), BorderLayout.WEST);
            this.add(playerButtons.get(1), BorderLayout.NORTH);
            this.add(deck, BorderLayout.CENTER);
        }
        else if(playerButtons.size() == 3) {
            this.add(playerButtons.get(0), BorderLayout.WEST);
            this.add(playerButtons.get(1), BorderLayout.NORTH);
            this.add(playerButtons.get(2), BorderLayout.EAST);
            this.add(deck, BorderLayout.CENTER);
        }
        else if(playerButtons.size() == 4) {
            this.add(playerButtons.get(0), BorderLayout.WEST);
            this.add(playerButtons.get(1), BorderLayout.NORTH);
            this.add(playerButtons.get(2), BorderLayout.EAST);
            this.add(playerButtons.get(3), BorderLayout.SOUTH);
            this.add(deck, BorderLayout.CENTER);
        }
    }

    @Override
    public void updateGetPlayerName(int index) {

    }

    @Override
    public void updateInvalidInput(String message) {

    }

    @Override
    public void handleNewTurn(Player player) {

        for(JButton playerButton : playerButtons) {
            playerButton.setBackground(Color.GRAY);

            if (playerButton.getText().equals(player.getName())) {
                playerButton.setBackground(Color.BLUE);
            }
        }
    }

    @Override
    public void updatePlayCard(Card playedCard, String additionalMessage) {

    }

    @Override
    public void updateDrawCard(Card drawnCard) {

    }

    @Override
    public void updateGetCard() {

    }

    @Override
    public void updateGetColour() {

    }

    @Override
    public void updateConfirmColour(Card.Colour colour) {

    }
}
