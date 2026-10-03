package ru.nsu.etronin.task113;

import java.util.Map;

class Sub extends BinaryOperation {
    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return format("-");
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return left.evaluate(variables) - right.evaluate(variables);
    }

    @Override
    public Expression derivative(String varName) {
        return new Sub(left.derivative(varName), right.derivative(varName));
    }
}