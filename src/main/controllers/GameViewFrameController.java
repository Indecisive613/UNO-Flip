package main.controllers;

import main.GameViewFrame;
import main.models.Game;
import main.models.GameRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller for the main UNO view. Handles menu actions.
 *
 * @author Fiona Cheng
 */
public class GameViewFrameController implements ActionListener {

    private Game game;
    private final GameViewFrame view;
    private final GameRunner gameRunner;

    /**
     * Create a controller for GameViewFrame
     *
     * @param view associated view
     */
    public GameViewFrameController(GameViewFrame view, GameRunner gameRunner) {
        this.view = view;
        this.gameRunner = gameRunner;
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
            gameRunner.exportGame(GameViewFrame.saveFile);
        }else if("Load Game".equals(command)){
            Game importedGame = gameRunner.importGame(GameViewFrame.saveFile);
            if (importedGame == null) {
                JOptionPane.showMessageDialog(view, "Error: No save file found");

            } else {
                this.game = importedGame;
                // Re-render game
                view.setVisible(false);
                view.setVisible(true);

            }
        } else if("Restart".equals(command)){
            game.restartGame();
        }
    }
}
