package main;

import main.cards.Card;

public class HandController {
    private Game game;

    public HandController() {

    }

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
