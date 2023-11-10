package main;

import main.cards.Card;

/**
 * The controller for the portion of the UNO interface that displays the current player's hand
 * and the actions available for the current player
 *
 * @author Jackie Smolkin-Lerner
 */

public class HandController {
    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isValidCard(Card card) {
        return game.canPlayCard(card);
    }

    public void playCard(int index) {
        game.playCard(game.getCurrentPlayer().playCard(index));
    }

    public void drawCard() {
        game.drawCard(game.getCurrentPlayer());
    }

    public void nextTurn() {
        game.advanceTurn();
    }
}
