package main;

import main.cards.Card;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static main.cards.Card.Side.LIGHT;

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
        return switch (card.getSymbol()) {
            case DRAW_ONE -> "<html>Draw" + "<br>" + "One</html>";
            case WILD -> "Wild";
            case WILD_DRAW_TWO -> "<html>Wild" + "<br>" + "Draw" + "<br>" + "Two</html>";
            case SKIP -> "Skip";
            case REVERSE -> "Reverse";
            default -> Integer.toString(card.getPointValue());
        };
    }

    /**
     * @param card An UNO card
     * @return The colour of the card, or white if it's a wild card
     */
    private static Color getColor(Card card) {
        if (card.getSide() == LIGHT) {
            return switch(card.getColour()) {
                case RED -> new Color(215, 38, 0);
                case GREEN -> new Color(55, 151, 17);
                case BLUE -> new Color(9, 86, 191);
                case YELLOW -> new Color(236, 212, 7);
                default -> new Color(255, 255, 255);
            };
        } else {
            return switch (card.getColour()) {
                case PINK -> Color.PINK;
                case TEAL -> new Color(33, 150, 174);
                case PURPLE -> new Color(60, 0, 45);
                case ORANGE -> new Color(220, 100, 0);
                default -> new Color(255, 255, 255);
            };
        }
    }
}