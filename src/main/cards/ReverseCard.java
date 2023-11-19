package main.cards;

import main.Game;
import main.cards.Card;

/**
 * A Reverse card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class ReverseCard extends Card {
    /**
     * Creates a new Reverse card with the specified color.
     *
     * @param colour The color of the card.
     * @param cardSide The side that the card belongs to.
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public ReverseCard(Card.Colour colour, Card.Side cardSide) throws IllegalArgumentException{
        super(colour, Card.Symbol.REVERSE, cardSide);
    }
    @Override
    public boolean cardAction(Game game) {
        game.reverseTurn();

        return false;
    }
}
