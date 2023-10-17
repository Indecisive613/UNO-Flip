package main;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

    public static final int PLAYER_MIN = 2;
    public static final int PLAYER_MAX = 4;
    public static final int STARTING_HAND_SIZE = 7;
    public static final int DRAW_CARD_ACTION = 0;

    private final ArrayList<GameView> views;
    private final ArrayList<Player> players;
    private final PlayedCards playedCards;
    private final Deck deck;
    private final boolean turnOrderReversed = false;
    private int currentPlayer = -1; // set to -1 for first increment


    public Game(Deck deck) {
        this.deck = deck;
        this.playedCards = new PlayedCards();
        players = new ArrayList<>();
        views = new ArrayList<>();
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

    public Card getTopCard() {
        return playedCards.seeTopCard();
    }

    public void shuffleDeck() {
        Collections.shuffle(deck.getDeck());
    }

    public void deal() {
        for (int i = 0; i < STARTING_HAND_SIZE; i++) {
            for (Player p : players) {
                p.dealCard(deck.drawCard());
            }
        }
        // Place starting card
        playedCards.dealCard(deck.drawCard());
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
        Card topCard = playedCards.seeTopCard();
        return (card == null
                || card.getColour().equals(Card.Colour.WILD)
                || card.getColour().equals(topCard.getColour())
                || card.getSymbol().equals(topCard.getSymbol()));
        // TODO: Add wild card logic (maybe store currentColour independently from the top card)
    }

    public void playCard(Card card) {
        playedCards.dealCard(card);
        for (GameView view : views) {
            view.updatePlayCard(card);
        }
    }

    public void drawCard(Player currentPlayer) {
        // TODO: Reshuffle deck & also add error checking for when deck is empty
        Card drawnCard = deck.drawCard();
        currentPlayer.dealCard(drawnCard);
        for (GameView view : views) {
            view.updateDrawCard(drawnCard);
        }
    }
}