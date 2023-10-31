package main;

public class DrawOneCard extends Card{
    public DrawOneCard(Card.Colour colour) throws IllegalArgumentException{
        super(colour, Card.Symbol.DRAW_ONE);
    }
    @Override
    public boolean cardAction(Game game) {
        int nextPlayer = game.nextPlayer();
        Card drawnCard = game.getDeck().pop();

        game.getPlayers().get(nextPlayer).dealCard(drawnCard);

        return false;
    }

    public static void main(String[] args){
        GameView view = new GameDebugView();
        Game game = new Game(GameRunner.createDeck());
    }
}
