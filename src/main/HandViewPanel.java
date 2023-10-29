package main;

import javax.swing.*;
import java.awt.*;

public class HandViewPanel extends JPanel implements GameView {

    private static final int CARD_WIDTH = 100;

    private Player player;
    private Game game;

    private final JLabel playerName;
    private final JPanel actions;
    private final JPanel buttons;
    private final HandController controller;

    public HandViewPanel() {
        controller = new HandController();

        playerName = new JLabel("", SwingConstants.CENTER);
        playerName.setFont(new Font("Mono", Font.PLAIN, 24));
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(playerName);

        actions = new JPanel();

        buttons = new JPanel();
        buttons.setSize(100, 300);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setSize(300, 60);
        JScrollPane scrollPane = new JScrollPane(buttons);
        // scrollPane.setPreferredSize(new Dimension(200, 100));
        //scrollPane.setSize(new Dimension(200, 100));
        this.add(actions);
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
        System.out.println(player.getName() + "'s turn");
        this.player = player;
        playerName.setText(player.getName());

        // Reset buttons
        actions.removeAll(); // TODO: Refactor to remove this
        buttons.removeAll();

        // Add next turn button
        JButton nextTurnButton = new JButton("NEXT TURN");
        nextTurnButton.setBackground(new Color(255, 255, 255));
        nextTurnButton.setFont(new Font("Mono", Font.PLAIN, 24));
        nextTurnButton.setEnabled(false);

        nextTurnButton.addActionListener(event -> {
            controller.nextTurn();
        });
        actions.add(nextTurnButton);

        // Add draw card button
        JButton drawButton = new JButton("DRAW");
        drawButton.setBackground(new Color(100, 100, 100));
        drawButton.setFont(new Font("Mono", Font.PLAIN, 24));

        drawButton.addActionListener(event -> {
            controller.drawCard();
            lockButtons();
            nextTurnButton.setEnabled(true);
        });
        actions.add(drawButton);

        // Add button for each card
        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i);
            JButton cardButton = new JCardButton(card);
            cardButton.setFont(new Font("Mono", Font.PLAIN, 24));
            cardButton.setPreferredSize(new Dimension(CARD_WIDTH, CARD_WIDTH * 100/70)); // Card ratio
            if (controller.isValidCard(card)) {
                int index = i;
                cardButton.addActionListener(event -> {
                   controller.playCard(index);
                   lockButtons();
                   nextTurnButton.setEnabled(true);
                });
            }
            else {
                cardButton.setEnabled(false);
            }
            buttons.add(cardButton);
        }
    }

    private void lockButtons() {
        for (Component button: buttons.getComponents()) {
            button.setEnabled(false);
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
