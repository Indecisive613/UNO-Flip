package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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
        JScrollPane scrollPane = new JScrollPane(buttons, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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
        nextTurnButton.setFocusPainted(false);
        nextTurnButton.setBackground(new Color(255, 255, 255));
        nextTurnButton.setFont(new Font("Mono", Font.PLAIN, 24));
        nextTurnButton.setEnabled(false);

        nextTurnButton.addActionListener(event -> {
            controller.nextTurn();
        });
        actions.add(nextTurnButton);

        // Add draw card button
        JButton drawButton = new JButton("DRAW +");
        drawButton.setFocusPainted(false);
        drawButton.setBackground(new Color(140, 140, 140));
        drawButton.setFont(new Font("Mono", Font.PLAIN, 24));
        System.out.println(Arrays.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));

        drawButton.addActionListener(event -> {
            controller.drawCard();
            drawButton.setEnabled(false);
            lockButtons();
            nextTurnButton.setEnabled(true);
            nextTurnButton.setBackground(Color.GREEN);
        });
        actions.add(drawButton);

        // Add button for each card
        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i);
            JButton cardButton = new JCardButton(card);
            cardButton.setPreferredSize(new Dimension(CARD_WIDTH, CARD_WIDTH * 100/70)); // Card ratio
            if (controller.isValidCard(card)) {
                int index = i;
                cardButton.addActionListener(event -> {
                   controller.playCard(index);
                   drawButton.setEnabled(false);
                   lockButtons();
                   nextTurnButton.setEnabled(true);
                   nextTurnButton.setBackground(Color.GREEN);
                });
            }
            else {
                cardButton.setEnabled(false);
            }
            buttons.add(cardButton);
        }

        System.out.println("Top card: " + game.getTopCard());
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
