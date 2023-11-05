package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class GameViewFrame extends JFrame {

    public static final Dimension GAME_SIZE = new Dimension(1920, 1080);

    private final NewGameView newGameView;

    public GameViewFrame(Game game) {
        this.setLayout(new BorderLayout());
        JFrame frame = new JFrame("UNO");

        // Add new game view
        newGameView = new NewGameView(this, game);
        game.addView(newGameView);
        frame.add(newGameView, BorderLayout.CENTER);

        // Add table view
        TableViewPanel table = new TableViewPanel();
        table.setGame(game);
        game.addView(table);
        frame.add(table, BorderLayout.CENTER);

        // Add hand view
        HandViewPanel hand = new HandViewPanel();
        hand.setGame(game);
        game.addView(hand);
        frame.add(hand, BorderLayout.SOUTH);

        frame.setSize(GAME_SIZE);
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
