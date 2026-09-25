package ru.nsu.etronin.task112;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private String output() {
        return outContent.toString();
    }

    private BlackJackGame gameWith(int seed, String input) {
        Deck deck = new Deck(1, new Random(seed));
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        return new BlackJackGame(deck, scanner);
    }

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

    @Test
    void testPlayRoundPlayerStands() {
        BlackJackGame game = gameWith(1, "2\n");
        game.playRound();
        int total = game.getPlayerWins() + game.getDealerWins();
        assertTrue(total <= 1);
    }

    @Test
    void testPlayRoundPlayerTakesOneCardThenStands() {
        BlackJackGame game = gameWith(1, "1\n2\n");
        game.playRound();
        assertTrue(output().contains("Вы открыли карту")
                || output().contains("Перебор")
                || output().contains("Блэкджек"));
    }

    @Test
    void testThreeRoundsScoreDoesNotDecrease() {
        BlackJackGame game = gameWith(1, "2\n2\n2\n");
        game.playRound();
        int first = game.getPlayerWins() + game.getDealerWins();
        game.playRound();
        int second = game.getPlayerWins() + game.getDealerWins();
        game.playRound();
        int third = game.getPlayerWins() + game.getDealerWins();
        assertTrue(second >= first);
        assertTrue(third >= second);
    }
}