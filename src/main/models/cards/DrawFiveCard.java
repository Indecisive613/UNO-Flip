package main.models.cards;

import main.models.Game;

/**
 * A draw 5 card in a game of UNO Flip.
 *
 * @author Fiona Cheng
 */
public class DrawFiveCard extends Card {
    /**
     * Creates a draw 5 card with the specified color.
     *
     * @param colour The color of the card.
     * @throws IllegalArgumentException if an invalid color is provided.
     */
    public DrawFiveCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Symbol.DRAW_FIVE, Card.getSideFromColour(colour));
    }
    @Override
    public void cardAction(Game game) {
        for(int i = 0; i < 5; i++){
            game.drawCard(game.getPlayers().get(game.nextPlayer()));
        }
    }
}