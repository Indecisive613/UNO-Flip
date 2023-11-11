package main.cards;

import main.Game;
import main.cards.Card;

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
        super(colour, Card.Symbol.DRAW_ONE);
    }
    @Override
    public boolean cardAction(Game game) {
        int nextPlayer = game.nextPlayer();
        Card drawnCard = game.getDeck().pop();

        game.getPlayers().get(nextPlayer).dealCard(drawnCard);

        return false;
    }
}
