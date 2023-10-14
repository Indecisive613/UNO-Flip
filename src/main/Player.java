package main;

import java.util.ArrayList;

public class Player {

    private final String name;
    private int score;
    private ArrayList<Card> hand;


    public Player(String name, ArrayList<Card> hand) throws IllegalArgumentException {
        if(hand.size() != 7){
            throw new IllegalArgumentException("The player must start with 7 cards in their hand.");
        }
        this.name = name;
        this.hand = hand;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Card playCard(int index) throws IllegalArgumentException {
        if(index < 0 || index > hand.size()){
            throw new IllegalArgumentException("You must play a card between 0 and " + hand.size());
        }
        Card card = hand.get(index);
        hand.remove(index);
        return card;
    }
}