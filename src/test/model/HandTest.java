package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {

    Hand hand1;
    Hand hand2;
    Hand garbage;
    Deck deck;


    @BeforeEach
    public void runBefore() {
        hand1 = new Hand();
        hand2 = new Hand();
        garbage = new Hand();
        deck = new Deck();
        deck.constructDeck();
    }

    @Test
    public void drawAndReadTest() {
        hand1.draw(deck);
        assertEquals("\n" + "Ace of Diamonds", hand1.readHand());
        hand1.draw(deck);
        assertEquals("\n" + "Ace of Diamonds" + "\n" + "Ace of Clubs", hand1.readHand());
        assertEquals("Ace of Clubs", hand1.announceNewDraw());
        assertEquals("Ace of Diamonds", hand1.readFirstCard());
    }

    @Test
    public void dumpTest(){
        hand2.draw(deck);
        hand2.draw(deck);
        hand2.dump();
        assertTrue(hand2.isEmpty());
    }

    @Test
    public void valueTest(){
        hand1.draw(deck);
        hand1.draw(deck);
        hand1.addAll();
        assertEquals(12, hand1.getCollectiveValue());

        hand2.draw(deck);
        hand2.draw(deck);
        hand2.draw(deck);
        hand2.draw(deck);
        hand2.draw(deck);
        hand2.addAll();
        assertEquals(18, hand2.getCollectiveValue());

        hand1.resetValue();
        assertEquals(0, hand1.getCollectiveValue());
        assertEquals(0, hand1.getNumberOfAces());
    }

    @Test
    public void moreValueTest(){
        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);         //end of aces
        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);            // end of two
        garbage.draw(deck);
        hand1.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);          // end of three
        garbage.draw(deck);
        hand1.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);       // end of four
        garbage.draw(deck);
        garbage.draw(deck);
        hand1.draw(deck);
        garbage.draw(deck);        //end of five
        garbage.draw(deck);
        garbage.draw(deck);
        hand1.draw(deck);
        garbage.draw(deck);        //end of six

        hand1.addAll();
        assertEquals(18, hand1.getCollectiveValue());
        hand1.dump();
        hand1.resetValue();

        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);
        hand1.draw(deck);         //end of seven
        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);
        hand1.draw(deck);        //end of eight

        hand1.addAll();
        assertEquals(15, hand1.getCollectiveValue());
        hand1.dump();
        hand1.resetValue();

        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);
        hand1.draw(deck);        //end of nine
        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);
        hand1.draw(deck);       //end of ten

        hand1.addAll();
        assertEquals(19, hand1.getCollectiveValue());
        hand1.dump();
        hand1.resetValue();

        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);
        hand1.draw(deck);        //end of jack
        garbage.draw(deck);
        garbage.draw(deck);
        garbage.draw(deck);
        hand1.draw(deck);        //end of queen

        hand1.addAll();
        assertEquals(20, hand1.getCollectiveValue());
        hand1.dump();
        hand1.resetValue();

        hand1.draw(deck);

        hand1.addAll();
        assertEquals(10, hand1.getCollectiveValue());
    }
}
