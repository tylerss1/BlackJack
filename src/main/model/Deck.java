package model;

import java.util.ArrayList;
import java.util.Collections;

//creates list of cards and operates on lists of cards

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>();
    }

    //MODIFIES: this
    //EFFECTS: creates a full deck with 52 distinct cards
    public void constructDeck() {
        for (Rank faceVal : Rank.values()) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(faceVal, suit));
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: shuffles the deck randomly
    public void shuffle() {
        Collections.shuffle(deck);
    }

    //MODIFIES: this
    //EFFECTS: clears the deck
    public void resetDeck() {
        deck.clear();
    }

    public Card getTopCard() {
        return deck.get(0);
    }

    public void removeTopCard() {
        deck.remove(0);
    }

    //For testing
    public int getSize() {
        return deck.size();
    }

    //For testing
    public boolean contains(Card card) {
        return deck.contains(card);
    }

    //For testing
    public void add(Card card) {
        deck.add(card);
    }

    //For testing
    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
