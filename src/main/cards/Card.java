package main.cards;

import main.Game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An UNO card that has a colour, symbol, and point value.
 *
 * @author Fiona Cheng, Anand Balaram
 */
public abstract class Card {
    public enum Side { LIGHT, DARK}
    public enum Colour { RED, GREEN, BLUE, YELLOW, WILD, PINK, TEAL, PURPLE, ORANGE}
    public enum Symbol {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        DRAW_ONE(10),
        SKIP(20),
        REVERSE(20),
        WILD(40),
        WILD_DRAW_TWO(50),
        SKIP_EVERYONE(30),
        DRAW_FIVE(20),
        FLIP(20),
        WILD_DRAW_COLOUR(60);

        private final int pointValue;

        Symbol(int pointValue) {
            this.pointValue = pointValue;
        }
    }

    private final Colour colour;
    private final Symbol symbol;
    private final int pointValue;

    public static final ArrayList<Symbol> wildSymbols = new ArrayList<Symbol>(Arrays.asList(Symbol.WILD, Symbol.WILD_DRAW_TWO));
    public static final ArrayList<Symbol> nonWildSymbols = new ArrayList<Symbol>(Arrays.asList(Symbol.ONE, Symbol.TWO, Symbol.THREE, Symbol.FOUR, Symbol.FIVE, Symbol.SIX, Symbol.SEVEN, Symbol.EIGHT, Symbol.NINE, Symbol.DRAW_ONE, Symbol.SKIP, Symbol.REVERSE));
    public static final ArrayList<Colour> wildColours = new ArrayList<Colour>(Arrays.asList(Colour.WILD));
    public static final ArrayList<Colour> nonWildColours = new ArrayList<Colour>(Arrays.asList(Colour.RED, Colour.GREEN, Colour.BLUE, Colour.YELLOW));

    /**
     * An UNO card with a colour and face value
     *
     * @param colour The colour of the card, or wild if it's a wild card
     * @param symbol The card number or symbol if it's a special card
     * @throws IllegalArgumentException if an invalid combination is provided.
     */
    public Card(Colour colour, Symbol symbol) throws IllegalArgumentException{
        this.colour = colour;
        this.symbol = symbol;
        this.pointValue = symbol.pointValue;

        if(wildColours.contains(colour) && nonWildSymbols.contains(symbol)) {
            throw new IllegalArgumentException("The symbol for a wild card can not be in " + nonWildSymbols);
        }
        if(nonWildColours.contains(colour) && wildSymbols.contains(symbol)) {
            throw new IllegalArgumentException("The symbol for a coloured card can not be in " + wildSymbols);
        }
    }

    /**
     * @return the colour of the card
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * @return The symbol of the card
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * @return The point value of the card
     */
    public int getPointValue() {
        return pointValue;
    }

    @Override
    public String toString() {
        return colour + " " + symbol;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card c = (Card) o;
        return this.colour.equals(c.colour) && this.symbol.equals(c.symbol);
    }

    /**
     * @param game The Game the card belongs to
     *
     * @return Whether the card is a wild
     */
    public abstract boolean cardAction(Game game);
}
