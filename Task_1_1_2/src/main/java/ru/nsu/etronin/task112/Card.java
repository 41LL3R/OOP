package ru.nsu.etronin.task112;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.getValue();
    }

    public String getFullCard() {
        return suit.getSymbol() + ' ' + rank.getName();
    }
}

