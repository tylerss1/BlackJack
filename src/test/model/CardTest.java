package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    Card card1;
    Card card2;
    Card card3;

    @BeforeEach
    public void runBefore(){
        card1 = new Card(Rank.Five, Suit.Diamonds);
        card2 = new Card(Rank.King, Suit.Spades);
        card3 = new Card(Rank.Ace, Suit.Hearts);
    }

    @Test
    public void getWholeCardTest(){
        assertEquals("Five of Diamonds", card1.getWholeCard());
        assertEquals("King of Spades", card2.getWholeCard());
        assertEquals("Ace of Hearts", card3.getWholeCard());
    }

    @Test
    public void getSuitTest(){
        assertEquals("Diamonds", card1.getSuit().toString());
    }
}
