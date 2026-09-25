package ru.nsu.etronin.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {
    @Test
    void testCardValue() {
        Card card = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(11, card.getValue());
    }

    @Test
    void testGetValueForNotNumberCard() {
        assertEquals(10, new Card(Suit.HEARTS, Rank.KING).getValue());
        assertEquals(10, new Card(Suit.HEARTS, Rank.QUEEN).getValue());
        assertEquals(10, new Card(Suit.HEARTS, Rank.JACK).getValue());
    }

    @Test
    void testGetValueForNumberCard() {
        assertEquals(7, new Card(Suit.DIAMONDS, Rank.SEVEN).getValue());
    }

    @Test
    void testGetRank() {
        Card card = new Card(Suit.CLUBS, Rank.FIVE);
        assertEquals(Rank.FIVE, card.getRank());
    }

    @Test
    void testToString() {
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        assertEquals("♥ Туз", card.toString());
    }
}