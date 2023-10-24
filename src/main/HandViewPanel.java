package main;

import javax.swing.*;
import java.awt.*;

public class HandViewPanel extends JPanel implements GameView {

    private Player player;
    private JPanel playerHand;
    private JTextArea playerName;

    public HandViewPanel() {
        playerName = new JTextArea();
        playerName.setEnabled(false);
        this.add(playerName);

        playerHand = new JPanel();
        this.setLayout(new FlowLayout());
        this.add(new JScrollPane(playerHand));
    }

    @Override
    public void setGame(Game game) {

    }

    @Override
    public void updateGetPlayerName(int index) {

    }

    @Override
    public void updateInvalidInput(String message) {

    }

    @Override
    public void handleNewTurn(Player player) {
        System.out.println("New turn in HandViewPanel");
        this.player = player;
        playerName.setText(player.getName());

        playerHand.removeAll();
        for (Card card : player.getHand()) {
            System.out.println("adding card: " + card);
            playerHand.add(new JCardButton(card));
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
