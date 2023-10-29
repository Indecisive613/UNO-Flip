package main;

import javax.swing.*;
import java.awt.*;

public class HandViewPanel extends JPanel implements GameView {

    private Player player;
    private Game game;

    private final JLabel playerName;
    private final Box buttons;
    private final HandController controller;

    public HandViewPanel() {
        controller = new HandController();

        playerName = new JLabel("", SwingConstants.CENTER);
        playerName.setFont(new Font("Mono", Font.PLAIN, 24));
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(playerName);

        buttons = Box.createHorizontalBox();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(300, 60);
        JScrollPane scrollPane = new JScrollPane(buttons);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        this.add(scrollPane);
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
        controller.setGame(game);
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

        buttons.removeAll();
        for (Card card : player.getHand()) {
            JButton cardButton = new JCardButton(card);
            cardButton.setFont(new Font("Mono", Font.PLAIN, 24));
            cardButton.setSize(70 ,200);
            if (controller.isValidCard(card)) {
                cardButton.addActionListener(event -> {
                   controller.playCard(card);
                });
            }
            else {
                cardButton.setEnabled(false);
            }
            buttons.add(cardButton);
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
