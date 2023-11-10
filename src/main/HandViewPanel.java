package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;

/**
 * A view to display the hand of the current player of the current UNO game.
 *
 * @authors Jackie Smolkin-Lerner, Jake Siushansian
 */
public class HandViewPanel extends JPanel implements GameView {

    private static final Font BUTTON_FONT = new Font("Mono", Font.BOLD, 30);

    private final JLabel playerName;
    private final JButton drawButton;
    private final JButton endTurn;
    private final JPanel cardPanel;
    private final HandController controller;

    private Player player;
    private Game game;

    public HandViewPanel() {
        controller = new HandController();

        playerName = new JLabel("", SwingConstants.CENTER);
        playerName.setFont(BUTTON_FONT);
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(playerName);

        JPanel actionPanel = new JPanel();

        cardPanel = new JPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Add end turn button
        endTurn = new JButton("END TURN");
        endTurn.setFocusPainted(false);
        endTurn.setBackground(new Color(255, 255, 255));
        endTurn.setFont(BUTTON_FONT);
        endTurn.setEnabled(false);

        endTurn.addActionListener(event -> {
            controller.nextTurn();
        });
        actionPanel.add(endTurn);

        // Add draw card button
        drawButton = new JButton("DRAW +");
        drawButton.setFocusPainted(false);
        drawButton.setBackground(Color.GREEN);
        drawButton.setFont(BUTTON_FONT);

        drawButton.addActionListener(event -> {
            controller.drawCard();

            drawButton.setEnabled(false);

            endTurn.setEnabled(true);
            endTurn.setBackground(Color.GREEN);
        });
        actionPanel.add(drawButton);

        this.add(actionPanel);
        this.add(scrollPane);
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
        controller.setGame(game);
    }

    @Override
    public void handleNewGame() {
    }

    @Override
    public void handleNewTurn(Player player) {

        this.player = player;
        playerName.setText("Current Player: " + player.getName());

        // Reset buttons
        endTurn.setEnabled(false);
        endTurn.setBackground(new Color(255, 255, 255));

        drawButton.setEnabled(true);
        updateCardPanel();
    }

    @Override
    public void handlePlayCard(Card playedCard, String additionalMessage) {
        updateCardPanel();
    }

    @Override
    public void handleDrawCard(Card drawnCard) {
        updateCardPanel();
    }

    @Override
    public void handleGetColour() {

    }

    @Override
    public void handleUpdateTurnOrder(boolean turnReversed) {

    }

    /**
     * Prevent the current player from playing any cards in their hand
     */
    private void lockHand() {
        for (Component button : cardPanel.getComponents()) {
            button.setEnabled(false);
        }

    }

    /**
     * Update the card panel to display the contents of their
     */
    private void updateCardPanel() {
        cardPanel.setVisible(false);
        cardPanel.removeAll();

        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i);
            JButton cardButton = new JCardButton(card);
            if (controller.isValidCard(card)) {
                int index = i;
                cardButton.addActionListener(event -> {
                    controller.playCard(index);
                    drawButton.setEnabled(false);
                    lockHand();
                    endTurn.setEnabled(true);
                    endTurn.setBackground(Color.GREEN);
                });
            } else {
                cardButton.setEnabled(false);
            }
            cardPanel.add(cardButton);
        }

        cardPanel.setVisible(true);

    }
}
