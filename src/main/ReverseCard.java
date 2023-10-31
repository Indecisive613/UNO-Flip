package main;

public class ReverseCard extends Card{
    public ReverseCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Card.Symbol.REVERSE);
    }
    @Override
    public boolean cardAction(Game game) {
        game.reverseTurn();

        return false;
    }
}
