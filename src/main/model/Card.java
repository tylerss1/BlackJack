package model;

//holds values and fields for a single card object

public class Card {

    private Rank faceValue;
    private Suit suit;

    public Card(Rank rank, Suit su) {
        faceValue = rank;
        suit = su;
    }

    public Rank getFaceValue() {
        return faceValue;
    }

    public Suit getSuit() {
        return suit;
    }

    //EFFECTS: returns String form of this card
    public String getWholeCard() {
        String faceValueText = faceValue.toString();
        String suitText = suit.toString();

        return faceValueText + " " + "of" + " " + suitText;
    }

}
