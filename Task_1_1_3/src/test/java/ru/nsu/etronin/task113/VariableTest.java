package ru.nsu.etronin.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import org.junit.jupiter.api.Test;

class VariableTest {

    @Test
    void testToString() {
        assertEquals("x", new Variable("x").toString());
    }

    @Test
    void testEvaluate() {
        assertEquals(10, new Variable("x").evaluate(Map.of("x", 10)));
    }

    @Test
    void testEvaluateMissingVariableThrows() {
        assertThrows(Exception.class, () -> new Variable("x").evaluate(Map.of("y", 10)));
    }

    @Test
    void testDerivativeBySameVariable() {
        assertEquals("1", new Variable("x").derivative("x").toString());
    }

    @Test
    void testDerivativeByDifferentVariable() {
        assertEquals("0", new Variable("x").derivative("y").toString());
    }
}