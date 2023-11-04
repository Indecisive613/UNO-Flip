package main.cards;

import main.Game;
import main.cards.Card;

/**
 * A wild draw two card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */

public class WildDrawTwoCard extends Card {
    /**
     * Creates an UNO wild draw two card with the specified color.
     *
     * @throws IllegalArgumentException if an invalid card is created.
     */
    public WildDrawTwoCard() throws IllegalArgumentException{
        super(Card.Colour.WILD, Symbol.WILD_DRAW_TWO);
    }
    @Override
    public boolean cardAction(Game game) {
        int nextPlayer = game.nextPlayer();
        Card drawnCard1 = game.getDeck().pop();
        Card drawnCard2 = game.getDeck().pop();

        game.getPlayers().get(nextPlayer).dealCard(drawnCard1);
        game.getPlayers().get(nextPlayer).dealCard(drawnCard2);

        game.setSkipPlayer();

        return true;
    }
}