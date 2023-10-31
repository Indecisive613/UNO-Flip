package main;

public class WildDrawTwoCard extends Card{
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

        return true;
    }
}
