package main;

import java.util.Stack;
import java.util.Collections;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<Card>();

        for(Card.Colour colour : Card.nonWildColours){
            for(Card.Symbol symbol : Card.nonWildSymbols) {
                Card card1, card2;
                try {
                    card1 = new Card(colour, symbol);
                    card2 = new Card(colour, symbol);
                } catch(IllegalArgumentException e){
                    System.out.println(e);
                    continue;
                }
                cards.push(card1);
                cards.push(card2);
            }
        }

        for(Card.Colour colour : Card.wildColours){
            for(Card.Symbol symbol : Card.wildSymbols) {
                Card card1, card2, card3, card4;
                try {
                    card1 = new Card(colour, symbol);
                    card2 = new Card(colour, symbol);
                    card3 = new Card(colour, symbol);
                    card4 = new Card(colour, symbol);
                } catch(IllegalArgumentException e){
                    System.out.println(e);
                    continue;
                }
                cards.push(card1);
                cards.push(card2);
                cards.push(card3);
                cards.push(card4);
            }
        }
    }

    //Method added for testing purposes only
    public Stack<Card> getDeck(){
        return cards;
    }

    public Card drawCard() throws ArrayIndexOutOfBoundsException {
        if(cards.isEmpty()){
            throw new ArrayIndexOutOfBoundsException("There are no more cards in the deck to draw.");
        }
        return cards.pop();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

}
