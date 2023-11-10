package main;

import main.cards.Card;

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
    public static final int STARTING_HAND_SIZE = 2;

    private final ArrayList<GameView> views;
    private final ArrayList<Player> players;

    private final Stack<Card> playedCards;
    private final Stack<Card> deck;

    private boolean turnOrderReversed;
    private boolean skipPlayer;
    private int currentPlayerIndex = -1; // set to -1 for first increment
    private Card.Colour currentColour;
    private boolean running = false;

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
     * @return The Deck in the game
     */
    public Stack<Card> getDeck(){
        return deck;
    }

    /**
     * @return The card on top
     */
    public Card getTopCard() {
        return playedCards.peek();
    }

    /**
     * @return the current colour of the top card
     */
    public Card.Colour getCurrentColour() { return currentColour; }

    /**
     * Set the top card
     * TODO: This is for testing, maybe should remove
     */
    public Card setTopCard(Card card) {
        currentColour = card.getColour();
        return playedCards.push(card);
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
        return this.running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Advance the turn to the next player
     */
    public void advanceTurn() {

        currentPlayerIndex = nextPlayer();
        skipPlayer = false;

        for (GameView view : views) {
            view.handleNewTurn(players.get(currentPlayerIndex));
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
     * @return Whether or not the card is wild
     */
    public boolean playCard(Card card) {
        playedCards.push(card);
        boolean isWild = card.cardAction(this);
        String message = "";
        if(!isWild){
            currentColour = card.getColour();
        }
        else{
            message = "WILD";
        }
        if (players.get(currentPlayerIndex).getHand().size() == 0){
            message = "Done game";
        }

        for (GameView view : views) {
            view.handlePlayCard(card, message);
        }
        return isWild;
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
            view.handleDrawCard(drawnCard);
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
            if (player.getScore() >= 500){
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

    /**
     * Reverses the turn order
     */
    public void reverseTurn(){
        turnOrderReversed = !turnOrderReversed;
        for (GameView view : views) {
            view.handleUpdateTurnOrder(turnOrderReversed);
        }
    }

    /**
     * Skips the next player
     */
    public void setSkipPlayer(){
        skipPlayer = true;
    }
}