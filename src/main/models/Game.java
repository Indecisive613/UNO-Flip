package main.models;

import main.models.cards.Card;
import main.models.cards.DoubleSidedCard;
import main.views.GameView;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * A light side UNO Flip game that has players, cards, and contains the logic required to play a game of light side UNO Flip.
 *
 * @author Fiona Cheng, Jackie Smolkin-Lerner, Anand Balaram, Jake Siushansian
 */
public class Game implements Serializable {

    public static final int PLAYER_MIN = 2;
    public static final int PLAYER_MAX = 4;
    public static final int STARTING_HAND_SIZE = 7;
    public static final int POINTS_TO_WIN = 500;

    private transient ArrayList<GameView> views;
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
    private boolean hasDrawnCard = false;
    private boolean hasPlayedCard = false;

    private boolean canRedo = false;
    private Game previousState;

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
        hasDrawnCard = false;
        hasPlayedCard = false;
        canRedo = false;
        previousState = null;
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
        return playedCards.peek().getActiveCard();
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
     * @return the current turn order of the game
     */
    public boolean getTurnOrderReversed() { return turnOrderReversed; }

    /**
     * @return whether a card has been drawn on this turn
     */
    public boolean getHasDrawnCard() { return hasDrawnCard; }

    /**
     * @return whether a card has been played on this turn
     */
    public boolean getHasPlayedCard() { return hasPlayedCard; }

