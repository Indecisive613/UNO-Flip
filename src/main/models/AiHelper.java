package main.models;

import main.models.cards.Card;
import main.models.cards.DoubleSidedCard;

import java.util.ArrayList;

public class AiHelper {

    //TODO put these methods into HandController?
    //TODO figure out AI logic in more depth

    private Card currentTopCard;
    private ArrayList<DoubleSidedCard> currentHand;
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
    public AiHelper(Card currentTopCard, ArrayList<DoubleSidedCard> currentHand, Card.Colour currentColour, Card.Symbol currentSymbol) {
        this.currentTopCard = currentTopCard;
        this.currentHand = currentHand;
        this.currentColour = currentColour;
        this.currentSymbol = currentSymbol;
    }

    /**
     * Determine which card to play based on the current top card and the AI player's current hand
     *
     * @param playerHand the current hand of the AI player
     * @return the index of the highest playable card or -1 if there is no card that can be played
     */
    public int chooseCard(ArrayList<Card> playerHand) {

        // determine if any of the cards in the current hand (excluding wild) can be played
        boolean canPlay = validCard(playerHand);

        if (canPlay) {
            // there is a card in the current hand that can be played and that is not a wild card
            if (!hasWild(playerHand)) {
                return getHighestValidCard(playerHand);
            }
            // there is a card in the current hand that can be played that is a wild card
            else if (hasWild(playerHand)) {
                return getWild(playerHand);
            }

        }
        return -1;
    }

    /**
     * Determine whether there are any valid cards (excluding wilds) in the current hand of the AI player
     *
     * @param playerHand the current hand of the AI player
     * @return Whether there is a valid card in the current hand
     */
    public boolean validCard(ArrayList<Card> playerHand) {

        // compare the current card in the player's hand to the current colour and the current symbol in the game
        Card.Symbol topCardSymbol = currentSymbol;
        Card.Colour topCardColour = currentColour;

        // iterate through the current hand of the player
        for (Card currentCard: playerHand) {

            Card.Symbol currentCardSymbol = currentCard.getSymbol();
            Card.Colour currentCardColour = currentCard.getColour();

            // check whether the current card is a wild card
            if (isWild(currentCard)) {
                return false;
            }
            // the current card is not a wild card
            else {
                // the current card can be played on the current top card
                if (currentCardSymbol.equals(topCardSymbol) || currentCardColour.equals(topCardColour)) {
                    return true;
                }
            }
        }
        // there are no cards (excluding wild cards) in the current hand that can be played on the current top card
        return false;
    }

    /**
     * Check if the current card in the current hand can be played on the top card
     *
     * @param currentCard The current card that is being evaluated
     * @return Whether the current card can be played on the top card
     */
    public boolean isValid(Card currentCard) {
        if (currentCard.getColour().equals(currentColour) || currentCard.getSymbol().equals(currentSymbol)) {
            return true;
        }
        return false;
    }

    /**
     * Return the first valid card found in the current hand of the AI player
     *
     * @param playerHand the current card in the current hand of the AI player
     * @return the first valid card found in the hand
     */
    public Card getValid(ArrayList<Card> playerHand) {
        for (Card card: playerHand) {
            if (isValid(card) && !isWild(card)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Check if the current card in the current hand of the AI player is a Wild card
     *
     * @param currentCard the current card in the current hand of the AI player
     * @return Whether the current card is a Wild card
     */
    public boolean isWild(Card currentCard) {
        Card.Symbol currentCardSymbol = currentCard.getSymbol();

        if (currentCardSymbol.equals(Card.Symbol.WILD)) { return true; }
        else if (currentCardSymbol.equals(Card.Symbol.WILD_DRAW_TWO)) { return true; }
        else if (currentCardSymbol.equals(Card.Symbol.WILD_DRAW_COLOUR)) { return true; }
        else { return false; }
    }

    /**
     * Return the index of the first Wild card found in the current hand of the AI player
     *
     * @param playerHand the current hand of the AI player
     * @return the index of the first Wild card found in the hand
     */
    public int getWild(ArrayList<Card> playerHand) {
        for (Card card: playerHand) {
            if (card.getSymbol().equals(Card.Symbol.WILD) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_TWO) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_COLOUR)) {
                return playerHand.indexOf(card);
            }
        }
        return 0;
    }

    /**
     * Check if the AI player has a Wild Card in their current hand
     *
     * @param playerHand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Wild Card
     */
    public boolean hasWild(ArrayList<Card> playerHand) {
        for (Card card: playerHand) {
            if (card.getSymbol().equals(Card.Symbol.WILD) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_TWO) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_COLOUR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the index of the highest value valid card (excluding wild cards) in the AI player's hand
     *
     * @param playerHand the current hand of the AI player
     * @return the index of the highest value valid card in the AI player's hand
     */
    public int getHighestValidCard(ArrayList<Card> playerHand) {

        Card highestValidCard = getValid(playerHand);

        if (highestValidCard == null) {
            return -1;
        }

        for (Card currentCard : playerHand) {

            // determine if the current card is valid (symbol or colour match the symbol or colour of the top card)
            if (isValid(currentCard) && !isWild(currentCard)) {

                // determine if the valid current card has a greater point value than the current highest valid card
                if (currentCard.getPointValue() > highestValidCard.getPointValue()) {
                    highestValidCard = currentCard;
                }
            }
        }
        return playerHand.indexOf(highestValidCard);
    }
}
