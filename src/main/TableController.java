package main;

import main.cards.Card;

import java.util.ArrayList;

/**
 * The controller for the portion of the UNO interface that displays the table, all of hte players, and relevant game data
 *
 * @author Jake Siushansain
 */
public class TableController {
    private Game game;

    private TableViewPanel view;

    public TableController(Game game, TableViewPanel view) {
        this.game = game;
        this.view = view;
    }

    /**
     * get the top card on the table
     *
     * @return the top card on the table
     */
    public Card getTopCard() {
        return game.getTopCard();
    }

    /**
     * get the current colour of the current UNO game
     *
     * @return the current colour of the top card
     */
    public String getCurrentColour() {
        return game.getCurrentColour().toString();
    }

    /**
     * get the current deck size of the current UNO game
     *
     * @return the current deck size
     */
    public int getDeckSize() {
        return game.getDeck().size();
    }

    /**
     * get all the players of the current UNO game
     *
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return game.getPlayers();
    }
}