package ru.nsu.etronin.task112;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {

    @Test
    void testConstructorStartsWithZeroScore() {
        BlackJackGame game = new BlackJackGame();
        assertEquals(0, game.getPlayerWins());
        assertEquals(0, game.getDealerWins());
    }

    @Test
    void testDealInitialCardsGivesTwoCardsEach() {
        BlackJackGame game = new BlackJackGame();
        game.dealInitialCards();
        assertEquals(2, game.getHumanCardCount());
        assertEquals(2, game.getDealerCardCount());
    }

    @Test
    void testResetHandsClearsBothHands() {
        BlackJackGame game = new BlackJackGame();
        game.dealInitialCards();
        game.resetHands();
        assertEquals(0, game.getHumanCardCount());
        assertEquals(0, game.getDealerCardCount());
    }

    @Test
    void testHandleBlackjackEmptyHandsReturnsFalse() {
        BlackJackGame game = new BlackJackGame();
        assertFalse(game.handleBlackjack());
    }
}