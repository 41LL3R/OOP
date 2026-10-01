package ru.nsu.etronin.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class SubTest {

    @Test
    void testToString() {
        Expression e = new Sub(new Number(10), new Number(3));
        assertEquals("(10-3)", e.toString());
    }

    @Test
    void testEvaluate() {
        Expression e = new Sub(new Number(10), new Number(3));
        assertEquals(7, e.evaluate(Map.of()));
    }

    @Test
    void testEvaluateWithVariables() {
        Expression e = new Sub(new Variable("x"), new Number(2));
        assertEquals(8, e.evaluate(Map.of("x", 10)));
    }

    @Test
    void testDerivative() {
        Expression e = new Sub(new Variable("x"), new Number(2));
        // (x - 2)' = (1 - 0)
        assertEquals("(1-0)", e.derivative("x").toString());
    }
}