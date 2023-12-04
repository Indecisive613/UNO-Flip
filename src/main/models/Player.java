package main.models;

import main.models.cards.*;

import java.util.ArrayList;

/**
 * A Player with a name, score, and hand of cards
 *
 * @author Fiona Cheng, Jackie Smolkin-Lerner, Anand Balaram
 */
public class Player {

    private final String name;
    private int score;
    private ArrayList<DoubleSidedCard> hand;
    private boolean isAI;

    public ArrayList<DoubleSidedCard> getPreviousHand() {
        return previousHand;
    }

    private ArrayList<DoubleSidedCard> previousHand;

    /**
     * Create a Player with a given name and hand of cards
     *
     * @param name The name of the Player
     * @param hand The Player's hand of cards
     * @param isAI Whether the player is an AI
     */
    public Player(String name, ArrayList<DoubleSidedCard> hand, boolean isAI) throws IllegalArgumentException {
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
    public ArrayList<DoubleSidedCard> getHand() {
        return hand;
    }

    /**
     * @return The Player's active hand
     */
    public ArrayList<Card> getActiveHand() {
        ArrayList<Card> activeHand = new ArrayList<>();
        for(DoubleSidedCard card: hand){
            activeHand.add(card.getActiveSide());
        }
        return activeHand;
    }

    /**
     * Deal a card to the Player
     *
     * @param card The dealt card
     */
    public void dealCard(DoubleSidedCard card) {
        hand.add(card);
    }

    /**
     * Return and remove a card from the Players hand given its index
     *
     * @param index The index of the card to be played from the players hand
     * @return The card at index
     */
    public DoubleSidedCard playCard(int index) throws IllegalArgumentException {
        if(index < 0 || index > hand.size() - 1) {
            throw new IllegalArgumentException("You must play a card between 0 and " + hand.size());
        }
        DoubleSidedCard card = hand.get(index);
        storePriorState();
        hand.remove(index);
        return card;
    }

    /**
     * Clear the hand of a player in the current UNO game
     */
    public void clearHand(){
        hand.clear();
    }

    public void storePriorState(){
        this.previousHand = copyHand();
        if (previousHand == null){
            System.out.println("what");
        }
        else{
            System.out.println("running");
        }
    }

    public void undo(){
        if (previousHand == null){
            System.out.println("it's becoming null...");
        }
        ArrayList<DoubleSidedCard> tempHand = copyHand();
        //ArrayList<DoubleSidedCard> tempHand = hand;
        if (previousHand == null){
            System.out.println("issue");
        }
        hand = previousHand;
        previousHand = tempHand;
    }

    public ArrayList<DoubleSidedCard> copyHand(){
        ArrayList<DoubleSidedCard> priorHand = new ArrayList<>();
        if (hand == null){
            System.out.println("issue2");
        }
        for (DoubleSidedCard card: hand){
            priorHand.add(card);
        }
        if(priorHand == null){
            System.out.println("yooo");
        }
        return priorHand;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player c = (Player) o;

        if(c.isAI != this.isAI || c.getName() != this.getName() || c.getScore() != this.getScore()){
            return false;
        }

        if(c.getHand().size() != this.getHand().size()){
            return false;
        }

        for(int i = 0; i < this.getHand().size(); i++){
            if(!c.getHand().get(i).toString().equals(this.getHand().get(i).toString())){
                return false;
            }
        }

        return true;
    }
}