    /**
     * @return whether a player can redo
     */
    public boolean getCanRedo() { return canRedo; }

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
    public void dealCards(int hand_size) {
        for (int i = 0; i < hand_size; i++) {
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
        currentColour = topCard.getActiveCard().getColour();
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

    /** Update the Game with new Views, and update each View
     *
     * @param views The list of Views
     */
    public void updateViews(ArrayList<GameView> views) {
        this.views = views;
        if (isDark() && DoubleSidedCard.getActiveSide() == Card.Side.LIGHT) {
            DoubleSidedCard.flip();
        }
        this.views.forEach(view -> view.handleNewTurn(getCurrentPlayer()));
    }

    public ArrayList<GameView> getViews() {
        return views;
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
        hasDrawnCard = false;
        hasPlayedCard = false;
        canRedo = false;

        storePriorState();
        for (Player player: players){
            player.storePriorState();
        }

        for (GameView view : views) {
            view.handleNewTurn(players.get(currentPlayerIndex));
        }
        if (getCurrentPlayer().getIsAI()) {
            DoubleSidedCard playedCard = null;
            AiHelper aiHelper = new AiHelper(this, getCurrentPlayer().getActiveHand());
            int action = aiHelper.getAiAction();
            boolean drewCard = false;
            Card activeCard = null;

            if (action == -1) {
                drawCard(getCurrentPlayer(), false);
                drewCard = true;
                setHasDrawnCard();

                // the AI player can check if their drawn card can be played
                AiHelper aiHelperDraw = new AiHelper(this, getCurrentPlayer().getActiveHand());
                int secondAction = aiHelperDraw.getAiAction();

                if (secondAction != -1) {
                    playedCard = getCurrentPlayer().playCard(secondAction);
                    activeCard = playedCard.getActiveCard();
                    playCard(playedCard);
                }
            }
            else {
                playedCard = getCurrentPlayer().playCard(action);
                activeCard = playedCard.getActiveCard();
                playCard(playedCard);
                setHasPlayedCard();
            }
            for (GameView view : views) {
                view.handleAiPlayerTurn(getCurrentPlayer(), activeCard, currentColour, drewCard);
            }
        }
    }

    /**
     * Return if a given Card can legally be played on the current top card
     *
     * @param card The card to be played
     * @return If the given Card can be played
     */
    public boolean canPlayCard(Card card) {
        Card topCard = getTopCard();

        return (card == null
                || card.getColour().equals(Card.Colour.WILD)
                || card.getColour().equals(currentColour)
                || card.getSymbol().equals(topCard.getSymbol())
                || currentColour.equals(Card.Colour.WILD)
        );
    }

    /**
     * Play a given card and determine its effect
     *
     * @param card The card to play
     */
    public void playCard(DoubleSidedCard card) {
        storePriorState();
        playedCards.push(card);
        setHasPlayedCard();

        Card activeSide = card.getActiveCard();

        if(activeSide.getColour() == Card.Colour.WILD) {
            activeSide.cardAction(this);
            if (getCurrentPlayer().getIsAI()) {
                AiHelper aiHelper = new AiHelper(this, getCurrentPlayer().getActiveHand());
                Card.Colour colour = aiHelper.getMostCommonColour();
                setCurrentColour(colour);
            } else {
                for (GameView view : views) {
                    view.handleGetColour();
                }
            }

        } else {
            currentColour = activeSide.getColour();
            activeSide.cardAction(this);
        }

        for (GameView view : views) {
            view.handlePlayCard(activeSide);
        }
    }

    /**
     * Deal a card from the deck to a given Player
     *
     * @param player The Player whose turn it is
     * @param storeState Whether the current state of the game has been stored
     */
    public DoubleSidedCard drawCard(Player player, boolean storeState) {
        if (storeState) {
            storePriorState();
            for (Player itPlayer : players) {
                itPlayer.storePriorState();
            }
        }
        if(deck.isEmpty()){
            shuffleDeck();
        }
        DoubleSidedCard drawnCard = deck.pop();
        player.dealCard(drawnCard);
        setHasDrawnCard();
        for (GameView view : views) {
            view.handleDrawCard(drawnCard.getActiveCard());
        }
        return drawnCard;
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
     * Return whether a Player has won the game
     *
     * @return Whether a Player has won the game
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
     * Starts a new round of the game
     */
    public void startNewRound(){
        roundNumber++;
        if (dark){
            flip();
            dark = false;
        }
        Stack<DoubleSidedCard> deck = DeckBuilder.createDoubleSidedDeck();
        setDeck(deck);
        this.playedCards = new Stack<DoubleSidedCard>();
        turnOrderReversed = false;
        skipPlayer = false;
        skipEveryone = false;
        hasDrawnCard = false;
        hasPlayedCard = false;
        canRedo = false;
        currentPlayerIndex = -1;
        for (Player player: players){
            player.clearHand();
        }

        for (GameView view : views) {
            view.handleNewGame();
            view.handleUpdateTurnOrder(false);
        }
        shuffleDeck();
        dealCards(STARTING_HAND_SIZE);
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
     * Sets the status of a card having been drawn on the current turn to true
     */
    public void setHasDrawnCard() {
        hasDrawnCard = true;
    }

    /**
     * Sets the status of a card having been played on the current turn to true
     */
    public void setHasPlayedCard() {
        hasPlayedCard = true;
    }

    /**
     * Sets the status of whether the player can redo
     */
    public void setCanRedo() { canRedo = true; }

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
        return currentPlayerIndex != -1 && players.get(currentPlayerIndex).getHand().isEmpty();
    }

    /**
     * @return whether the current state of the current game is dark or not
     */
    public boolean isDark() {
        return dark;
    }

    /**
     * Flip the UNO deck of the current game
     */
    public void flip(){
        DoubleSidedCard.flip();
        dark = !dark;
        Collections.reverse(deck);
        Collections.reverse(playedCards);
        setCurrentColour(playedCards.peek().getActiveCard().getColour());
    }

    /**
     * Set the previous state of the current game
     *
     * @param previousState the previous state of the current game
     */
    public void setPreviousState(Game previousState) {
        this.previousState = previousState;
    }

    /**
     * Copy the current state of the current game
     * This copy can later be used to save and load the previous game state
     * @return the previous state of the current game
     */
    private Game copyGame(){
        Stack<DoubleSidedCard> priorDeck = new Stack<>();
        for (DoubleSidedCard card:deck){
            priorDeck.add(card);
        }
        Game priorState = new Game(priorDeck);

        for (GameView view: views){
            priorState.addView(view);
        }
        for (Player player: players){
            priorState.addPlayer(player);
        }
        for (DoubleSidedCard card: playedCards){
            priorState.playedCards.add(card);
        }

        priorState.turnOrderReversed = turnOrderReversed;
        priorState.skipPlayer = skipPlayer;
        priorState.skipEveryone = skipEveryone;
        priorState.currentPlayerIndex = currentPlayerIndex;
        priorState.currentColour = currentColour;
        priorState.running = running;
        priorState.roundNumber = roundNumber;
        priorState.hasDrawnCard = hasDrawnCard;
        priorState.hasPlayedCard = hasPlayedCard;
        priorState.canRedo = canRedo;
        priorState.dark = dark;
        return priorState;
    }

    /**
     * Store the previous state of the current game
     */
    public void storePriorState(){
        setPreviousState(copyGame());
    }

    /**
     * Restore the state of the game prior to the most recent action of the current player
     */
    public void restorePriorState() {
        turnOrderReversed = previousState.turnOrderReversed;
        skipPlayer = previousState.skipPlayer;
        skipEveryone = previousState.skipEveryone;
        currentPlayerIndex = previousState.currentPlayerIndex;
        currentColour = previousState.currentColour;
        running = previousState.running;
        roundNumber = previousState.roundNumber;
        hasDrawnCard = previousState.hasDrawnCard;
        hasPlayedCard = previousState.hasPlayedCard;
        canRedo = previousState.canRedo;
        dark = previousState.dark;
    }

    /**
     * Undo the most recent action of the current player
     */
    public void undo(){
        Game tempGame = copyGame();
        restorePriorState();
        setCanRedo();
        if (tempGame.isDark() != isDark()){
            DoubleSidedCard.flip();
        }

        for (Player player:players){
            player.undo();
        }
        playedCards.clear();
        for (DoubleSidedCard card: previousState.playedCards){
            playedCards.push(card);
        }
        deck.clear();
        for (DoubleSidedCard card: previousState.deck){
            deck.push(card);
        }
        for (GameView view: views) {
            view.handleUndoAction();
        }
        setPreviousState(tempGame);
    }

    /**
     * Redo the most recent action of the current player
     */
    public void redo(){
        undo();
        for (GameView view: views) {
            view.handleRedoAction();
        }
        canRedo = false;
    }

    /**
     * Restart the game from the start of the most recent round
     */
    public void restartGame() {
        if (dark){
            flip();
            dark = false;
        }
        Stack<DoubleSidedCard> deck = DeckBuilder.createDoubleSidedDeck();
        setDeck(deck);
        this.playedCards = new Stack<DoubleSidedCard>();
        turnOrderReversed = false;
        skipPlayer = false;
        skipEveryone = false;
        hasDrawnCard = false;
        hasPlayedCard = false;
        currentPlayerIndex = -1;
        for (Player player: players){
            player.clearHand();
        }

        for (GameView view : views) {
            view.handleUpdateTurnOrder(false);
        }
        shuffleDeck();
        dealCards(STARTING_HAND_SIZE);
        advanceTurn();
        for (GameView view: views){
            view.handleRestartGame();
        }
    }

}