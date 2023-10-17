package main;

import java.util.ArrayList;

public class Game {

    public static final int PLAYER_MIN = 2;
    public static final int PLAYER_MAX = 4;
    public static final int STARTING_HAND_SIZE = 7;
    public static final int DRAW_CARD_ACTION = 0;

    private final ArrayList<GameView> views;
    private final ArrayList<Player> players;
    private final Deck deck;
    private final boolean turnOrderReversed = false;
    private int currentPlayer = -1; // set to -1 for first increment
    private final PlayedCards playedCards;


    public Game(Deck deck) {
        this.deck = deck;
        players = new ArrayList<>();
        views = new ArrayList<>();
        playedCards = new PlayedCards();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void addView(GameView view) {
        views.add(view);
    }

    public void shuffleDeck() {
    }

    public void deal() {
        for (int i = 0; i < STARTING_HAND_SIZE; i++) {
            for (Player p : players) {
                p.dealCard(deck.drawCard());
            }
        }
    }

    public boolean isRunning() {
        return true;
    }

    public void nextTurn() {
        if (turnOrderReversed) {
            currentPlayer = (currentPlayer - 1) % players.size();
        } else {
            currentPlayer = (currentPlayer + 1) % players.size();
        }
        for (GameView view : views) {
            view.updateNewTurn(players.get(currentPlayer));
        }
    }

    public boolean canPlay(Card card) {
        return true; // TODO: implement
    }

    public void playCard(Card card, Player player) {
        Card topCard = playedCards.seeTopCard();
        if (card.getSymbol().equals(Card.Symbol.WILD) | topCard.getColour().equals(card.getColour()) | topCard.getSymbol().equals(card.getSymbol())) {
            playedCards.dealCard(card);
            for (GameView view : views) {
                view.updatePlayCard(card);
            }
        }
        else{
            player.dealCard(card);
            for (GameView view: views){
                view.updateCardDoesntMatch();
            }
        }
    }

    public void drawCard(Player currentPlayer) {
        Card drawnCard = deck.drawCard();
        currentPlayer.dealCard(drawnCard);
        for (GameView view : views) {
            view.updateDrawCard(drawnCard);
        }
    }
}
