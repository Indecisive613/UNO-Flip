package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TableViewPanel extends JPanel implements GameView {

    private static final int CARD_WIDTH = 200;
    private static final int CARD_HEIGHT = CARD_WIDTH * 100/70;
    private static final Dimension PLAYER_SIZE = new Dimension(200, 200);
    private static final Font BUTTON_FONT = new Font("Mono", Font.BOLD, 30);
    private JLabel currentDirection;
    private JLabel currentColor;
    private JLabel remainingCards;
    private JPanel gameStatusPanel;
    private JButton topCard;
    private JPanel topCardPanel;
    private JPanel playerPanel;
    private Game game;

    public TableViewPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        gameStatusPanel = new JPanel();

        // initialize the current direction
        currentDirection = new JLabel(" --- Current Direction: Clockwise", SwingConstants.CENTER);
        currentDirection.setFont(BUTTON_FONT);
        currentDirection.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameStatusPanel.add(currentDirection);

        // initialize the current color
        currentColor = new JLabel(" --- Current Color: ", SwingConstants.CENTER);
        currentColor.setFont(BUTTON_FONT);
        currentColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameStatusPanel.add(currentColor);

        // initialize the number of remaining cards
        remainingCards = new JLabel(" --- Remaining Cards: ", SwingConstants.CENTER);
        remainingCards.setFont(BUTTON_FONT);
        remainingCards.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameStatusPanel.add(remainingCards);

        this.add(gameStatusPanel);

        // initialize the players
        playerPanel = new JPanel();
        playerPanel.setSize(400, 400);
        this.add(playerPanel);

        // initialize the top card
        topCardPanel = new JPanel();
        topCard = new JCardButton(null);
        topCard.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        topCard.setEnabled(false);
        topCardPanel.add(topCard);
        this.add(topCardPanel);
    }
    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void handleNewGame() {

    }

    @Override
    public void handleNewTurn(Player player) {

        topCardPanel.removeAll();
        topCard = new JCardButton(game.getTopCard());

        topCard.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        topCard.setEnabled(false);
        topCardPanel.add(topCard);

        this.add(topCardPanel);

        currentColor.setText(" --- Current Color: " + game.getCurrentColour().toString());
        remainingCards.setText(" --- Remaining Cards: " + game.getDeck().size() + " --- ");

        ArrayList<JButton> playerButtons = new ArrayList<>();
        ArrayList<Player> players = game.getPlayers();

        playerPanel.removeAll();

        for(Player selectedPlayer : players) {
            JButton playerButton = new JButton(selectedPlayer.getName());

            playerButton.setFont(BUTTON_FONT);
            playerButton.setPreferredSize(PLAYER_SIZE);
            playerButton.setBackground(Color.GRAY);
            playerButton.setEnabled(false);
            playerButtons.add(playerButton);
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

        topCardPanel.removeAll();
        topCard = new JCardButton(game.getTopCard());

        topCard.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        topCard.setEnabled(false);
        topCardPanel.add(topCard);

        // Rerender with new card
        topCardPanel.setVisible(false);
        topCardPanel.setVisible(true);

        this.add(topCardPanel);

        currentColor.setText(" --- Current Color: " + game.getCurrentColour().toString());
        remainingCards.setText(" --- Remaining Cards: " + game.getDeck().size() + " --- ");
    }

    @Override
    public void handleDrawCard(Card drawnCard) {
        remainingCards.setText("Remaining Cards: " + game.getDeck().size() + " --- ");
    }

    @Override
    public void handleGetColour() {

    }

    @Override
    public void handleUpdateTurnOrder(boolean turnReversed) {

    }
}
