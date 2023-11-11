package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * The parent view to display and run the entire UNO game.
 *
 * @author Fiona Cheng, Jackie Smolkin-Lerner, Anand Balaram, Jake Siushansian
 */
public class GameViewFrame extends JFrame {

    private final NewGameView newGameView;

    /** Create a new GameViewFrame.
     * @param game The UNO game to display
     */
    public GameViewFrame(Game game) {
        super("UNO");
        this.setLayout(new BorderLayout());

        // Add new game view
        newGameView = new NewGameView(this, game);
        game.addView(newGameView);
        this.add(newGameView, BorderLayout.CENTER);

        GameEndView gameEndView = new GameEndView(this, game);
        game.addView(gameEndView);

        WildView wildView = new WildView(this, game);
        game.addView(wildView);

        // Add table view
        TableViewPanel table = new TableViewPanel();
        table.setGame(game);
        game.addView(table);
        this.add(table, BorderLayout.CENTER);

        // Add hand view
        HandViewPanel hand = new HandViewPanel();
        hand.setGame(game);
        game.addView(hand);
        this.add(hand, BorderLayout.SOUTH);

        // Add info view
        InfoViewPanel info = new InfoViewPanel();
        info.setGame(game);
        game.addView(info);
        this.add(info, BorderLayout.PAGE_START);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** Start an UNO game
     * @param args Main args - not used
     */
    public static void main(String[] args) {
        Stack<Card> deck = GameRunner.createDeck();
        Game g = new Game(deck);
        GameRunner runner = new GameRunner(g);
        GameViewFrame f = new GameViewFrame(g);
        runner.addView(f.newGameView);
        runner.initGame();
    }
}
