package main;

/**
 * A Reverse card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */

public class ReverseCard extends Card{
    /**
     * Creates a new Reverse card with the specified color.
     *
     * @param colour The color of the card.
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public ReverseCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Card.Symbol.REVERSE);
    }
    @Override
    public boolean cardAction(Game game) {
        game.reverseTurn();

        return false;
    }
}
