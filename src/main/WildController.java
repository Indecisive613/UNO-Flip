package main;

import main.cards.Card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller to control the portion of the UNO interface that pops up if a Wild card is played
 *
 * @author Anand Balaram
 */
public class WildController implements ActionListener {
    private Game game;
    private WildView view;

    public WildController(Game game, WildView view){
        this.game = game;
        this.view = view;
    }

    /**
     * Gets the new colour of the pile
     *
     * @return the name of the player
     */
    public void requestWildColour(){
        String[] strOptions = {"Red", "Blue", "Green", "Yellow"};
        Card.Colour[] colours = {Card.Colour.RED, Card.Colour.BLUE, Card.Colour.GREEN, Card.Colour.YELLOW};

        int input = view.getColour(strOptions);
        while(input == -1) {
            view.showErrorMessage("Selecting a colour is mandatory.");
            input = view.getColour(strOptions);
        }
        game.setCurrentColour(colours[input]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}