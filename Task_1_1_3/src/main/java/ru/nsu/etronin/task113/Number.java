package ru.nsu.etronin.task113;

import java.util.Map;

class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return value;
    }

    @Override
    public Expression derivative(String varName) {
        return new Number(0);
    }
}
