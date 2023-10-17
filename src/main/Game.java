package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class Game {

    public static final int PLAYER_MIN = 2;
    public static final int PLAYER_MAX = 4;
    public static final int STARTING_HAND_SIZE = 7;
    public static final int DRAW_CARD_ACTION = 0;

    private final ArrayList<GameView> views;
    private final ArrayList<Player> players;
    private final Stack<Card> playedCards;

    private final Stack<Card> deck;
    private final boolean turnOrderReversed = false;
    private int currentPlayer = -1; // set to -1 for first increment


    public Game(Stack<Card> deck) {
        this.deck = deck;
        this.playedCards = new Stack<Card>();
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
        return playedCards.peek();
    }

    public void shuffleDeck() {
        //Puts cards in played cards into discard and reshuffles
        if(!playedCards.isEmpty()) {
            Card topCard = playedCards.pop();
            while (!playedCards.isEmpty()) {
                deck.push(playedCards.pop());
            }
            playedCards.push(topCard);
        }
        Collections.shuffle(deck);
    }

    public void deal() {
        for (int i = 0; i < STARTING_HAND_SIZE; i++) {
            for (Player p : players) {
                if(deck.isEmpty()){
                    shuffleDeck();
                }
                p.dealCard(deck.pop());
            }
        }
        // Place starting card
        if(deck.isEmpty()){
            shuffleDeck();
        }
        playedCards.push(deck.pop());
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
        Card topCard = playedCards.peek();
        return (card == null
                || card.getColour().equals(Card.Colour.WILD)
                || card.getColour().equals(topCard.getColour())
                || card.getSymbol().equals(topCard.getSymbol()));
        // TODO: Add wild card logic (maybe store currentColour independently from the top card)
    }

    public void playCard(Card card) {
        playedCards.push(card);
        for (GameView view : views) {
            view.updatePlayCard(card);
        }
    }

    public void drawCard(Player currentPlayer) {
        if(deck.isEmpty()){
            shuffleDeck();
        }
        Card drawnCard = deck.pop();
        currentPlayer.dealCard(drawnCard);
        for (GameView view : views) {
            view.updateDrawCard(drawnCard);
        }
    }
}