package main;

import java.util.ArrayList;

public class GameView {

    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    private void updateStartGame() {
    }

    private void updatePlayedCard(Card card) {
        System.out.printf("Played: %s\n", card);
    }

    private void updateInvalidInput() {
        System.out.println("Invalid input. Please enter a valid number.");
    }

    private void updateInvalidChoice() {
        System.out.println("Invalid choice. Try again.");
    }

    private void updateCardDoesntMatch(Card card) {
        System.out.println("Card doesn't match the top card. Try again.");
    }

    public void updateNewTurn(Player player) {
        System.out.printf("%s's Turn.\n", player.getName());
        System.out.printf("Current side: %s.\n", "Light");
        System.out.println("Your cards:");

        ArrayList<Card> playersHand = player.getHand();
        for (int i = 0; i < playersHand.size(); i++) {
            System.out.println(i + 1 + ". " + playersHand.get(i));
        }
        System.out.println();
        System.out.printf("Top card %s.\n", "YELLOW SKIP");
        System.out.println("Enter card index to play or 0 to draw a card:");

    }

    public static void main(String[] args) {
        Game g = new Game();
        ArrayList<Card> h = new ArrayList<>();
        h.add(new Card(Card.Colour.BLUE, Card.Symbol.EIGHT));
        h.add(new Card(Card.Colour.WILD, Card.Symbol.WILD_DRAW_TWO));
        h.add(new Card(Card.Colour.BLUE, Card.Symbol.DRAW_ONE));
        h.add(new Card(Card.Colour.RED, Card.Symbol.REVERSE));
        h.add(new Card(Card.Colour.RED, Card.Symbol.REVERSE));
        h.add(new Card(Card.Colour.BLUE, Card.Symbol.DRAW_ONE));
        h.add(new Card(Card.Colour.RED, Card.Symbol.REVERSE));
        Player p = new Player("Test guy 1", h);


        GameView v = new GameView();
        v.updateNewTurn(p);
    }
}
