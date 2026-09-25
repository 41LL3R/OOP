package ru.nsu.etronin.task112;

public class Player {
    protected final Hand hand = new Hand();
    protected final String name;

    public Player(String name) {
        this.name = name;
    }

    public void takeCard(Card card) {
        hand.addCard(card);
    }

    public void resetHand() {
        hand.clear();
    }

    public int getScore() {
        return hand.getScore();
    }

    public String getHandString(boolean hide) {
        return hand.getCardsString(hide);
    }

    public String getClosedCardString() {
        return hand.getClosedCardString();
    }

    public int getCardCount() {
        return hand.size();
    }
}