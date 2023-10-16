package main;

import java.util.ArrayList;

public class Game {

    public static final int PLAYER_MIN = 2;
    public static final int PLAYER_MAX = 4;
    public static final int STARTING_HAND_SIZE = 7;

    private final ArrayList<GameView> views;
    private final ArrayList<Player> players;
    private final Deck deck;
    private final boolean turnOrderReversed = false;
    private int currentPlayer = 0;


    public Game(Deck deck) {
        this.deck = deck;
        players = new ArrayList<>();
        views = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void addView(GameView view) {
        views.add(view);
    }

    public void shuffleDeck() {
    }

    public void deal() {
        for (int i = 0; i < STARTING_HAND_SIZE; i++) {
            for (Player p : players) {
                p.dealCard(deck.drawCard());
            }
        }
    }

    public boolean isRunning() {
        return true;
    }

    public void nextTurn() {
        for (GameView view : views) {
            view.updateNewTurn(players.get(currentPlayer));
        }
        if (turnOrderReversed) {
            currentPlayer = (currentPlayer - 1) % players.size();
        } else {
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }

    public boolean canPlay(Card card) {
        return true; // TODO: implement
    }

    public void playCard(Card card) {
        // TODO: Play card logic
        for (GameView view : views) {
            view.updatePlayCard(card);
        }
    }
}
