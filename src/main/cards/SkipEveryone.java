package main.cards;

import main.Game;

/**
 * A skip everyone card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class SkipEveryone extends Card {
    /**
     * Creates a draw 5 card with the specified color.
     *
     * @param colour The color of the card.
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public SkipEveryone(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Symbol.SKIP_EVERYONE, Card.getSideFromColour(colour));
    }
    @Override
    public boolean cardAction(Game game) {
        return false;
    }
}
