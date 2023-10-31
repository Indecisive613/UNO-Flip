package main;

/**
 * A plain wild card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */

public class WildCard extends Card{
    /**
     * Creates an UNO wild card with the specified color.
     *
     * @throws IllegalArgumentException if an invalid card is created.
     */
    public WildCard() throws IllegalArgumentException{
        super(Card.Colour.WILD, Card.Symbol.WILD);
    }
    @Override
    public boolean cardAction(Game game) {
        return true;
    }
}
