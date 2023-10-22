package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * A light side UNO Flip game that has players, cards, and contains the logic required to play a game of light side UNO Flip.
 *
 * @author Fiona Cheng, Jackie Smolkin-Lerner, Anand Balaram
 */
public class Game {

    public static final int PLAYER_MIN = 2;
    public static final int PLAYER_MAX = 4;
    public static final int STARTING_HAND_SIZE = 7;
    public static final int DRAW_CARD_ACTION = 0;

    private final ArrayList<GameView> views;
    private final ArrayList<Player> players;

    private final Stack<Card> playedCards;
    private final Stack<Card> deck;

    private boolean turnOrderReversed;
    private boolean skipPlayer;
    private int currentPlayerIndex = -1; // set to -1 for first increment
    private Card.Colour currentColour;

    /**
     * Create a new UNO Game given a deck of cards
     *
     * @param deck The decks of cards to use in this game
     */
    public Game(Stack<Card> deck) {
        this.deck = deck;
        this.playedCards = new Stack<Card>();
        players = new ArrayList<>();
        views = new ArrayList<>();
        turnOrderReversed = false;
        skipPlayer = false;
    }

    /**
     * @return The Player whose turn it is
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * @return The score of the current player
     */
    public int getCurrentPlayerScore(){
        return players.get(currentPlayerIndex).getScore();
    }

    /**
     * Return the score of the current Player assuming they have zero cards in their hand.
     * This is calculated by summing the values of all other Player's card.
     *
     * @return The score of the current Player
     */
    public int getCurrentScore() {
        int score = 0;
        for (Player player : players) {
            for (Card card : player.getHand()) {
                score += card.getPointValue();
            }
        }

        return score;
    }

    /**
     * @return The Players in the game
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**
     * @return The card on top
     */
    public Card getTopCard() {
        return playedCards.peek();
    }

    /**
     * Add a new Player to the Game
     *
     * @param player The Player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Add a GameView to display game info
     *
     * @param view The GameView
     */
    public void addView(GameView view) {
        views.add(view);
    }

    /**
     * Shuffle the deck of cards
     */
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

    /**
     * Deal STARTING_HAND_SIZE cards to each player
     */
    public void dealCards() {
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

    /**
     * @return If the game is running
     */
    public boolean isRunning() {
        return true;
    }

    /**
     * Advance the turn to the next player
     */
    public void advanceTurn() {
        currentPlayerIndex = nextPlayer();
        skipPlayer = false;

        for (GameView view : views) {
            view.updateNewTurn(players.get(currentPlayerIndex));
        }
    }

    /**
     * Return if a given Card can legally be played on the current top card
     *
     * @param card The card to be played
     * @return If the given Card can be played
     */
    public boolean canPlayCard(Card card) {
        Card topCard = playedCards.peek();

        return (card == null
                || card.getColour().equals(Card.Colour.WILD)
                || card.getColour().equals(currentColour)
                || card.getSymbol().equals(topCard.getSymbol())
                || currentColour.equals(Card.Colour.WILD));
    }

    /**
     * Play a given card and determine its effect
     *
     * @param card The card to play
     * @return An integer that indicates if and which action card was played
     */
    public boolean playCard(Card card) {
        playedCards.push(card);
        boolean wild = false;
        String additionalMessage = "";
        if (!card.getColour().equals(Card.Colour.WILD)){
            currentColour = card.getColour();
        }
        else{
            wild = true;
        }
        if (card.getSymbol().equals(Card.Symbol.DRAW_ONE)){
            int nextPlayer = nextPlayer();
            Card drawn = deck.pop();
            players.get(nextPlayer).dealCard(drawn);
            additionalMessage += players.get(nextPlayer).getName() + " has to draw a card due to " + card + ": " + drawn;
        }
        else if (card.getSymbol().equals(Card.Symbol.SKIP)){
            skipPlayer = true;
        }
        else if (card.getSymbol().equals(Card.Symbol.REVERSE)){
            turnOrderReversed = !turnOrderReversed;
        }
        else if (card.getSymbol().equals(Card.Symbol.WILD_DRAW_TWO)){
            int nextPlayer = nextPlayer();
            skipPlayer = true;
            Card drawn1 = deck.pop();
            Card drawn2 = deck.pop();
            players.get(nextPlayer).dealCard(drawn1);
            players.get(nextPlayer).dealCard(drawn2);
            additionalMessage += players.get(nextPlayer).getName() + " has to draw two cards due to " + card + ": " + drawn1 + ", " + drawn2;
        }
        for (GameView view : views) {
            view.updatePlayCard(card, additionalMessage);
        }
        return wild;
    }

    /**
     * Deal a card from the deck to a given Player
     *
     * @param player The Player whose turn it is
     */
    public void drawCard(Player player) {
        if(deck.isEmpty()){
            shuffleDeck();
        }
        Card drawnCard = deck.pop();
        player.dealCard(drawnCard);
        for (GameView view : views) {
            view.updateDrawCard(drawnCard);
        }
    }

    /**
     * Determines the next Player
     *
     * @return The index of the next Player
     */
    public int nextPlayer(){
        int playerCount = players.size();

        if (turnOrderReversed && skipPlayer) {
            return ((currentPlayerIndex - 2) % playerCount + playerCount) % playerCount;
        }
        else if (turnOrderReversed) {
            return ((currentPlayerIndex - 1) % playerCount + playerCount) % playerCount;
        }
        else if (skipPlayer){
            return (currentPlayerIndex + 2) % playerCount;
        }
        else {
            return (currentPlayerIndex + 1) % playerCount;
        }
    }

    /**
     * Return whether or not a Player has won the game
     *
     * @return Whether or not a Player has won the game
     */
    public boolean hasWonGame(){
        for (Player player : players){
            if (player.getScore() >= 200){
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the score for the current player
     */
    public void assignScore(){
        players.get(currentPlayerIndex).incrementScore(getCurrentScore());
    }

    /**
     * Sets the current colour
     */
    public void setCurrentColour(Card.Colour currentColour) {
        this.currentColour = currentColour;
    }

    /**
     * Puts all the cards in a given hand back into the deck
     */
    public void addToDeck(ArrayList<Card> hand){
        for (Card card: hand){
            deck.push(card);
        }
    }

    /**
     * Resets the state of the game
     */
    public void resetGame(){
        turnOrderReversed = false;
        skipPlayer = false;
        currentPlayerIndex = -1;
    }
}