package main;

import main.cards.Card;
import main.cards.DoubleSidedCard;

import java.util.*;

/**
 * A light side UNO Flip game that has players, cards, and contains the logic required to play a game of light side UNO Flip.
 *
 * @author Fiona Cheng, Jackie Smolkin-Lerner, Anand Balaram, Jake Siushansian
 */
public class Game {

    public static final int PLAYER_MIN = 2;
    public static final int PLAYER_MAX = 4;
    public static final int STARTING_HAND_SIZE = 7;
    public static final int POINTS_TO_WIN = 500;

    private final ArrayList<GameView> views;
    private final ArrayList<Player> players;

    private Stack<DoubleSidedCard> playedCards;
    private Stack<DoubleSidedCard> deck;

    private boolean turnOrderReversed;
    private boolean skipPlayer;
    private boolean skipEveryone;
    private int currentPlayerIndex = -1; // set to -1 for first increment
    private Card.Colour currentColour;
    private boolean running = false;
    private int roundNumber;
    private boolean dark = false;

    /**
     * Create a new UNO Game given a deck of cards
     *
     * @param deck The decks of cards to use in this game
     */
    public Game(Stack<DoubleSidedCard> deck) {
        this.deck = deck;
        this.playedCards = new Stack<DoubleSidedCard>();
        players = new ArrayList<>();
        views = new ArrayList<>();
        turnOrderReversed = false;
        skipPlayer = false;
        skipEveryone = false;
        roundNumber = 0;
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
            for (Card card : player.getActiveHand()) {
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
    public Stack<DoubleSidedCard> getDeck(){
        return deck;
    }

    /**
     * @return The card on top
     */
    public Card getTopCard() {
        return playedCards.peek().getActiveSide();
    }

    /**
     * @return the current colour of the top card
     */
    public Card.Colour getCurrentColour() { return currentColour; }

    /**
     * @return the current symbol of the top card
     */
    public Card.Symbol getCurrentSymbol() { return getTopCard().getSymbol(); }

    /**
     * Add a new Player to the Game
     *
     * @param player The Player to add
     */
    public void addPlayer(Player player) {

        if (player.getIsAI()) {
            AiPlayer aiPlayer = new AiPlayer (player.getName(), player.getHand(), player.getIsAI(), this);
            players.add(aiPlayer);
        }
        else {
            players.add(player);
        }

        //players.add(player);
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
            DoubleSidedCard topCard = playedCards.pop();
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
        DoubleSidedCard topCard = deck.pop();
        playedCards.push(topCard);
        currentColour = topCard.getActiveSide().getColour();
    }

    /**
     * @return If the game is running
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     *
     * @param running the status of whether the game is running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Advance the turn to the next player
     */
    public void advanceTurn() {
        if(hasWonRound()){
            for (GameView view : views) {
                view.handleNewTurn(players.get(currentPlayerIndex));
            }
            return;
        }
        currentPlayerIndex = nextPlayer();
        skipPlayer = false;
        skipEveryone = false;

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
        Card topCard = playedCards.peek().getActiveSide();

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
    public boolean playCard(DoubleSidedCard card) {
        playedCards.push(card);
        Card activeSide = card.getActiveSide();
        boolean isWild = activeSide.cardAction(this);
        String message = "";
        if(!isWild){
            currentColour = activeSide.getColour();
        }
        else{
            message = "WILD";
        }

        for (GameView view : views) {
            view.handlePlayCard(activeSide, message);
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
        DoubleSidedCard drawnCard = deck.pop();
        player.dealCard(drawnCard);
        for (GameView view : views) {
            view.handleDrawCard(drawnCard.getActiveSide());
        }
    }

    /**
     * Determines the next Player
     *
     * @return The index of the next Player
     */
    public int nextPlayer(){
        int playerCount = players.size();

        if(skipEveryone) {
            return currentPlayerIndex;
        }
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
            if (player.getScore() >= POINTS_TO_WIN){
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
    public void addToDeck(ArrayList<DoubleSidedCard> hand){
        for (DoubleSidedCard card: hand){
            deck.push(card);
        }
    }

    /**
     * Resets the state of the game
     */
    public void resetGame(){
        roundNumber++;
        Stack<DoubleSidedCard> deck = GameRunner.createDoubleSidedDeck();
        setDeck(deck);
        this.playedCards = new Stack<DoubleSidedCard>();
        turnOrderReversed = false;
        skipPlayer = false;
        skipEveryone = false;
        currentPlayerIndex = -1;
        for (Player player: players){
            player.clearHand();
        }

        for (GameView view : views) {
            view.handleNewGame();
            view.handleUpdateTurnOrder(false);
        }
        shuffleDeck();
        dealCards();
        advanceTurn();
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

    /**
     * Skips everyone
     */
    public void setSkipEveryone(){
        skipEveryone = true;
    }

    /**
     * @return The number of the current round
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /** Sets the deck of cards to be used in play
     * @param deck The deck of cards
     */
    public void setDeck(Stack<DoubleSidedCard> deck){
        this.deck = deck;
    }

    /**
     * @return If the current player has won
     */
    public boolean hasWonRound(){
        return currentPlayerIndex != -1 && players.get(currentPlayerIndex).getHand().size() == 0;
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public void flip(){
        dark = !dark;
        for (Player player:players){
            player.flip();
        }
        flipStack(deck);
        flipStack(playedCards);
    }
    private void flipStack(Stack<DoubleSidedCard> stack){
        Queue<DoubleSidedCard> queue = new LinkedList<>();
        while(!stack.isEmpty()){
            queue.add(stack.pop());
        }
        while(!queue.isEmpty()){
            stack.add(queue.remove());
        }
    }
}