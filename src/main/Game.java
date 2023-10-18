package main;

import java.util.ArrayList;
import java.util.Collections;
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
    private boolean turnOrderReversed = false;
    private boolean skipPlayer = false;
    private int currentPlayer = -1; // set to -1 for first increment
    private Card.Colour currentColour;


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
        Card topCard = deck.pop();
        playedCards.push(topCard);
        currentColour = topCard.getColour();
    }

    public boolean isRunning() {
        return true;
    }

    public void nextTurn() {
        if (turnOrderReversed) {
            currentPlayer = (currentPlayer - 1) % players.size();
            turnOrderReversed = false;
        }
        else if (skipPlayer){
            currentPlayer = (currentPlayer + 2) & players.size();
            skipPlayer = false;
        }
        else {
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
                || card.getColour().equals(currentColour)
                || card.getSymbol().equals(topCard.getSymbol())
                || currentColour.equals(Card.Colour.WILD));
        // TODO: Add wild card logic (maybe store currentColour independently from the top card)
    }

    public void playCard(Card card) {
        playedCards.push(card);
        if (!card.getColour().equals(Card.Colour.WILD)){
            currentColour = card.getColour();
        }
        if (card.getSymbol().equals(Card.Symbol.DRAW_ONE)){
            int nextPlayer = (currentPlayer + 1) % players.size();
            players.get(nextPlayer).dealCard(deck.pop());
        }
        else if (card.getSymbol().equals(Card.Symbol.SKIP)){
            skipPlayer = true;
        }
        else if (card.getSymbol().equals(Card.Symbol.REVERSE)){
            turnOrderReversed = true;
        }
        else if (card.getSymbol().equals(Card.Symbol.WILD)){
            currentColour = Card.Colour.WILD; // TODO: in game controller implement a select colour method for user to chose colour
        }
        else if (card.getSymbol().equals(Card.Symbol.WILD_DRAW_TWO)){
            currentColour = Card.Colour.WILD;
            int nextPlayer = (currentPlayer + 1) % players.size();
            players.get(nextPlayer).dealCard(deck.pop());
            players.get(nextPlayer).dealCard(deck.pop());
        }
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