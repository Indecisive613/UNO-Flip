package main.models;

import main.views.GameView;

import java.util.ArrayList;

/**
 * A class to initialize and run an UNO game.
 *
 * @author Fiona Cheng, Anand Balaram, Jackie Smolkin-Lerner
 */
public class GameRunner {

    private final Game game;
    private final ArrayList<GameView> views;

    /**
     * Create a GameRunner with a specific Game
     *
     * @param game The Game to run
     */
    public GameRunner(Game game) {
        this.game = game;
        this.views = new ArrayList<>();
    }

    /**
     * Add a view to the GameRunner
     *
     * @param view the view to be added
     */
    public void addView(GameView view) {
        views.add(view);
    }

    /**
     * Initialize a new game
     */
    public void initGame(){
        for (GameView view : views) {
            view.handleNewGame();
        }
        game.shuffleDeck();
        game.dealCards(Game.STARTING_HAND_SIZE);
        game.advanceTurn();
    }

}