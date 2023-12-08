package main.controllers;

import main.models.cards.Card;
import main.models.Game;

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
    public void drawCard() { game.drawCard(game.getCurrentPlayer(), true); }

    /**
     * Check if a card has been drawn this turn
     *
     * @return Whether the current player has drawn a card in the current state
     */
    public boolean hasDrawnCard() { return game.getHasDrawnCard(); }

    /**
     * Check if a card has been played this turn
     *
     * @return Whether the current player has played a card in the current state
     */
    public boolean hasPlayedCard() { return game.getHasPlayedCard(); }

    /**
     * Check if the player can undo
     *
     * @return Whether the current player can undo their action
     */
    public boolean canRedo() { return game.getCanRedo(); }

    /**
     * advances the turn so that the next player in the game can play
     */
    public void nextTurn() {
        game.advanceTurn();
    }

    /**
     * undoes the action of the current player
     */
    public void undoAction() {
        game.undo();
    }

    /**
     * redoes the action of the current player
     */
    public void redoAction() {
        game.redo();
    }
}