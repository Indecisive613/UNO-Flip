package main.views;

import main.controllers.TableController;
import main.models.Game;
import main.models.JCardButton;
import main.models.Player;
import main.models.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A view to display the table of the current UNO game.
 *
 * @author Jackie Smolkin-Lerner, Jake Siushansian
 */
public class TableViewPanel extends JPanel implements GameView {

    private static final Dimension PLAYER_SIZE = new Dimension(200, 200);
    private static final Font BUTTON_FONT = new Font("Mono", Font.BOLD, 30);
    private final JLabel turnOrderReversed;
    private final JLabel currentColor;
    private final JLabel remainingCards;
    private final JPanel topCardPanel;
    private final JPanel playerPanel;
    private JButton topCard;
    private Game game;
    private TableController controller;

    public TableViewPanel(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel gameStatusPanel = new JPanel();

        // initialize the current direction
        turnOrderReversed = new JLabel("--- Turn Order Reversed: false", SwingConstants.CENTER);
        turnOrderReversed.setFont(BUTTON_FONT);
        turnOrderReversed.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameStatusPanel.add(turnOrderReversed);

        // initialize the current color
        currentColor = new JLabel(" --- Current Color: ", SwingConstants.CENTER);
        currentColor.setFont(BUTTON_FONT);
        currentColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameStatusPanel.add(currentColor);

        // initialize the number of remaining cards
        remainingCards = new JLabel(" --- Remaining Cards: ---", SwingConstants.CENTER);
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
        topCard.setEnabled(false);
        topCardPanel.add(topCard);
        this.add(topCardPanel);
    }
    @Override
    public void setGame(Game game) {
        this.game = game;
        controller = new TableController(game, this);
    }

    @Override
    public void handleNewGame() {

    }

    @Override
    public void handleNewTurn(Player player) {

        topCardPanel.removeAll();
        topCard = new JCardButton(controller.getTopCard());

        topCard.setEnabled(false);
        topCardPanel.add(topCard);

        this.add(topCardPanel);

        currentColor.setText(" --- Current Color: " + controller.getCurrentColour());
        remainingCards.setText(" --- Remaining Cards: " + controller.getDeckSize() + " ---");

        ArrayList<JButton> playerButtons = new ArrayList<>();
        ArrayList<Player> players = controller.getPlayers();

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
    public void handlePlayCard(Card playedCard) {

        topCardPanel.removeAll();
        topCard = new JCardButton(controller.getTopCard());

        topCard.setEnabled(false);
        topCardPanel.add(topCard);

        // Rerender with new card
        topCardPanel.setVisible(false);
        topCardPanel.setVisible(true);

        this.add(topCardPanel);

        currentColor.setText(" --- Current Color: " + controller.getCurrentColour());
        remainingCards.setText(" --- Remaining Cards: " + controller.getDeckSize() + " ---");
    }

    @Override
    public void handleDrawCard(Card drawnCard) {
        remainingCards.setText(" --- Remaining Cards: " + controller.getDeckSize() + " ---");
    }

    @Override
    public void handleGetColour() {

    }

    @Override
    public void handleUpdateTurnOrder(boolean turnReversed) {
        turnOrderReversed.setText("--- Turn Order Reversed: " + turnReversed);
    }

    @Override
    public void handleAiPlayerTurn(Player currentPlayer, Card playedCard, Card.Colour currentColour, boolean drewCard) {

    }

    @Override
    public void handleUndoAction() {
        handlePlayCard(controller.getTopCard());
        remainingCards.setText(" --- Remaining Cards: " + controller.getDeckSize() + " ---");
        handleUpdateTurnOrder(game.getTurnOrderReversed());
    }

    @Override
    public void handleRedoAction() {
        handleUndoAction();
    }

    @Override
    public void handleRestartGame() {

    }

}