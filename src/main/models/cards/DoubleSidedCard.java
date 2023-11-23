package main.models.cards;

/**
 * A double-sided UNO Flip card
 *
 * @author Fiona Cheng
 */
public class DoubleSidedCard {
    private final Card lightSideCard;
    private final Card darkSideCard;
    private static Card.Side activeSide = Card.Side.LIGHT;

    /**
     * Creates a double-sided UNO Flip card
     *
     * @param lightCard The light side of the card
     * @param darkCard The dark side of the card
     * @throws IllegalArgumentException if a card does not match its description
     */
    public DoubleSidedCard(Card lightCard, Card darkCard) throws IllegalArgumentException{
        if(lightCard.getSide() != Card.Side.LIGHT){
            throw new IllegalArgumentException("The light side card is not valid: " + lightCard);
        }
        if(darkCard.getSide() != Card.Side.DARK){
            throw new IllegalArgumentException("The dark side card is not valid: " + darkCard);
        }

        this.lightSideCard = lightCard;
        this.darkSideCard = darkCard;
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


    /**
     * @return The Card on the active side (Light or Dark)
     */
    public Card getActiveSide() {
        if (activeSide == Card.Side.LIGHT) {
            return getLightSideCard();
        } else {
            return getDarkSideCard();
        }
    }

    /**
     * Flip the side of all Cards
     */
    public static void flip() {
        if (activeSide == Card.Side.LIGHT) {
            DoubleSidedCard.activeSide = Card.Side.DARK;
        }
        else{
            DoubleSidedCard.activeSide = Card.Side.LIGHT;
        }
    }

    @Override
    public String toString(){
        return lightSideCard + " | " + darkSideCard;
    }
}