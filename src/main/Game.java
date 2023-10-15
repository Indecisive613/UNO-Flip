package main;

import java.util.ArrayList;

public class Game {

    private Deck deck;
    private final ArrayList<Player> players;

    public Game() {
        players = new ArrayList<>();
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
