package main;

import main.cards.Card;

import java.util.ArrayList;

public class AiPlayer extends Player {

    public Game game;

    //TODO put these methods into HandController?
    //TODO figure out AI logic in more depth
    public AiPlayer(String name, ArrayList<Card> hand, boolean isAI, Game game) {
        super(name, hand, isAI);
        this.game = game;
    }

    /**
     * Return and remove a card from the Players hand given its index
     *
     * @param index The index of the card to be played from the players hand
     * @return The card at index
     */
    @Override
    public Card playCard(int index) throws IllegalArgumentException {
        if (index < 0 || index > super.getHand().size() - 1) {
            throw new IllegalArgumentException("You must play a card between 0 and " + super.getHand().size());
        }
        Card card = super.getHand().get(index);
        super.getHand().remove(index);
        return card;
    }

    /**
     * Determine which card to play based on the current top card and the AI player's current hand
     */
    public int chooseCard() {
        ArrayList<Card> playerHand = super.getHand();

        if (validCards(playerHand)) {
            Card highestCard = getHighestValidCard(playerHand);
            int highestCardIndex = super.getHand().indexOf(highestCard);
            return highestCardIndex;
        }
    }

    /**
     * Retrieve the current number of cards in the AI player's hand
     *
     * @return The current number of cards in the AI player's hand
     */
    public int getNumCards(ArrayList<Card> playerHand) {
        return playerHand.size();
    }

    /**
     * Check if the AI player has a Wild Card in their current hand
     *
     * @param playerHand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Wild Card
     */
    public boolean hasWild(ArrayList<Card> playerHand) {
        for (Card card: playerHand) {
            if (card.getSymbol().equals(Card.Symbol.WILD) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_TWO)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the AI player has a Reverse Card in their current hand
     *
     * @param playerHand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Skip Card
     */
    public boolean hasReverse(ArrayList<Card> playerHand) {
        for (Card card: playerHand) {
            if (card.getSymbol().equals(Card.Symbol.REVERSE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the AI player has a Skip Card in their current hand
     *
     * @param playerHand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Reverse Card
     */
    public boolean hasSkip(ArrayList<Card> playerHand) {
        for (Card card: playerHand) {
            if (card.getSymbol().equals(Card.Symbol.REVERSE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the AI player has a Draw One Card in their current hand
     *
     * @param playerHand the current hand of the AI player
     * @return whether the current hand of the AI player contains a Draw One Card
     */
    public boolean hasDrawOne(ArrayList<Card> playerHand) {
        for (Card card: playerHand) {
            if (card.getSymbol().equals(Card.Symbol.DRAW_ONE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether there are any valid cards in the current hand of the AI player
     *
     * @param playerHand the current hand of the AI player
     * @return Whether there is a valid card in the current hand
     */
    public boolean validCards(ArrayList<Card> playerHand) {

        Card topCard = game.getTopCard();
        Card.Symbol topCardSymbol = game.getTopCard().getSymbol();
        Card.Colour topCardColour = game.getTopCard().getColour();

        for (Card currentCard: playerHand) {

            Card.Symbol currentCardSymbol = currentCard.getSymbol();
            Card.Colour currentCardColour = currentCard.getColour();

            if (currentCardSymbol.equals(topCardSymbol) || currentCardColour.equals(topCardColour)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the highest value valid card in the AI player's hand
     *
     * @param playerHand the current hand of the AI player
     * @return the highest value valid card in the AI player's hand
     */
    public Card getHighestValidCard(ArrayList<Card> playerHand) {

        Card hiValCard = playerHand.get(0);

        Card topCard = game.getTopCard();
        Card.Symbol topCardSymbol = game.getTopCard().getSymbol();
        Card.Colour topCardColour = game.getTopCard().getColour();

        for (Card currentCard: playerHand) {

            Card.Symbol currentCardSymbol = currentCard.getSymbol();
            Card.Colour currentCardColour = currentCard.getColour();

            // determine if the current card is valid (symbol or colour match the symbol or colour of the top card)
            if (currentCardSymbol.equals(topCardSymbol) || currentCardColour.equals(topCardColour)) {

                // determine if the valid current card has a greater point value than the current highest valid card
                if (currentCard.getPointValue() > hiValCard.getPointValue()) {
                    hiValCard = currentCard;
                }
            }
        }
        return hiValCard;
    }
}
