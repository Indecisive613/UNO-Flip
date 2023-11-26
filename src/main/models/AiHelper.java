package main.models;

import main.models.cards.Card;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Allows the AI to determine what card to play based on the current state of the game, including the current hand of the
 * player, the current game colour, and the current game symbol
 *
 * @author Jake Siushansian, Jackie Smolkin-Lerner, Fiona Cheng
 */
public class AiHelper {
    private Game currentGame;
    private ArrayList<Card> currentHand;

    /**
     * Create an instance of AI Helper to play a card from the AI player's hand based on the current state of the game
     *
     * @param currentGame the current UNO game
     * @param currentHand the AI player's active hand
     */
    public AiHelper(Game currentGame, ArrayList<Card> currentHand) {
        this.currentGame = currentGame;
        this.currentHand = currentHand;
    }

    /**
     * @return the current UNO game
     */
    public Game getCurrentGame(){
        return currentGame;
    }

    /**
     * @return the hand of the AI player
     */
    public ArrayList<Card> getCurrentHand(){
        return currentHand;
    }

    /**
     * Determine which card to play based on the current top card and the AI player's current hand
     *
     * @return the index of the highest playable card in the current hand or -1 if there is no card that can be played
     */
    public int getAiAction() {

        // determine if any of the cards in the current hand (excluding wild) can be played
        if (hasValidNonWildCards()) {
            // there is a card in the current hand that can be played and that is not a wild card
            return getHighestValidCard();
        }
        else if (hasWild()) {
            // there is a card in the current hand that can be played that is a wild card
            return getWild();
        }
        // there are no cards in the current hand that can be played
        return -1;
    }

    /**
     * Determine whether there are any valid cards in the current hand of the AI player
     *
     * @return Whether there is a valid card in the current hand
     */
    private boolean hasValidNonWildCards() {
        for (Card currentCard: currentHand) {
            if (currentGame.canPlayCard(currentCard) && currentCard.getColour() != Card.Colour.WILD) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the first valid card found in the current hand of the AI player
     *
     * @return the first valid card found in the current hand
     */
    private Card getFirstValid() {
        for (Card card: currentHand) {
            boolean isWild = card.getColour().equals(Card.Colour.WILD);
            if (currentGame.canPlayCard(card) && !isWild) {
                return card;
            }
        }
        return null;
    }

    /**
     * Return the index of the highest value valid card (excluding wild cards) in the AI player's hand
     *
     * @return the index of the highest value valid card in the current hand or -1 if there is no card that can be played
     */
    private int getHighestValidCard() {

        // initially assign the first valid card as the highest valid card
        // the value of the next valid card in the hand can be compared against this card
        Card highestValidCard = getFirstValid();

        // if there are no valid cards in the hand, then return -1
        if (highestValidCard == null) {
            return -1;
        }

        for (Card currentCard : currentHand) {
            // determine if the current card is a wild card
            boolean isWild = currentCard.getColour().equals(Card.Colour.WILD);

            // determine if the current card is valid
            // (the symbol or the colour of the current card match the symbol or the colour of the top card)
            if (currentGame.canPlayCard(currentCard) && !isWild) {

                // determine if the valid current card has a greater value than the current highest valid card
                if (currentCard.getPointValue() > highestValidCard.getPointValue()) {
                    highestValidCard = currentCard;
                }
            }
        }
        return currentHand.indexOf(highestValidCard);
    }

    /**
     * Return the card colour that appears the most in the current hand of the AI player
     *
     * @return the card colour that appears the most in the current hand
     */
    public Card.Colour getMostCommonColour() {

        // create a hash map to store the number of cards of each colour that are present in the current hand
        HashMap<Card.Colour, Integer> colourNumCards = new HashMap<>();
        for (Card.Colour colour : Card.Colour.values()) {
            colourNumCards.put(colour, 0);
        }

        // identify the colour of the current card in the current hand and update the count of that colour in the hash map
        for (Card currentCard : currentHand) {
            colourNumCards.put(currentCard.getColour(), colourNumCards.get(currentCard.getColour()) + 1);
        }

        int count = 0;
        Card.Colour mostCommonColour;

        // assign RED and TEAL as the default most common colours in the event that there is no most common colour
        if(currentGame.getTopCard().getSide() == Card.Side.LIGHT) {
            mostCommonColour = Card.Colour.RED;
        } else{
            mostCommonColour = Card.Colour.TEAL;
        }

        // identify the most common card colour in the current hand, excluding wild as a colour
        for (Card.Colour colour : Card.Colour.values()) {
            int colourCount = colourNumCards.get(colour);
            if (colourCount > count && (colour != Card.Colour.WILD)) {
                count = colourCount;
                mostCommonColour = colour;
            }
        }

        return mostCommonColour;
    }

    /**
     * Return the index of the first Wild card found in the current hand of the AI player
     *
     * @return the index of the first Wild card found in the current hand
     */
    private int getWild() {
        for (Card card: currentHand) {
            if (card.getColour() == Card.Colour.WILD) {
                return currentHand.indexOf(card);
            }
        }
        return -1;
    }

    /**
     * Check if the AI player has a Wild Card in their current hand
     *
     * @return whether the current hand contains a Wild Card
     */
    private boolean hasWild() {
        for (Card card: currentHand) {
            if (card.getColour() == Card.Colour.WILD) {
                return true;
            }
        }
        return false;
    }
}
