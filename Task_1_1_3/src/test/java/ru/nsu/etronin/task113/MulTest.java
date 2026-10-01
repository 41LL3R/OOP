package ru.nsu.etronin.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class MulTest {

    @Test
    void testToString() {
        Expression e = new Mul(new Number(3), new Number(4));
        assertEquals("(3*4)", e.toString());
    }

    @Test
    void testEvaluate() {
        Expression e = new Mul(new Number(3), new Number(4));
        assertEquals(12, e.evaluate(Map.of()));
    }

    @Test
    void testEvaluateWithVariables() {
        Expression e = new Mul(new Number(2), new Variable("x"));
        assertEquals(10, e.evaluate(Map.of("x", 5)));
    }

    @Test
    void testDerivative() {
        Expression e = new Mul(new Number(2), new Variable("x"));
        assertEquals("((0*x)+(2*1))", e.derivative("x").toString());
    }
}