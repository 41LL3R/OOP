package ru.nsu.etronin.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void testCardValue() {
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(11, card.getValue());
    }
}