package main;

import main.cards.Card;

import java.util.ArrayList;

/**
 * A Player with a name, score, and hand of cards
 *
 * @author Fiona Cheng, Jackie Smolkin-Lerner, Anand Balaram
 */
public class Player {

    private final String name;
    private int score;
    private ArrayList<Card> hand;
    private boolean isAI;

    /**
     * Create a Player with a given name and hand of cards
     *
     * @param name The name of the Player
     * @param hand The Player's hand of cards
     * @param isAI Whether the player is an AI
     */
    public Player(String name, ArrayList<Card> hand, boolean isAI) throws IllegalArgumentException {
        this.name = name;
        this.hand = hand;
        this.isAI = isAI;

        score = 0;
    }

    /**
     * @return The Player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The Player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return The Player's status as
     */
    public boolean getIsAI() { return isAI; }

    /**
     * Increment the Player's score by a given amount
     *
     * @param score The score increment
     */
    public void incrementScore(int score) {
        this.score += score;
    }

    /**
     * @return The Player's hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Deal a card to the Player
     *
     * @param card The dealt card
     */
    public void dealCard(Card card) {
        hand.add(card);
    }

    /**
     * Return and remove a card from the Players hand given its index
     *
     * @param index The index of the card to be played from the players hand
     * @return The card at index
     */
    public Card playCard(int index) throws IllegalArgumentException {
        if(index < 0 || index > hand.size() - 1) {
            throw new IllegalArgumentException("You must play a card between 0 and " + hand.size());
        }
        Card card = hand.get(index);
        hand.remove(index);
        return card;
    }

    /**
     * Clear the hand of a player in the current UNO game
     *
     * @return the temporary hand
     */
    public ArrayList<Card> clearHand(){
        ArrayList<Card> tempHand = hand;
        hand.clear();
        return tempHand;
    }
}