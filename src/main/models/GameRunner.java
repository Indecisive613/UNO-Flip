package main.models;

import main.views.GameView;

import java.io.*;
import java.util.ArrayList;

/**
 * A class to initialize and run an UNO game.
 *
 * @author Fiona Cheng, Anand Balaram, Jackie Smolkin-Lerner
 */
public class GameRunner {

    private Game game;
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

    /** Export the current Game as a file
     *
     * @param filename The name of the exported file
     */
    public void exportGame(String filename) {
        try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename))) {
            objOut.writeObject(this.game);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Update the Game from an imported save file
     *
     * @param filename The file name of the saved game
     */
    public Game importGame(String filename) {
        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename))) {
            Game importedGame = (Game) objIn.readObject();

            ArrayList<GameView> gameViews = game.getViews();
            gameViews.forEach(view -> view.setGame(importedGame));
            importedGame.updateViews(gameViews);

            this.game = importedGame;

            return this.game;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }
}