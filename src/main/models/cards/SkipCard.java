package main.models.cards;

import main.models.Game;

/**
 * A Skip card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class SkipCard extends Card {
    /**
     * Creates a new Uno Skip card with the specified color.
     *
     * @param colour The color of the card.
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public SkipCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Card.Symbol.SKIP, Card.getSideFromColour(colour));
    }
    @Override
    public boolean cardAction(Game game) {
        game.setSkipPlayer();

        return false;
    }
}