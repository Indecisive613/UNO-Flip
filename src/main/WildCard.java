package main;

public class WildCard extends Card{
    public WildCard() throws IllegalArgumentException{
        super(Card.Colour.WILD, Card.Symbol.WILD);
    }
    @Override
    public boolean cardAction(Game game) {
        return true;
    }
}
