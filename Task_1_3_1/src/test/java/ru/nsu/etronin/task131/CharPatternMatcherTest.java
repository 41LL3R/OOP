package ru.nsu.etronin.task131;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharPatternMatcherTest {

    @Test
    void findsAllIncludingOverlapping() {
        CharPatternMatcher m = new CharPatternMatcher("aa");
        assertEquals(List.of(0, 1, 2),
                m.findAll("aaaa".toCharArray(), 4));
    }

    @Test
    void respectsLimitAndReturnsEmptyWhenTooShort() {
        CharPatternMatcher m = new CharPatternMatcher("abc");
        char[] buf = "abcXYZabc".toCharArray();
        assertEquals(List.of(0), m.findAll(buf, 3));
        assertTrue(m.findAll("ab".toCharArray(), 2).isEmpty());
    }
}