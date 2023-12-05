package main.controllers;

import main.models.Game;
import main.GameViewFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller for the main UNO view. Handles menu actions.
 *
 * @author Fiona Cheng
 */
public class GameViewFrameController implements ActionListener {

    private Game game;
    private GameViewFrame view;

    /**
     * Create a controller for GameViewFrame
     *
     * @param view associated view
     */
    public GameViewFrameController(GameViewFrame view) {
        this.view = view;
    }

    /**
     * Sets the current UNO game
     *
     * @param game the UNO game
     */
    public void setGame(Game game) {
        this.game = game;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        if ("Exit".equals(command)) {
            System.exit(0);
        } else if ("Save Game".equals(command)) {
            System.out.println("Save"); //TODO change this to export to a file
        }else if("Load Game".equals(command)){
            System.out.println("Load"); //TODO change this to import from a specified file
        } else if("Restart".equals(command)){
            game.restartGame();
        }
    }
}
