package ru.nsu.etronin.task112;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Тест");
    }

    @Test
    void testInitialScoreIsZero() {
        assertEquals(0, player.getScore());
    }

    @Test
    void testTakeCardIncreasesScore() {
        player.takeCard(new Card(Suit.SPADES, Rank.TEN));
        assertEquals(10, player.getScore());
    }

    @Test
    void testResetHandClearsScore() {
        player.takeCard(new Card(Suit.SPADES, Rank.TEN));
        player.resetHand();
        assertEquals(0, player.getScore());
    }

    @Test
    void testGetHandString() {
        player.takeCard(new Card(Suit.HEARTS, Rank.TWO));
        assertEquals("[♥ 2 (2)]", player.getHandString(false));
        assertEquals("[<???>]", player.getHandString(true));
    }

    @Test
    void testGetClosedCardString() {
        player.takeCard(new Card(Suit.CLUBS, Rank.NINE));
        player.takeCard(new Card(Suit.DIAMONDS, Rank.ACE));
        assertEquals("♣ 9", player.getClosedCardString());
    }
}