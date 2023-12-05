package main.views;

import main.controllers.HandController;
import main.models.Game;
import main.models.JCardButton;
import main.models.Player;
import main.models.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A view to display the hand of the current player of the current UNO game.
 *
 * @author Jackie Smolkin-Lerner, Jake Siushansian
 */
public class HandViewPanel extends JPanel implements GameView {

    private static final Font BUTTON_FONT = new Font("Mono", Font.BOLD, 30);

    private final JLabel playerName;
    private final JLabel actionMessage;

    private final JButton drawButton;
    private final JButton endTurn;
    private final JButton undoButton;
    private final JButton redoButton;

    private final JPanel cardPanel;

    private final HandController controller;

    private Player player;
    private Game game;

    /**
     * Create a new HandViewPanel, along with its contents, and controller
     */
    public HandViewPanel() {
        controller = new HandController();

        playerName = new JLabel(" ", SwingConstants.CENTER);
        playerName.setFont(BUTTON_FONT);
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(playerName);

        actionMessage = new JLabel(" ", SwingConstants.CENTER);
        actionMessage.setFont(new Font("Mono", Font.PLAIN, 24));
        actionMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(actionMessage);

        JPanel actionPanel = new JPanel();

        cardPanel = new JPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Initialize the buttons
        endTurn = new JButton("END TURN");
        drawButton = new JButton("DRAW +");
        undoButton = new JButton("UNDO");
        redoButton = new JButton("REDO");

        // Add end turn button
        endTurn.setFocusPainted(false);
        endTurn.setBackground(new Color(255, 255, 255));
        endTurn.setFont(BUTTON_FONT);
        endTurn.setEnabled(false);

        endTurn.addActionListener(event -> {
            controller.nextTurn();
        });
        actionPanel.add(endTurn);

        // Add draw card button
        drawButton.setFocusPainted(false);
        drawButton.setBackground(Color.GREEN);
        drawButton.setFont(BUTTON_FONT);

        drawButton.addActionListener(event -> {
            controller.drawCard();

            drawButton.setEnabled(false);
            drawButton.setBackground(Color.WHITE);

            endTurn.setEnabled(true);
            endTurn.setBackground(Color.GREEN);

            undoButton.setEnabled(true);
            undoButton.setBackground(Color.GREEN);

            redoButton.setEnabled(false);
            redoButton.setBackground(Color.WHITE);
        });
        actionPanel.add(drawButton);

        // Add undo button
        undoButton.setFocusPainted(false);
        undoButton.setEnabled(false);
        undoButton.setBackground(Color.WHITE);
        undoButton.setFont(BUTTON_FONT);

        undoButton.addActionListener(event -> {
            controller.undoAction();

            endTurn.setEnabled(false);
            endTurn.setBackground(Color.WHITE);

            drawButton.setEnabled(true);
            drawButton.setBackground(Color.GREEN);
        });
        actionPanel.add(undoButton);

        // Add redo button
        redoButton.setFocusPainted(false);
        redoButton.setEnabled(false);
        redoButton.setBackground(Color.WHITE);
        redoButton.setFont(BUTTON_FONT);

        redoButton.addActionListener(event -> {
            controller.redoAction();

            endTurn.setEnabled(true);
            endTurn.setBackground(Color.GREEN);

            drawButton.setEnabled(false);
            drawButton.setBackground(Color.WHITE);
        });
        actionPanel.add(redoButton);

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
        actionMessage.setText(" ");

        if (player.getIsAI()) {
            playerName.setText("Current Player: " + player.getName() + " (AI Player)");
        }

        // Reset buttons
        endTurn.setEnabled(false);
        endTurn.setBackground(Color.WHITE);
        drawButton.setEnabled(true);
        drawButton.setBackground(Color.GREEN);

        undoButton.setEnabled(false);
        undoButton.setBackground(Color.WHITE);
        redoButton.setEnabled(false);
        redoButton.setBackground(Color.WHITE);

        updateCardPanel();
    }

    @Override
    public void handlePlayCard(Card playedCard) { updateCardPanel(); }

