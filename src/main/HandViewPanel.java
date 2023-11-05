package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class HandViewPanel extends JPanel implements GameView {

    private static final int CARD_WIDTH = 100;

    private Player player;
    private Game game;

    private final JLabel playerName;
    private final JButton drawButton;
    private final JButton endTurn;

    //
    private final JButton UNOButton;

    private final JPanel actionPanel;
    private final JPanel cardPanel;
    private final HandController controller;
    private static final Font BUTTON_FONT = new Font("Mono", Font.BOLD, 24);

    public HandViewPanel() {
        controller = new HandController();

        playerName = new JLabel("", SwingConstants.CENTER);
        playerName.setFont(BUTTON_FONT);
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(playerName);

        actionPanel = new JPanel();

        cardPanel = new JPanel();
        cardPanel.setSize(100, 300);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(100, 300);

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

        //
        // Add uno button
        UNOButton = new JButton("UNO");
        UNOButton.setFocusPainted(false);
        UNOButton.setBackground(new Color(255, 255, 255));
        UNOButton.setFont(BUTTON_FONT);
        UNOButton.setEnabled(false);

        UNOButton.addActionListener(event -> {
            controller.sayUNO(true);
        });
        actionPanel.add(UNOButton);

        // Add draw card button
        drawButton = new JButton("DRAW +");
        drawButton.setFocusPainted(false);
        drawButton.setBackground(Color.GREEN);
        drawButton.setFont(BUTTON_FONT);

        drawButton.addActionListener(event -> {
            controller.drawCard();
            drawButton.setEnabled(false);

            //
            UNOButton.setEnabled(false);

            lockHand();
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

        //
        UNOButton.setEnabled(false);
        UNOButton.setBackground(new Color(255, 255, 255));

        drawButton.setEnabled(true);
        updateCardPanel();

        System.out.println("Top card: " + game.getTopCard());
    }

    private void lockHand() {
        for (Component button: cardPanel.getComponents()) {
            button.setEnabled(false);
        }

    }

    private void updateCardPanel() {
        cardPanel.setVisible(false);
        cardPanel.removeAll();

        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i);
            JButton cardButton = new JCardButton(card);
            cardButton.setPreferredSize(new Dimension(CARD_WIDTH, CARD_WIDTH * 100/70)); // Card ratio
            if (controller.isValidCard(card)) {
                int index = i;
                cardButton.addActionListener(event -> {
                    controller.playCard(index);
                    drawButton.setEnabled(false);
                    lockHand();
                    endTurn.setEnabled(true);
                    endTurn.setBackground(Color.GREEN);
                });
            }
            else {
                cardButton.setEnabled(false);
            }
            cardPanel.add(cardButton);
        }

        cardPanel.setVisible(true);

        //
        if (player.getHand().size() == 1) {
            handleUNO();
        }
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

    public void handleUNO() {

        UNOButton.setEnabled(true);
        UNOButton.setBackground(Color.GREEN);

        endTurn.setEnabled(true);
        endTurn.setBackground(Color.GREEN);

        UNOButton.addActionListener(event -> {
            UNOButton.setEnabled(false);
            UNOButton.setBackground(new Color(255, 255, 255));
        });

        endTurn.addActionListener(event -> {
            if(UNOButton.getModel().isEnabled() == true) {
                controller.sayUNO(false);
            }
            else {
                controller.sayUNO(true);
            }
        });
    }
}
