package main;

import javax.swing.*;
import java.awt.*;

public class JCardButton extends JButton {

    private Card card;

    public JCardButton(Card card) {
        if (card != null) {
            setCard(card);
        }
    }

    public void setCard(Card card) {
        this.card = card;
        this.setText(card.getSymbol().toString());
        this.setBackground(getColor(card));
    }

    public Card getCard() {
        return card;
    }

    private static Color getColor(Card card) {
        if (card.getColour() == Card.Colour.RED) {
            return new Color(215, 38, 0);
        }
        else if (card.getColour() == Card.Colour.GREEN) {
            return new Color(55, 151, 17);
        }
        else if (card.getColour() == Card.Colour.BLUE) {
            return new Color(9, 86, 191);
        }
        else if (card.getColour() == Card.Colour.YELLOW) {
            return new Color(236, 212, 7);
        }
        else {
            return new Color(20, 20, 20);
        }
    }
}