package main;

public class Card {
    public enum Colour { RED, GREEN, BLUE, YELLOW, WILD }
    public enum Symbol { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_ONE, WILD_DRAW_TWO, SKIP, REVERSE, }

    private final Colour colour;
    private final Symbol symbol;

    public Card(Colour colour, Symbol symbol) {
        this.colour = colour;
        this.symbol = symbol;
    }

    public Colour getColour() {
        return colour;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    /* Return whether a given card can be played on this card
     */
    public Boolean validCard(Card card) {
        return false;
    }


    @Override
    public String toString() {
        return colour + " " + symbol;
    }
}
