package main;

import main.cards.Card;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Create JButtons to represent Cards in the HandView interface of the UNO game.
 * The cards have a set size and appearance.
 *
 * @author Jackie Smolkin-Lerner
 */
public class JCardButton extends JButton {

    public static final int CARD_WIDTH = 150;
    public static final int CARD_HEIGHT = CARD_WIDTH * 100/70;

    private final static Font NUMBER_CARD_FONT = new Font("Mono", Font.BOLD, 90);
    private final static Font SPECIAL_CARD_FONT = new Font("Mono", Font.BOLD, 30);

    private Card card;

    public JCardButton(Card card) {
        setFocusPainted(false);
        if (card != null) {
            setCard(card);
        }
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
    }

    /**
     * @return The contained card
     */
    public Card getCard() {
        return card;
    }

    /**
     * @param card the card that is being initialized
     */
    public void setCard(Card card) {
        this.card = card;
        setText(getText(card));

        setForeground(Color.WHITE);

        if (isNumber(card)) {
            setFont(NUMBER_CARD_FONT);
        } else {
            setFont(SPECIAL_CARD_FONT);
        }

        if (card.getColour() == Card.Colour.WILD) {
            setBackground(Color.BLACK);
        } else {
            setBackground(getColor(card));
            setBorder(BorderFactory.createCompoundBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK), new MatteBorder(7, 7, 7, 7, Color.WHITE)));
        }
    }

    /**
     * @param card An UNO card
     * @return If the card is a number card, and not a special card
     */
    private static boolean isNumber(Card card) {
        Card.Symbol symbol = card.getSymbol();
        return !(symbol == Card.Symbol.DRAW_ONE
                || symbol == Card.Symbol.WILD_DRAW_TWO
                || symbol == Card.Symbol.WILD
                || symbol == Card.Symbol.SKIP
                || symbol == Card.Symbol.REVERSE
        );
    }

    /**
     * @param card An UNO card
     * @return The textual representation of a card
     */
    private static String getText(Card card) {
        if (card.getSymbol() == Card.Symbol.DRAW_ONE) {
            return "<html>Draw" + "<br>" + "One</html>";
        } else if (card.getSymbol() == Card.Symbol.WILD) {
            return "Wild";
        } else if (card.getSymbol() == Card.Symbol.WILD_DRAW_TWO) {
            return "<html>Wild" + "<br>" + "Draw" + "<br>" + "Two</html>";
        } else if (card.getSymbol() == Card.Symbol.SKIP) {
            return "Skip";
        } else if (card.getSymbol() == Card.Symbol.REVERSE) {
            return "Reverse";
        } else {
            return Integer.toString(card.getPointValue());
        }
    }

    /**
     * @param card An UNO card
     * @return The colour of the card, or white if it's a wild card
     */
    private static Color getColor(Card card) {
        if (card.getColour() == Card.Colour.RED) {
            return new Color(215, 38, 0);
        } else if (card.getColour() == Card.Colour.GREEN) {
            return new Color(55, 151, 17);
        } else if (card.getColour() == Card.Colour.BLUE) {
            return new Color(9, 86, 191);
        } else if (card.getColour() == Card.Colour.YELLOW) {
            return new Color(236, 212, 7);
        } else {
            return new Color(255, 255, 255);
        }
    }
}