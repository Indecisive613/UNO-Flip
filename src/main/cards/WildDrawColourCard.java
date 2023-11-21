package main.cards;

import main.Game;

/**
 * A wild draw colour card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class WildDrawColourCard extends Card {
    /**
     * Creates a wild draw colour card
     *
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public WildDrawColourCard() throws IllegalArgumentException{
        super(Card.Colour.WILD, Card.Symbol.WILD_DRAW_COLOUR, Card.Side.DARK);
    }
    @Override
    public boolean cardAction(Game game) {
        int nextPlayer = game.nextPlayer();
        DoubleSidedCard drawnCard;
        Colour currentColour = game.getCurrentColour();
        do {
            drawnCard = game.getDeck().pop();
            game.getPlayers().get(nextPlayer).dealCard(drawnCard);
        } while (!drawnCard.getActiveSide().getColour().equals(currentColour));
        game.setSkipPlayer();
        return true;
    }
}
