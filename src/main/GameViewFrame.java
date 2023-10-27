package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class GameViewFrame extends JFrame {

    private final Game game;
    private final HandViewPanel hand;
    private final TableViewFrame table;

    public GameViewFrame(Game game) {
        this.setLayout(new BorderLayout());
        this.game = game;
        JFrame frame = new JFrame("UNO");

        // Add table view
        table = new TableViewFrame();
        game.addView(table);
        frame.add(table, BorderLayout.CENTER);

        // Add hand view
        hand = new HandViewPanel();
        game.addView(hand);
        frame.add(hand, BorderLayout.SOUTH);

        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
