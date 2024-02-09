package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Deck deck1;
    Deck deck2;

    @BeforeEach
    public void runBefore() {
        deck1 = new Deck();
        deck1.constructDeck();
    }


    @Test
    public void shuffleTest() {
        assertEquals(52, deck1.getSize());
        deck1.shuffle();
        assertEquals(52, deck1.getSize());
    }

    @Test
    public void constructAndShuffleTest(){
        deck2 = new Deck();

        for (Rank faceVal : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(faceVal, suit);
                deck2.add(card);
                deck2.shuffle();
                assertTrue(deck2.contains(card));
            }
        }
    }

    @Test
    public void removeTest(){
        deck1.removeTopCard();
        assertEquals(51, deck1.getSize());
    }

    @Test
    public void resetTest(){
        deck1.resetDeck();
        assertTrue(deck1.isEmpty());
    }
}