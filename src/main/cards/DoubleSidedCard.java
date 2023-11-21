package main.cards;

import main.Game;

/**
 * A double-sided UNO Flip card
 *
 * @author Fiona Cheng
 */
public class DoubleSidedCard {
    private Card lightSideCard;
    private Card darkSideCard;
    private Card activeSide;
    //private boolean dark = false;

    /**
     * Creates a double-sided UNO Flip card
     *
     * @param lightCard The light side of the card
     * @param darkCard The dark side of the card
     * @throws IllegalArgumentException if a card does not match its description
     */
    public DoubleSidedCard(Card lightCard, Card darkCard) throws IllegalArgumentException{
        if(lightCard.getSide() == Card.Side.LIGHT){
            this.lightSideCard = lightCard;
        } else{
            throw new IllegalArgumentException("The light side card is not valid.");
        }
        if(darkCard.getSide() == Card.Side.DARK){
            this.darkSideCard = darkCard;
        } else{
            throw new IllegalArgumentException("The dark side card is not valid." + darkCard);
        }
        activeSide = lightCard;
    }

    /**
     * @return The light side card
     */
    public Card getLightSideCard(){
        return lightSideCard;
    }

    /**
     * @return The dark side card
     */
    public Card getDarkSideCard(){
        return darkSideCard;
    }

    @Override
    public String toString(){
        return lightSideCard + " | " + darkSideCard;
    }

    public Card getActiveSide() {
        return activeSide;
    }

    public void flip() {
        if (activeSide.equals(lightSideCard)) {
            this.activeSide = darkSideCard;
        }
        else{
            this.activeSide = lightSideCard;
        }
    }
}