package main.cards;

import main.Game;

/**
 * A wild draw colour card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class WildDrawColour extends Card {
    /**
     * Creates a wild draw colour card
     *
     * @param cardSide The side of the card
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public WildDrawColour(Card.Side cardSide) throws IllegalArgumentException{
        super(Card.Colour.WILD, Symbol.WILD_DRAW_COLOUR, cardSide);
    }
    @Override
    public boolean cardAction(Game game) {
        return false;
    }
}
