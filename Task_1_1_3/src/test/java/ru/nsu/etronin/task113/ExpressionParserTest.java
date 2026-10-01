package ru.nsu.etronin.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class ExpressionParserTest {

    @Test
    void testParseNumber() {
        assertEquals("5", ExpressionParser.parse("5").toString());
    }

    @Test
    void testParseVariable() {
        assertEquals("x", ExpressionParser.parse("x").toString());
    }

    @Test
    void testParseSimpleAdd() {
        assertEquals("(3+5)", ExpressionParser.parse("(3+5)").toString());
    }

    @Test
    void testParseNested() {
        assertEquals("(3+(2*x))", ExpressionParser.parse("(3+(2*x))").toString());
    }

    @Test
    void testParseWithSpaces() {
        assertEquals("(3+5)", ExpressionParser.parse("( 3 + 5 )").toString());
    }

    @Test
    void testParseAndEvaluate() {
        Expression e = ExpressionParser.parse("(3+(2*x))");
        assertEquals(23, e.evaluate(Map.of("x", 10)));
    }
}