package ru.nsu.etronin.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class AddTest {

    @Test
    void testToString() {
        Expression e = new Add(new Number(3), new Number(5));
        assertEquals("(3+5)", e.toString());
    }

    @Test
    void testEvaluate() {
        Expression e = new Add(new Number(3), new Number(5));
        assertEquals(8, e.evaluate(Map.of()));
    }

    @Test
    void testEvaluateWithVariables() {
        Expression e = new Add(new Variable("x"), new Number(10));
        assertEquals(15, e.evaluate(Map.of("x", 5)));
    }

    @Test
    void testDerivative() {
        Expression e = new Add(new Variable("x"), new Number(10));
        assertEquals("(1+0)", e.derivative("x").toString());
    }
}