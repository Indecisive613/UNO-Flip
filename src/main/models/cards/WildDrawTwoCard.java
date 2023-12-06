package main.models.cards;

import main.models.Game;

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
        super(Colour.WILD, Symbol.WILD_DRAW_TWO, Side.LIGHT);
    }

    @Override
    public void cardAction(Game game) {
        for(int i = 0; i < 2; i++){
            game.drawCard(game.getPlayers().get(game.nextPlayer()), false);
        }
        game.setSkipPlayer();
    }
}