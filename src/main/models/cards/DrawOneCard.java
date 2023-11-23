package main.models.cards;

import main.models.Game;

/**
 * A Draw One card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class DrawOneCard extends Card {
    /**
     * Creates a new Uno Draw One card with the specified color.
     *
     * @param colour The color of the card.
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public DrawOneCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Card.Symbol.DRAW_ONE, Card.getSideFromColour(colour));
    }

    @Override
    public boolean cardAction(Game game) {
        game.drawCard(game.getPlayers().get(game.nextPlayer()));

        return false;
    }
}