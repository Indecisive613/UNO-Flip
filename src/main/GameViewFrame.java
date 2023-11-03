package main;

import main.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        newGameView = new NewGameView(this);
        game.addView(newGameView);
        newGameView.setGame(game);
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

    /**
     * @return the number of players in the game
     */
    public int requestPlayerCount(){
        return newGameView.requestPlayerCount(game.PLAYER_MIN, game.PLAYER_MAX);
    }

    /**
     * Adds playerCount players to the game
     *
     * @param playerCount number of players in the game
     */
    public void addPlayers(int playerCount){
        for(int i = 0; i < playerCount; i++){
            game.addPlayer(new Player(newGameView.requestPlayerName(i+1), new ArrayList<Card>()));
        }
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
