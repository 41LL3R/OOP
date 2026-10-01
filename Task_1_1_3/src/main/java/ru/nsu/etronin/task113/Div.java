package ru.nsu.etronin.task113;

import java.util.Map;

class Div extends BinaryOperation {
    public Div(Expression left, Expression right) { super(left, right); }

    @Override
    public String toString() { return format("/"); }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return left.evaluate(variables) / right.evaluate(variables);
    }

    @Override
    public Expression derivative(String varName) {
        Expression numerator = new Sub(
                new Mul(left.derivative(varName), right),
                new Mul(left, right.derivative(varName))
        );
        Expression denominator = new Mul(right, right);
        return new Div(numerator, denominator);
    }
}