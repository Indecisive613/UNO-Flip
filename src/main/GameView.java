package main;

import main.cards.Card;

/**
 * A View interface for Game to update and display an UNO game
 *
 * @authors Jackie Smolkin-Lerner, Anand Balaram
 */
public interface GameView {

    /**
     * Set the current UNO game
     *
     * @param game the current UNO game
     */
    void setGame(Game game);

    void handleNewGame();

    /**
     * Display the card side, the top card, and the hand of the current player for this turn
     *
     * @param player The current player whose turn it is
     */
    void handleNewTurn(Player player);

    /**
     * Display information about the card that was played and additional useful information
     *
     * @param playedCard        the card that has been played
     * @param additionalMessage a message that provides useful information to the players
     */
    void handlePlayCard(Card playedCard, String additionalMessage);

    /**
     * Display information about the card that was drawn by a player
     *
     * @param drawnCard the card that was drawn by a player
     */
    void handleDrawCard(Card drawnCard);

    /**
     * Display instructions to the current player about what colour to pick
     */
    void handleGetColour();
}