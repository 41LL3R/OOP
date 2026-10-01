package ru.nsu.etronin.task113;

import java.util.Map;

class Mul extends BinaryOperation {
    public Mul(Expression left, Expression right) { super(left, right); }

    @Override
    public String toString() { return format("*"); }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return left.evaluate(variables) * right.evaluate(variables);
    }

    @Override
    public Expression derivative(String varName) {
        return new Add(
                new Mul(left.derivative(varName), right),
                new Mul(left, right.derivative(varName))
        );
    }
}