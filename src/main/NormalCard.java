package main;

public class NormalCard extends Card{
    public NormalCard(Card.Colour colour, Card.Symbol symbol) throws IllegalArgumentException{
        super(colour, symbol);
    }
    @Override
    public boolean cardAction(Game game) {
        return false;
    }
}
