package main;

import main.cards.*;

import java.util.ArrayList;
import java.util.Arrays;
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
     * Create and return a deck of UNO cards
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
                cards.push(new ReverseCard(colour));
                cards.push(new ReverseCard(colour));
                cards.push(new SkipCard(colour));
                cards.push(new SkipCard(colour));
            }

           //Add four of each wild card
           for(int i = 0; i < 4; i++){
               cards.push(new WildCard());
               cards.push(new WildDrawTwoCard());
           }
        } catch(IllegalArgumentException e){
                System.out.println(e);
        }
        return cards;
    }
}