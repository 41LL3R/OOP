package ru.nsu.etronin.task112;

public class Dealer extends Player {

    private static final int DEALER_MIN_SCORE = 17;

    public Dealer(String name) {
        super(name);
    }

    public boolean shouldHit() {
        return getScore() < DEALER_MIN_SCORE;
    }
}