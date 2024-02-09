package model;

import java.util.ArrayList;

//creates lists of cards and operates on the hands of the dealer and the user

public class Hand {

    private ArrayList<Card> playerDeck;
    private int collectiveValue = 0;
    private int numberOfAces = 0;

    public Hand() {
        playerDeck = new ArrayList<Card>();
    }

    //MODIFIES: this
    //EFFECTS: removes top card from the playing deck and adds it to the player's hand
    public void draw(Deck deck) {
        playerDeck.add(deck.getTopCard());
        deck.removeTopCard();
    }

    //EFFECTS: announces drawn card into console
    public String announceNewDraw() {
        return playerDeck.get(playerDeck.size() - 1).getWholeCard();
    }


    public int getCollectiveValue() {
        return collectiveValue;
    }

    public int getNumberOfAces() {
        return numberOfAces;
    }

    //MODIFIES: this
    //EFFECTS: resets fields to zero
    public void resetValue() {
        collectiveValue = 0;
        numberOfAces = 0;
    }

    //MODIFIES: this
    //EFFECTS: clears the hand of the player
    public void dump() {
        playerDeck.clear();
    }

    //EFFECTS: announces the hand of the holder into the console
    public String readHand() {
        String output = "";
        for (Card card : playerDeck) {
            output += "\n" + card.getWholeCard();
        }
        return output;
    }

    //EFFECTS: reads out one of the cards of the dealer
    public String readFirstCard() {
        return playerDeck.get(0).getWholeCard();
    }

    //MODIFIES: this
    //EFFECTS: assigns and adds values to the cards in the hand
    public void addSomeValues() {
        for (Card hand : playerDeck) {
            if (hand.getFaceValue() == Rank.Two) {
                collectiveValue += 2;
            } else if (hand.getFaceValue() == Rank.Three) {
                collectiveValue += 3;
            } else if (hand.getFaceValue() == Rank.Four) {
                collectiveValue += 4;
            } else if (hand.getFaceValue() == Rank.Five) {
                collectiveValue += 5;
            } else if (hand.getFaceValue() == Rank.Six) {
                collectiveValue += 6;
            } else if (hand.getFaceValue() == Rank.Seven) {
                collectiveValue += 7;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: assigns and adds values to the cards in the hand
    public void addSomeMoreValues() {
        for (Card hand : playerDeck) {
            if (hand.getFaceValue() == Rank.Eight) {
                collectiveValue += 8;
            } else if (hand.getFaceValue() == Rank.Nine) {
                collectiveValue += 9;
            } else if (hand.getFaceValue() == Rank.Ten) {
                collectiveValue += 10;
            } else if (hand.getFaceValue() == Rank.Jack) {
                collectiveValue += 10;
            } else if (hand.getFaceValue() == Rank.Queen) {
                collectiveValue += 10;
            } else if (hand.getFaceValue() == Rank.King) {
                collectiveValue += 10;
            } else if (hand.getFaceValue() == Rank.Ace) {
                numberOfAces++;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: assigns and adds values to the aces in the hand
    public void accountForAces() {
        for (int x = 0; x < numberOfAces; x++) {
            if (collectiveValue > 10) {
                collectiveValue++;
            } else {
                collectiveValue += 11;
            }
        }
    }

    ////MODIFIES: this
    //EFFECTS: assigns and adds values of ALL cards in hand
    public void addAll() {
        addSomeValues();
        addSomeMoreValues();
        accountForAces();
    }

    //For testing
    public boolean isEmpty() {
        return playerDeck.isEmpty();
    }
}
