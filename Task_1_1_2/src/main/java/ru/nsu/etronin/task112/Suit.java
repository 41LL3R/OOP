package ru.nsu.etronin.task112;

public enum Suit {
    SPADES("♠"),
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣");

    private final String symbol;

    Suit(String Symbol) {
        this.symbol = Symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
