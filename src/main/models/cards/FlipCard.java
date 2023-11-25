package main.models.cards;

import main.models.Game;

/**
 * A Flip card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class FlipCard extends Card {
    /**
     * Creates a Flip card
     *
     * @param colour The colour of the card
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public FlipCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Symbol.FLIP, Card.getSideFromColour(colour));
    }
    @Override
    public void cardAction(Game game) {
        game.flip();
    }
}