package ru.nsu.etronin.task113;

abstract class BinaryOperation extends Expression {
    protected final Expression left;
    protected final Expression right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    protected String format(String op) {
        return "(" + left.toString() + op + right.toString() + ")";
    }
}