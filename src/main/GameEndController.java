package main;

import main.cards.Card;

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
        Card.Colour[] colours = {Card.Colour.RED, Card.Colour.BLUE, Card.Colour.GREEN, Card.Colour.YELLOW};

        int input = view.getNewGameConfirmation(strOptions, winner);
        while(input == -1) {
            view.showErrorMessage("Starting a new game is mandatory");
            input = view.getNewGameConfirmation(strOptions, winner);
        }
        game.resetGame(); // TODO: figure out how to reset the game...using GameRunner.initGame()?
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
