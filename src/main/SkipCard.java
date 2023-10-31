package main;

public class SkipCard extends Card{
    public SkipCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Card.Symbol.SKIP);
    }
    @Override
    public boolean cardAction(Game game) {
        game.setSkipPlayer();

        return false;
    }
}