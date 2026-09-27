package ru.nsu.etronin.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    void testEmptyHandScoreIsZero() {
        assertEquals(0, hand.getScore());
    }

    @Test
    void testSingleCardScore() {
        hand.addCard(new Card(Suit.SPADES, Rank.SEVEN));
        assertEquals(7, hand.getScore());
    }

    @Test
    void testSumOfCards() {
        hand.addCard(new Card(Suit.SPADES, Rank.TEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.SIX));
        assertEquals(16, hand.getScore());
    }

    @Test
    void testAceCountsAsEleven() {
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.SIX));
        assertEquals(17, hand.getScore());
    }

    @Test
    void testAceDropsToTenOnOverflow() {
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand.addCard(new Card(Suit.CLUBS, Rank.QUEEN));
        assertEquals(21, hand.getScore());
    }

    @Test
    void testTwoAcesBothDropOnOverflow() {
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.CLUBS, Rank.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        assertEquals(22, hand.getScore());
    }

    @Test
    void testClearEmptiesHand() {
        hand.addCard(new Card(Suit.SPADES, Rank.TEN));
        hand.clear();
        assertEquals(0, hand.getScore());
    }

    @Test
    void testGetCardsString() {
        hand.addCard(new Card(Suit.HEARTS, Rank.TWO));
        hand.addCard(new Card(Suit.SPADES, Rank.SIX));
        assertEquals("[♥ 2 (2), ♠ 6 (6)]", hand.getCardsString(false));
    }

    @Test
    void testGetCardsStringHidesFirstCard() {
        hand.addCard(new Card(Suit.HEARTS, Rank.TWO));
        hand.addCard(new Card(Suit.SPADES, Rank.SIX));
        assertEquals("[<???>, ♠ 6 (6)]", hand.getCardsString(true));
    }

    @Test
    void testGetClosedCardString() {
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
        assertEquals("♣ 9", hand.getClosedCardString());
    }

    @Test
    void testGetHoleCardStringOnEmptyHand() {
        assertEquals("<нет карты>", hand.getClosedCardString());
    }
}