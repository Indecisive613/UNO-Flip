package main;

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
    private final JButton nextTurnButton;
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

        // Add next turn button
        nextTurnButton = new JButton("NEXT TURN");
        nextTurnButton.setFocusPainted(false);
        nextTurnButton.setBackground(new Color(255, 255, 255));
        nextTurnButton.setFont(BUTTON_FONT);
        nextTurnButton.setEnabled(false);

        nextTurnButton.addActionListener(event -> {
            controller.nextTurn();
        });
        actionPanel.add(nextTurnButton);

        // Add draw card button
        drawButton = new JButton("DRAW +");
        drawButton.setFocusPainted(false);
        drawButton.setBackground(new Color(140, 140, 140));
        drawButton.setFont(BUTTON_FONT);

        drawButton.addActionListener(event -> {
            controller.drawCard();
            drawButton.setEnabled(false);
            lockHand();
            nextTurnButton.setEnabled(true);
            nextTurnButton.setBackground(Color.GREEN);
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
    public void updateGetPlayerName(int index) {

    }

    @Override
    public void updateInvalidInput(String message) {

    }

    @Override
    public void handleNewTurn(Player player) {
        this.player = player;
        playerName.setText("Current Player: " + player.getName());

        // Reset buttons
        nextTurnButton.setEnabled(false);
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
                    nextTurnButton.setEnabled(true);
                    nextTurnButton.setBackground(Color.GREEN);
                });
            }
            else {
                cardButton.setEnabled(false);
            }
            cardPanel.add(cardButton);
        }

        cardPanel.setVisible(true);
    }

    @Override
    public void updatePlayCard(Card playedCard, String additionalMessage) {
        updateCardPanel();
    }

    @Override
    public void updateDrawCard(Card drawnCard) {
        updateCardPanel();
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

    public static void main(String[] args) {
        Stack<Card> deck = GameRunner.createDeck();
        Game game = new Game(deck);

        game.addPlayer(new Player("Player 1", new ArrayList<Card>()));
        game.addPlayer(new Player("Player 2", new ArrayList<Card>()));

        game.shuffleDeck();
        game.dealCards();

        GameViewFrame frame = new GameViewFrame(game);
        game.advanceTurn();
    }
}
