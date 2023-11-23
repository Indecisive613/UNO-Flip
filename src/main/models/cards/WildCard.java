package main.models.cards;

import main.models.Game;

/**
 * A plain wild card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class WildCard extends Card {
    /**
     * Creates an UNO wild card with the specified color.
     *
     * @param cardSide The side of the card
     * @throws IllegalArgumentException if an invalid card is created.
     */
    public WildCard(Card.Side cardSide) throws IllegalArgumentException{
        super(Card.Colour.WILD, Card.Symbol.WILD, cardSide);
    }
    @Override
    public void cardAction(Game game) {
    }
}