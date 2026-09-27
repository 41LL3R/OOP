package ru.nsu.etronin.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    void testDeckWithSixDecksHas312Cards() {
        Deck deck = new Deck(6);
        int count = 0;
        for (int i = 0; i < 312; i++) {
            deck.drawCard();
            count++;
        }
        assertEquals(312, count);
    }

    @Test
    void testDrawCardReturnsNonNull() {
        Deck deck = new Deck(1);
        assertNotNull(deck.drawCard());
    }

    @Test
    void testDeckRefillsWhenEmpty() {
        Deck deck = new Deck(1);
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        Card card = deck.drawCard();
        assertNotNull(card);
    }
}