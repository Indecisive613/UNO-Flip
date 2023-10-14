package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Card {
    public enum Colour { RED, GREEN, BLUE, YELLOW, WILD }
    public enum Symbol { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAW_ONE, WILD_DRAW_TWO, SKIP, REVERSE, WILD }

    private final Colour colour;
    private final Symbol symbol;
    private final int pointValue;

    public static final ArrayList<Symbol> wildSymbols = new ArrayList<Symbol>(Arrays.asList(Symbol.WILD, Symbol.WILD_DRAW_TWO));
    public static final ArrayList<Symbol> nonWildSymbols = new ArrayList<Symbol>(Arrays.asList(Symbol.ONE, Symbol.TWO, Symbol.THREE, Symbol.FOUR, Symbol.FIVE, Symbol.SIX, Symbol.SEVEN, Symbol.EIGHT, Symbol.NINE, Symbol.DRAW_ONE, Symbol.SKIP, Symbol.REVERSE));
    public static final ArrayList<Colour> wildColours = new ArrayList<Colour>(Arrays.asList(Colour.WILD));
    public static final ArrayList<Colour> nonWildColours = new ArrayList<Colour>(Arrays.asList(Colour.RED, Colour.GREEN, Colour.BLUE, Colour.YELLOW));

    public Card(Colour colour, Symbol symbol) throws IllegalArgumentException{
        //Making the assumption that no one will try to create WILD SEVEN for example...
        this.colour = colour;

        this.symbol = symbol;

        switch(symbol){
            case ONE:
                pointValue = 1;
                break;
            case TWO:
                pointValue = 2;
                break;
            case THREE:
                pointValue = 3;
                break;
            case FOUR:
                pointValue = 4;
                break;
            case FIVE:
                pointValue = 5;
                break;
            case SIX:
                pointValue = 6;
                break;
            case SEVEN:
                pointValue = 7;
                break;
            case EIGHT:
                pointValue = 8;
                break;
            case NINE:
                pointValue = 9;
                break;
            case DRAW_ONE:
                pointValue = 10;
                break;
            case WILD_DRAW_TWO:
                pointValue = 50;
                break;
            case SKIP:
            case REVERSE:
                pointValue = 20;
                break;
            case WILD:
                pointValue = 40;
                break;
            default:
                throw new IllegalArgumentException("Invalid Symbol");
        }

        if(wildColours.contains(colour) && nonWildSymbols.contains(symbol)) {
            throw new IllegalArgumentException("The symbol for a wild card can not be in " + nonWildSymbols);
        }
        if(nonWildColours.contains(colour) && wildSymbols.contains(symbol)) {
            throw new IllegalArgumentException("The symbol for a colored card can not be in " + wildSymbols);
        }
    }

    public Colour getColour() {
        return colour;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getPointValue() {
        return pointValue;
    }

    @Override
    public String toString() {
        return colour + " " + symbol;
    }
}
