package main.controllers;

import main.views.GameEndView;
import main.models.cards.Card;
import main.models.Game;
import main.models.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller for the portion of the UNO interface that ends the current UNO game
 *
 * @author Anand Balaram
 */
public class GameEndController implements ActionListener {
    private Game game;
    private GameEndView view;

    public GameEndController(Game game, GameEndView view){
        this.game = game;
        this.view = view;
    }

    /**
     * Gets the new colour of the pile
     *
     * @return the name of the player
     */
    public void requestNewGame(){
        Player winner = game.getCurrentPlayer();
        game.assignScore();
        if (game.hasWonGame()){
            int input = view.hasWonGame(winner);
            while (input == -1){
                view.showErrorMessage("End the game by pressing the button");
                input = view.hasWonGame(winner);
            }
            view.closeProgram();
            return;
        }
        String[] strOptions = {"Continue"};

        int input = view.getNewGameConfirmation(strOptions, winner);
        while(input == -1) {
            view.showErrorMessage("Starting a new game is mandatory");
            input = view.getNewGameConfirmation(strOptions, winner);
        }
        game.startNewRound();
    }

    public void requestRestart(){

        String[] strOptions = {"Continue"};

        int input = view.getRestartConfirmation(strOptions);
        while(input == -1) {
            view.showErrorMessage("Starting a new game is mandatory");
            input = view.getRestartConfirmation(strOptions);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}