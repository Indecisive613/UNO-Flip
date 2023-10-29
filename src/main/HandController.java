package main;

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

    public void playCard(Card card) {
        game.playCard(card);
        //game.getCurrentPlayer().playCard(); TODO: Remove card from player
    }
}
