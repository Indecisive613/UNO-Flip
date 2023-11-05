package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class GameViewFrame extends JFrame {

    private final Game game;
    private final NewGameView newGameView;
    private final HandViewPanel hand;
    private final TableViewPanel table;

    public GameViewFrame(Game game) {
        this.setLayout(new BorderLayout());
        this.game = game;
        JFrame frame = new JFrame("UNO");

        // Add new game view
        newGameView = new NewGameView(this, game);
        game.addView(newGameView);
        frame.add(newGameView, BorderLayout.CENTER);

        // Add table view
        table = new TableViewPanel();
        table.setGame(game);
        game.addView(table);
        frame.add(table, BorderLayout.CENTER);

        // Add hand view
        hand = new HandViewPanel();
        hand.setGame(game);
        game.addView(hand);
        frame.add(hand, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Stack<Card> deck = GameRunner.createDeck();
        Game g = new Game(deck);
        GameRunner runner = new GameRunner(g);
        GameViewFrame f = new GameViewFrame(g);
        runner.addView(f.newGameView);
        runner.initGame();
    }
}
