package ru.nsu.etronin.task113;

import java.util.Map;

class Div extends BinaryOperation {
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return format("/");
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        final int leftValue = left.evaluate(variables);
        final int rightValue = right.evaluate(variables);

        if (rightValue == 0) { // Java сама бросает ArithmeticException при делении на 0
            throw new ArithmeticException("Division by zero is prohibited"); // Я добавляю понятное сообщение
        }

        return leftValue / rightValue;
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