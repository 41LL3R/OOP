package ru.nsu.etronin.task112;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
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

    @Test
    void testMainConstructor() {
        Main main = new Main();
        assertNotNull(main);
    }
    @Test
    void testPrintStateHiddenDealerCard() {
        Main main = new Main();
        main.printState(true);
        assertTrue(output().contains("Ваши карты"));
        assertTrue(output().contains("Карты дилера"));
    }

    @Test
    void testPrintStateOpenDealerCard() {
        Main main = new Main();
        main.printState(false);
        assertTrue(output().contains("Ваши карты"));
        assertTrue(output().contains("Карты дилера"));
    }

    @Test
    void testPrintScore() {
        Main main = new Main();
        main.printScore();
        assertTrue(output().contains("Счет 0:0"));
    }

    @Test
    void testResetHands() {
        Main main = new Main();
        main.resetHands();
        assertNotNull(main);
    }

    @Test
    void testDealInitialCards() {
        Main main = new Main();
        main.dealInitialCards();
        assertTrue(output().contains("Дилер раздал карты"));
    }

    @Test
    void testHandleBlackjackEmptyHands() {
        Main main = new Main();
        boolean result = main.handleBlackjack();
        assertFalse(result);
    }

}