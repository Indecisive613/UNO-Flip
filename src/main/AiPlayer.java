package main;

import main.cards.Card;

import java.util.ArrayList;

public class AiPlayer extends Player {

    //TODO put these methods into HandController?
    public AiPlayer(String name, ArrayList<Card> hand, boolean isAI) {
        super(name, hand, isAI);
    }

    /**
     * Return and remove a card from the Players hand given its index
     *
     * @param index The index of the card to be played from the players hand
     * @return The card at index
     */
    @Override
    public Card playCard(int index) throws IllegalArgumentException {

        // Check top card

        // If the colour of the top card matches the colour of a card in the hand play that card
            // Play high cards before low cards
            // Play number cards before special cards

        // If possible . . .
                // Play Draw One before Skip
                // Play Skip before Reverse

        // Else if the number of hte top card matches the number of a card in the hand play that card
            // Play high cards before low cards

        // If the player cannot play any other card, play a Wild card
            // Select the colour of the top card based on colour that appears most in the deck

    }

    /**
     * Retrieve the current number of cards in the AI player's hand
     *
     * @return The current number of cards in the AI player's hand
     */
    public int getNumCards(ArrayList<Card> hand) {
        return hand.size();
    }

    /**
     * Check if the AI player has a Wild Card in their current hand
     *
     * @param hand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Wild Card
     */
    public boolean haveWild(ArrayList<Card> hand) {
        for (Card card: hand) {
            if (card.getSymbol().equals(Card.Symbol.WILD) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_TWO)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the AI player has a Reverse Card in their current hand
     *
     * @param hand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Skip Card
     */
    public boolean hasReverse(ArrayList<Card> hand) {
        for (Card card: hand) {
            if (card.getSymbol().equals(Card.Symbol.REVERSE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the AI player has a Skip Card in their current hand
     *
     * @param hand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Reverse Card
     */
    public boolean hasSkip(ArrayList<Card> hand) {
        for (Card card: hand) {
            if (card.getSymbol().equals(Card.Symbol.REVERSE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the AI player has a Draw One Card in their current hand
     *
     * @param hand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Draw One Card
     */
    public boolean hasDrawOne(ArrayList<Card> hand) {
        for (Card card: hand) {
            if (card.getSymbol().equals(Card.Symbol.DRAW_ONE)) {
                return true;
            }
        }
        return false;
    }
}
