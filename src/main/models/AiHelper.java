package main.models;

import main.models.cards.Card;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Allows the AI to determine what card to play based on the current hand, the current colour, and the current symbol
 *
 * @author Jake Siushansian, Jackie Smolkin-Lerner
 */
public class AiHelper {

    private Game currentGame;
    private Card currentTopCard;
    private ArrayList<Card> currentHand;
    private Card.Colour currentColour;
    private Card.Symbol currentSymbol;

    /**
     * Create an instance of AI Helper to play a card from the AI player's hand based on the current top card,
     * the current colour, and the symbol of the top card
     *
     * @param currentTopCard
     * @param currentHand
     * @param currentColour
     * @param currentSymbol
     */
    public AiHelper(Game currentGame, Card currentTopCard, ArrayList<Card> currentHand, Card.Colour currentColour, Card.Symbol currentSymbol) {
        this.currentGame = currentGame;
        this.currentTopCard = currentTopCard;
        this.currentHand = currentHand;
        this.currentColour = currentColour;
        this.currentSymbol = currentSymbol;
    }

    /**
     * Determine which card to play based on the current top card and the AI player's current hand
     *
     * @return the index of the highest playable card or -1 if there is no card that can be played
     */
    public int chooseCard() {

        // determine if any of the cards in the current hand (excluding wild) can be played
        boolean canPlay = anyValidCards();

        if (canPlay) {
            // there is a card in the current hand that can be played and that is not a wild card
            return getHighestValidCard();
        }
        else if (!canPlay && hasWild()) {
            // there is a card in the current hand that can be played that is a wild card
            return getWild();
        }
        else if (!canPlay && !hasWild()) {
            return -1;
        }
        return -1;
    }

    /**
     * Determine whether there are any valid cards in the current hand of the AI player
     *
     * @return Whether there is a valid card in the current hand
     */
    private boolean anyValidCards() {

        for (Card currentCard: currentHand) {
            if (currentGame.canPlayCard(currentCard)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the first valid card found in the current hand of the AI player
     *
     * @return the first valid card found in the hand
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
     * @return the index of the highest value valid card in the AI player's hand
     */
    private int getHighestValidCard() {

        Card highestValidCard = getFirstValid();

        if (highestValidCard == null) {
            return -1;
        }

        for (Card currentCard : currentHand) {

            boolean isWild = currentCard.getColour().equals(Card.Colour.WILD);
            // determine if the current card is valid (symbol or colour match the symbol or colour of the top card)
            if (currentGame.canPlayCard(currentCard) && !isWild) {

                // determine if the valid current card has a greater point value than the current highest valid card
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
     * @return the card colour that appears the most in the current hand of the AI player
     */
    public Card.Colour getMaxCardColour() {

        HashMap<Card.Colour, Integer> colourNumCards = new HashMap<>();
        colourNumCards.put(Card.Colour.RED, 0);
        colourNumCards.put(Card.Colour.BLUE, 0);
        colourNumCards.put(Card.Colour.GREEN, 0);
        colourNumCards.put(Card.Colour.YELLOW, 0);

        colourNumCards.put(Card.Colour.ORANGE, 0);
        colourNumCards.put(Card.Colour.TEAL, 0);
        colourNumCards.put(Card.Colour.PURPLE, 0);
        colourNumCards.put(Card.Colour.PINK, 0);

        Integer maxInteger = 0;
        Card.Colour maxColour = null;


        for (Card currentCard : currentHand) {
            if (currentCard.getColour().equals(Card.Colour.RED)) {
                Integer currentValue = colourNumCards.get(Card.Colour.RED);
                colourNumCards.replace(Card.Colour.RED, currentValue, currentValue+1);
            }
            else if (currentCard.getColour().equals(Card.Colour.BLUE)) {
                Integer currentValue = colourNumCards.get(Card.Colour.BLUE);
                colourNumCards.replace(Card.Colour.BLUE, currentValue, currentValue+1);
            }
            else if (currentCard.getColour().equals(Card.Colour.GREEN)) {
                Integer currentValue = colourNumCards.get(Card.Colour.GREEN);
                colourNumCards.replace(Card.Colour.GREEN, currentValue, currentValue+1);
            }
            else if (currentCard.getColour().equals(Card.Colour.YELLOW)) {
                Integer currentValue = colourNumCards.get(Card.Colour.YELLOW);
                colourNumCards.replace(Card.Colour.YELLOW, currentValue, currentValue+1);
            }
            else if (currentCard.getColour().equals(Card.Colour.ORANGE)) {
                Integer currentValue = colourNumCards.get(Card.Colour.ORANGE);
                colourNumCards.replace(Card.Colour.ORANGE, currentValue, currentValue+1);
            }
            else if (currentCard.getColour().equals(Card.Colour.TEAL)) {
                Integer currentValue = colourNumCards.get(Card.Colour.TEAL);
                colourNumCards.replace(Card.Colour.TEAL, currentValue, currentValue+1);
            }
            else if (currentCard.getColour().equals(Card.Colour.PURPLE)) {
                Integer currentValue = colourNumCards.get(Card.Colour.PURPLE);
                colourNumCards.replace(Card.Colour.PURPLE, currentValue, currentValue+1);
            }
            else if (currentCard.getColour().equals(Card.Colour.PINK)) {
                Integer currentValue = colourNumCards.get(Card.Colour.PINK);
                colourNumCards.replace(Card.Colour.PINK, currentValue, currentValue+1);
            }
        }

        for (HashMap.Entry<Card.Colour, Integer> currentColourNumCards : colourNumCards.entrySet()) {

            if (maxColour == null) {
                maxInteger = currentColourNumCards.getValue();
                maxColour = currentColourNumCards.getKey();
            }
            else if (currentColourNumCards.getValue() > maxInteger) {
                maxInteger = currentColourNumCards.getValue();
                maxColour = currentColourNumCards.getKey();
            }
        }
        return maxColour;
    }

    /**
     * Return the index of the first Wild card found in the current hand of the AI player
     *
     * @return the index of the first Wild card found in the hand
     */
    private int getWild() {
        for (Card card: currentHand) {
            if (card.getSymbol().equals(Card.Symbol.WILD) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_TWO) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_COLOUR)) {
                return currentHand.indexOf(card);
            }
        }
        return 0;
    }

    /**
     * Check if the AI player has a Wild Card in their current hand
     *
     * @return whether the current hand of the AI player contains a Wild Card
     */
    private boolean hasWild() {
        for (Card card: currentHand) {
            if (card.getColour().equals(Card.Colour.WILD)) {
                return true;
            }
        }
        return false;
    }
}
