package main.cards;

import main.Game;

/**
 * A draw 5 card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class DrawFiveCard extends Card {
    /**
     * Creates a draw 5 card with the specified color.
     *
     * @param colour The color of the card.
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public DrawFiveCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Symbol.DRAW_FIVE, Card.getSideFromColour(colour));
    }
    @Override
    public boolean cardAction(Game game) {
        return false;
    }
}
