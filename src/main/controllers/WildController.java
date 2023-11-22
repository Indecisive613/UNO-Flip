package main.controllers;

import main.models.cards.Card;
import main.models.Game;
import main.views.WildView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<String> strOptionsList;
        ArrayList<Card.Colour> coloursList;
        if (!game.isDark()){
            strOptionsList = new ArrayList<String>(List.of("Red", "Blue", "Green", "Yellow"));
            coloursList = new ArrayList<Card.Colour>(List.of(Card.Colour.RED, Card.Colour.BLUE, Card.Colour.GREEN, Card.Colour.YELLOW));
        }
        else{
            strOptionsList = new ArrayList<String>(List.of("Orange", "Teal", "Purple", "Pink"));
            coloursList = new ArrayList<Card.Colour>(List.of(Card.Colour.ORANGE, Card.Colour.TEAL, Card.Colour.PURPLE, Card.Colour.PINK));
        }
        String[] strOptions = strOptionsList.toArray(new String[0]);
        Card.Colour[] colours = coloursList.toArray(new Card.Colour[0]);

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