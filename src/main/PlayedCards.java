package main;

import java.util.Stack;

public class PlayedCards {

    private Stack<Card> cards;

    public PlayedCards() {
        cards = new Stack<Card>();
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void dealCard(Card card) {
        cards.push(card);
    }

    public Card seeTopCard() {
        if(cards.empty()) {
            return null;
        }
        return cards.peek();
    }
}
