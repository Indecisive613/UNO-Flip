package main;

import main.cards.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * A class to initialize and run an UNO game.
 *
 * @author Fiona Cheng, Anand Balaram, Jackie Smolkin-Lerner
 */
public class GameRunner {

    private final Game game;
    private final ArrayList<GameView> views;

    /**
     * Create a GameRunner with a specific Game
     *
     * @param game The Game to run
     */
    public GameRunner(Game game) {
        this.game = game;
        this.views = new ArrayList<>();
    }

    /**
     * Add a view to the GameRunner
     *
      * @param view the view to be added
     */
    public void addView(GameView view) {
        views.add(view);
    }

    /**
     * Initialize a new game
     */
    public void initGame(){
        for (GameView view : views) {
            view.handleNewGame();
        }
        game.shuffleDeck();
        game.dealCards();
        game.advanceTurn();
    }

    /**
     * Create and return a deck of UNO cards //TODO delete this one once the double sided deck works in the game
     *
     * @return A deck of UNO cards
     */
    public static Stack<Card> createDeck() {
        Stack<Card> cards = new Stack<Card>();
        ArrayList<Card.Symbol> plainNumbers = new ArrayList<Card.Symbol>(Arrays.asList(Card.Symbol.ONE, Card.Symbol.TWO, Card.Symbol.THREE, Card.Symbol.FOUR,
                Card.Symbol.FIVE, Card.Symbol.SIX, Card.Symbol.SEVEN, Card.Symbol.EIGHT, Card.Symbol.NINE));

        try{
            for(Card.Colour colour : Card.nonWildColours){
                //add 2 of each number
                for(Card.Symbol symbol : plainNumbers) {
                    Card card1 = new NormalCard(colour, symbol);
                    Card card2 = new NormalCard(colour, symbol);
                    cards.push(card1);
                    cards.push(card2);
                }
                //add 2 of each special card
                cards.push(new DrawOneCard(colour));
                cards.push(new DrawOneCard(colour));
                cards.push(new ReverseCard(colour, Card.Side.LIGHT));
                cards.push(new ReverseCard(colour, Card.Side.LIGHT));
                cards.push(new SkipCard(colour));
                cards.push(new SkipCard(colour));
            }

           //Add four of each wild card
           for(int i = 0; i < 4; i++){
               cards.push(new WildCard(Card.Side.LIGHT));
               cards.push(new WildDrawTwoCard());
           }
        } catch(IllegalArgumentException e){
                System.out.println(e);
        }
        return cards;
    }

    /**
     * Create and return a deck of light side UNO cards
     *
     * @return A deck of light side UNO cards
     */
    public static Stack<Card> createLightDeck() {
        Stack<Card> cards = new Stack<Card>();
        ArrayList<Card.Symbol> plainNumbers = new ArrayList<Card.Symbol>(Arrays.asList(Card.Symbol.ONE, Card.Symbol.TWO, Card.Symbol.THREE, Card.Symbol.FOUR,
                Card.Symbol.FIVE, Card.Symbol.SIX, Card.Symbol.SEVEN, Card.Symbol.EIGHT, Card.Symbol.NINE));

        try{
            for(Card.Colour colour : Card.lightColours){
                //add 2 of each number
                for(Card.Symbol symbol : plainNumbers) {
                    Card card1 = new NormalCard(colour, symbol);
                    Card card2 = new NormalCard(colour, symbol);
                    cards.push(card1);
                    cards.push(card2);
                }
                //add 2 of each special card
                cards.push(new DrawOneCard(colour));
                cards.push(new DrawOneCard(colour));
                cards.push(new ReverseCard(colour, Card.Side.LIGHT));
                cards.push(new ReverseCard(colour, Card.Side.LIGHT));
                cards.push(new SkipCard(colour));
                cards.push(new SkipCard(colour));
                cards.push(new Flip(colour, Card.Side.LIGHT));
                cards.push(new Flip(colour, Card.Side.LIGHT));
            }

            //Add four of each wild card
            for(int i = 0; i < 4; i++){
                cards.push(new WildCard(Card.Side.LIGHT));
                cards.push(new WildDrawTwoCard());
            }
        } catch(IllegalArgumentException e){
            System.out.println(e);
        }
        return cards;
    }

    /**
     * Create and return a deck of dark side UNO cards
     *
     * @return A deck of dark side UNO cards
     */
    public static Stack<Card> createDarkDeck() {
        Stack<Card> cards = new Stack<Card>();
        ArrayList<Card.Symbol> plainNumbers = new ArrayList<Card.Symbol>(Arrays.asList(Card.Symbol.ONE, Card.Symbol.TWO, Card.Symbol.THREE, Card.Symbol.FOUR,
                Card.Symbol.FIVE, Card.Symbol.SIX, Card.Symbol.SEVEN, Card.Symbol.EIGHT, Card.Symbol.NINE));

        try{
            for(Card.Colour colour : Card.darkColours){
                //add 2 of each number
                for(Card.Symbol symbol : plainNumbers) {
                    Card card1 = new NormalCard(colour, symbol);
                    Card card2 = new NormalCard(colour, symbol);
                    cards.push(card1);
                    cards.push(card2);
                }
                //add 2 of each special card
                cards.push(new DrawFive(colour));
                cards.push(new DrawFive(colour));
                cards.push(new SkipEveryone(colour));
                cards.push(new SkipEveryone(colour));
                cards.push(new ReverseCard(colour, Card.Side.DARK));
                cards.push(new ReverseCard(colour, Card.Side.DARK));
                cards.push(new Flip(colour, Card.Side.DARK));
                cards.push(new Flip(colour, Card.Side.DARK));
            }

            //Add four of each wild card
            for(int i = 0; i < 4; i++){
                cards.push(new WildCard(Card.Side.DARK));
                cards.push(new WildDrawColour());
            }
        } catch(IllegalArgumentException e){
            System.out.println(e);
        }
        return cards;
    }

    /**
     * Create and returns a double-sided UNO Flip deck
     *
     * @return A deck of UNO Flip cards
     */
    public static Stack<DoubleSidedCard> createDoubleSidedDeck() {
        Stack<DoubleSidedCard> cards = new Stack<DoubleSidedCard>();

        Stack<Card> lightDeck = createLightDeck();
        Stack<Card> darkDeck = createDarkDeck();
        Collections.shuffle(lightDeck);
        Collections.shuffle(darkDeck);

        System.out.println(lightDeck.size() + " is light. Dark: " + darkDeck.size());
        if(lightDeck.size() != darkDeck.size()){
            throw new RuntimeException("The size of the light and dark decks do not match.");
        }

        for(Card lightCard: lightDeck){
            cards.push(new DoubleSidedCard(lightCard, darkDeck.pop()));
        }
        return cards;
    }

    public static void main(String[] args) {
        Stack<Card> deck = createLightDeck();
        for(Card card: deck){
            System.out.println(card);
        }

        deck = createDarkDeck();
        for(Card card: deck){
            System.out.println(card);
        }

        Stack<DoubleSidedCard> finalDeck = createDoubleSidedDeck();
        for(DoubleSidedCard card: finalDeck){
            System.out.println(card);
        }
    }
}