    @Override
    public void handleDrawCard(Card drawnCard) { updateCardPanel(); }

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
     * Update the card panel to display the current players hand
     */
    private void updateCardPanel() {
        cardPanel.setVisible(false);
        cardPanel.removeAll();

        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i).getActiveSide();
            JButton cardButton = new JCardButton(card);
            if (controller.isValidCard(card)) {
                int index = i;
                cardButton.addActionListener(event -> {
                    controller.playCard(index);
                    drawButton.setEnabled(false);
                    drawButton.setBackground(Color.WHITE);
                    lockHand();
                    endTurn.setEnabled(true);
                    endTurn.setBackground(Color.GREEN);
                    undoButton.setEnabled(true);
                    undoButton.setBackground(Color.GREEN);
                    redoButton.setEnabled(false);
                    redoButton.setBackground((Color.WHITE));
                });
            } else {
                cardButton.setEnabled(false);
            }
            cardPanel.add(cardButton);
        }
        cardPanel.setVisible(true);
    }

    @Override
    public void handleAiPlayerTurn(Player currentPlayer, Card playedCard, Card.Colour currentColour, boolean drewCard) {
        lockHand();
        drawButton.setEnabled(false);
        drawButton.setBackground(Color.WHITE);
        endTurn.setEnabled(true);
        endTurn.setBackground(Color.GREEN);

        undoButton.setEnabled(false);
        undoButton.setBackground(Color.WHITE);
        redoButton.setEnabled(false);
        redoButton.setBackground(Color.WHITE);

        StringBuilder sb = new StringBuilder();
        sb.append(currentPlayer.getName());
        if (playedCard == null) {
            sb.append(" drew a card");
        } else if (playedCard != null && drewCard) {
            String card;
            sb.append(" drew a card and played a ");

            if (playedCard.getColour() == Card.Colour.WILD) {
                card = Arrays.stream(playedCard.toString().split("\\s+|_")).skip(1)
                        .limit(playedCard.toString().split("\\s+|_").length - 2)
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));
                card += " and set the colour to " + currentColour;
            } else {
                card = Arrays.stream(playedCard.toString().split("\\s+|_"))
                        .limit(playedCard.toString().split("\\s+|_").length - 1)
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));
            }
            sb.append(card);

        } else {
            String card;
            // TODO: Fix wild message, colour, maybe add better toString in card to avoid this lol
            if (playedCard.getColour() == Card.Colour.WILD) {
                card = Arrays.stream(playedCard.toString().split("\\s+|_")).skip(1)
                        .limit(playedCard.toString().split("\\s+|_").length - 2)
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));
                card += " and set the colour to " + currentColour;
            } else {
                card = Arrays.stream(playedCard.toString().split("\\s+|_"))
                        .limit(playedCard.toString().split("\\s+|_").length - 1)
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "));
            }
            sb.append(" played a ").append(card);
        }
        if (playedCard != null && playedCard.getSymbol() == Card.Symbol.FLIP) {
            sb.append(" and flipped the deck ");
        }
        actionMessage.setText(sb.toString());
    }

    @Override
    public void handleUndoAction() {
        actionMessage.setText("The action of " + player.getName() + " was undone");

        drawButton.setEnabled(true);
        drawButton.setBackground(Color.GREEN);
        endTurn.setEnabled(false);
        endTurn.setBackground(Color.WHITE);

        undoButton.setEnabled(false);
        undoButton.setBackground(Color.WHITE);
        redoButton.setEnabled(true);
        redoButton.setBackground(Color.GREEN);

        updateCardPanel();
    }

    @Override
    public void handleRedoAction() {
        actionMessage.setText("The action of " + player.getName() + " was redone");

        drawButton.setEnabled(false);
        drawButton.setBackground(Color.WHITE);
        endTurn.setEnabled(true);
        endTurn.setBackground(Color.GREEN);

        redoButton.setEnabled(false);
        redoButton.setBackground(Color.WHITE);
        undoButton.setEnabled(true);
        undoButton.setBackground(Color.GREEN);

        updateCardPanel();
    }

    @Override
    public void handleRestartGame() {

    }
}