package ru.nsu.etronin.task112;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final Random random = new Random();
    private final int numberOfDecks;

    public Deck(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        refill();
        shuffle();
    }

    // Наполняет колоду заново (numberOfDecks × 52 карты)
    private void refill() {
        for (int d = 0; d < numberOfDecks; d++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards, random);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            System.out.println("Колода пуста, перетасовка...");
            refill();
            shuffle();
        }
        return cards.remove(cards.size() - 1);
    }
}