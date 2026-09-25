package ru.nsu.etronin.task112;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer("Дилер");
    }

    @Test
    void testConstructorCreatesDealer() {
        assertNotNull(dealer);
    }

    @Test
    void testShouldHitOnEmptyHand() {
        assertTrue(dealer.shouldHit());
    }

    @Test
    void testShouldHitOnLowScore() {
        dealer.takeCard(new Card(Suit.SPADES, Rank.TWO));
        dealer.takeCard(new Card(Suit.HEARTS, Rank.THREE));
        assertTrue(dealer.shouldHit());
    }

    @Test
    void testShouldHitOnSixteen() {
        dealer.takeCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.takeCard(new Card(Suit.HEARTS, Rank.SIX));
        assertEquals(16, dealer.getScore());
        assertTrue(dealer.shouldHit());
    }

    @Test
    void testShouldNotHitOnSeventeen() {
        dealer.takeCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.takeCard(new Card(Suit.HEARTS, Rank.SEVEN));
        assertEquals(17, dealer.getScore());
        assertFalse(dealer.shouldHit());
    }

    @Test
    void testShouldNotHitOnTwenty() {
        dealer.takeCard(new Card(Suit.SPADES, Rank.TEN));
        dealer.takeCard(new Card(Suit.HEARTS, Rank.KING));
        assertEquals(20, dealer.getScore());
        assertFalse(dealer.shouldHit());
    }

    @Test
    void testShouldNotHitOnBlackjack() {
        dealer.takeCard(new Card(Suit.SPADES, Rank.ACE));
        dealer.takeCard(new Card(Suit.HEARTS, Rank.KING));
        assertEquals(21, dealer.getScore());
        assertFalse(dealer.shouldHit());
    }

    @Test
    void testTakeCardAndGetScore() {
        dealer.takeCard(new Card(Suit.CLUBS, Rank.FIVE));
        assertEquals(5, dealer.getScore());
    }

    @Test
    void testGetCardCount() {
        dealer.takeCard(new Card(Suit.CLUBS, Rank.FIVE));
        dealer.takeCard(new Card(Suit.DIAMONDS, Rank.SIX));
        assertEquals(2, dealer.getCardCount());
    }

    @Test
    void testResetHand() {
        dealer.takeCard(new Card(Suit.CLUBS, Rank.FIVE));
        dealer.resetHand();
        assertEquals(0, dealer.getScore());
        assertEquals(0, dealer.getCardCount());
    }

    @Test
    void testGetHandString() {
        dealer.takeCard(new Card(Suit.HEARTS, Rank.TWO));
        dealer.takeCard(new Card(Suit.SPADES, Rank.SIX));
        assertNotNull(dealer.getHandString(false));
        assertNotNull(dealer.getHandString(true));
    }

    @Test
    void testGetClosedCardString() {
        dealer.takeCard(new Card(Suit.CLUBS, Rank.NINE));
        dealer.takeCard(new Card(Suit.DIAMONDS, Rank.ACE));
        assertNotNull(dealer.getClosedCardString());
    }
}