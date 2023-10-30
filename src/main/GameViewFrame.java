package main;

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
        frame.add(newGameView, BorderLayout.CENTER);

        // Add table view
        table = new TableViewPanel();
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
        GameViewFrame f = new GameViewFrame(g);

        int playerCount = f.requestPlayerCount();
        System.out .println("The number of players is" + playerCount);

        g.shuffleDeck();
        g.dealCards();

        f.addPlayers(playerCount);
        System.out.println("The players in the game are as follows: ");
        for(Player player: g.getPlayers()){
            System.out.println("Name: " + player.getName());
            System.out.println("Hand: " + player.getHand());
        }

        g.advanceTurn();
    }
}
