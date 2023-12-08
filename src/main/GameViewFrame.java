package main;

import main.controllers.GameViewFrameController;
import main.models.DeckBuilder;
import main.models.Game;
import main.models.GameRunner;
import main.models.cards.DoubleSidedCard;
import main.views.*;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * The parent view to display and run the entire UNO game.
 *
 * @author Fiona Cheng, Jackie Smolkin-Lerner, Anand Balaram, Jake Siushansian
 */
public class GameViewFrame extends JFrame {

    public static final String saveFile = "save.ser";

    /**
     * Create a new GameViewFrame.
     *
     * @param game The UNO game to display
     */
    public GameViewFrame(Game game, GameRunner runner) {
        super("UNO");

        GameViewFrameController controller = new GameViewFrameController(this, runner);
        controller.setGame(game);

        this.setLayout(new BorderLayout());

        //Menu bar setup
        JMenuBar menuBar = new JMenuBar();

        JMenu actionMenu = new JMenu("Actions");
        JMenuItem saveGameMenuItem = new JMenuItem("Save Game");
        JMenuItem loadGameMenuItem = new JMenuItem("Load Game");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem restartMenuItem = new JMenuItem("Restart");

        actionMenu.add(saveGameMenuItem);
        actionMenu.add(loadGameMenuItem);
        actionMenu.add(exitMenuItem);
        actionMenu.add(restartMenuItem);

        menuBar.add(actionMenu);
        this.setJMenuBar(menuBar);

        saveGameMenuItem.addActionListener(controller);
        loadGameMenuItem.addActionListener(controller);
        exitMenuItem.addActionListener(controller);
        restartMenuItem.addActionListener(controller);

        // Add new game view
        NewGameView newGameView = new NewGameView(this, game);
        runner.addView(newGameView);
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
        Stack<DoubleSidedCard> deck = DeckBuilder.createDoubleSidedDeck();
        Game g = new Game(deck);
        GameRunner runner = new GameRunner(g);
        new GameViewFrame(g, runner);
        runner.initGame();
    }
}