package ru.nsu.etronin.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class DivTest {

    @Test
    void testToString() {
        Expression e = new Div(new Number(10), new Number(2));
        assertEquals("(10/2)", e.toString());
    }

    @Test
    void testEvaluate() {
        Expression e = new Div(new Number(10), new Number(2));
        assertEquals(5, e.evaluate(Map.of()));
    }

    @Test
    void testEvaluateWithVariables() {
        Expression e = new Div(new Variable("x"), new Number(2));
        assertEquals(5, e.evaluate(Map.of("x", 10)));
    }

    @Test
    void testDerivative() {
        Expression e = new Div(new Variable("x"), new Number(2));
        assertEquals("(((1*2)-(x*0))/(2*2))", e.derivative("x").toString());
    }
}