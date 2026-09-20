package ru.nsu.etronin.task112;

public class Player {
    protected final Hand hand = new Hand();
    protected final String name;

    public Player(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    public void takeCard(Card card) {
        hand.addCard(card);
    }

    public void resetHand() {
        hand.clear();
    }
}