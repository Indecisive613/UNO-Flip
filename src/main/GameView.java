package main;

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
    public void setGame(Game game);

    /**
     * Display instructions for the user(s) to enter the name of a specific player
     *
     * @param index the number-1 corresponding to a specific player
     */
    public void updateGetPlayerName(int index);

    /**
     * Display an invalid input message
     *
     * @param message the message that is displayed
     */
    public void updateInvalidInput(String message);

    /**
     * Display the card side, the top card, and the hand of the current player for this turn
     *
     * @param player The current player whose turn it is
     */
    public void handleNewTurn(Player player);

    /**
     * Display information about the card that was played and additional useful information
     *
     * @param playedCard the card that has been played
     * @param additionalMessage a message that provides useful information to the players
     */
    public void updatePlayCard(Card playedCard, String additionalMessage);

    /**
     * Display information about the card that was drawn by a player
     *
     * @param drawnCard the card that was drawn by a player
     */
    public void updateDrawCard(Card drawnCard);

    /**
     * Display instructions to the current player about how to play or draw a card
     */
    public void updateGetCard();

    /**
     * Display instructions to the current player about what colour to pick
     */
    public void updateGetColour();

    /**
     * Display information about what colour the player has chosen
     *
     * @param colour The colour that the player has chosen
     */
    public void updateConfirmColour(Card.Colour colour);
}