package ru.nsu.etronin.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void testToString() {
        assertEquals("5", new Number(5).toString());
    }

    @Test
    void testEvaluate() {
        assertEquals(5, new Number(5).evaluate(Map.of()));
    }

    @Test
    void testEvaluateIgnoresVariables() {
        assertEquals(5, new Number(5).evaluate(Map.of("x", 100)));
    }

    @Test
    void testDerivative() {
        assertEquals("0", new Number(5).derivative("x").toString());
    }

    @Test
    void testDerivativeByAnyVariable() {
        assertEquals("0", new Number(5).derivative("y").toString());
    }

    @Test
    void testOriginalNotModified() {
        Number n = new Number(5);
        n.derivative("x");
        assertEquals("5", n.toString());
    }
}