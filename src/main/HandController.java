package main;

import main.cards.Card;

/**
 * The controller for the portion of the UNO interface that displays the current player's hand
 * and the actions available for the current player
 *
 * @author Jackie Smolkin-Lerner
 */
public class HandController {
    private Game game;

    /**
     * sets the current UNO game
     *
     * @param game the UNO game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * checks if a card in a hand can be played on the current top card
     *
     * @param card the card that is being validated
     * @return true if the card can be played
     */
    public boolean isValidCard(Card card) {
        return game.canPlayCard(card);
    }

    /**
     * play the card on the current top card
     *
     * @param index the index of the card
     */
    public void playCard(int index) {
        game.playCard(game.getCurrentPlayer().playCard(index));
    }

    /**
     * draw a card from the deck
     */
    public void drawCard() {
        game.drawCard(game.getCurrentPlayer());
    }

    /**
     * advances the turn so that the next player in the game can play
     */
    public void nextTurn() {
        game.advanceTurn();
    }
}
