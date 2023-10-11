package main;

import java.util.ArrayList;

public class Player {

    private final String name;
    private final Integer score = 0;
    private final ArrayList<Card> hand;


    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
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
}
