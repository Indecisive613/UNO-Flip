package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class GameViewFrame extends JFrame implements GameView {

    private final Game game;
    private final JCardButton topCardButton;
    private final HandViewPanel hand;
    private final TableViewFrame table;

    public GameViewFrame(Game game) {
        this.setLayout(new BorderLayout());
        this.game = game;
        JFrame frame = new JFrame("UNO");

        frame.setSize(300, 300);

        // Add top card
        topCardButton = new JCardButton(null);
        topCardButton.setEnabled(false);
        topCardButton.setCard(new Card(Card.Colour.BLUE, Card.Symbol.ONE));
        topCardButton.setSize(50, 50);
        frame.add(topCardButton, BorderLayout.CENTER);

        // Add table view
        table = new TableViewFrame();
        game.addView(table);
        frame.add(table, BorderLayout.CENTER);

        // Add hand view
        hand = new HandViewPanel();
        game.addView(hand);
        frame.add(hand, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setGame(Game game) {

    }

    @Override
    public void handleNewTurn(Player player) {

    }

    @Override
    public void updateGetPlayerName(int index) {

    }

    @Override
    public void updateInvalidInput(String message) {

    }


    @Override
    public void updatePlayCard(Card playedCard, String additionalMessage) {
        topCardButton.setCard(playedCard);
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
        Stack<Card> deck = new Stack<>();
        Game g = new Game(deck);

        ArrayList<Card> h = new ArrayList<>();
        h.add(new Card(Card.Colour.BLUE, Card.Symbol.ONE));
        h.add(new Card(Card.Colour.RED, Card.Symbol.FOUR));
        h.add(new Card(Card.Colour.GREEN, Card.Symbol.SKIP));
        h.add(new Card(Card.Colour.WILD, Card.Symbol.WILD));
        Player p = new Player("Player 1", h);
        g.addPlayer(p);
        JFrame f = new GameViewFrame(g);

        g.advanceTurn();
    }
}
