package ru.nsu.etronin.task113;

import java.util.Map;

class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        if (!variables.containsKey(name)) {
            throw new IllegalArgumentException("Variable " + name + " not found");
        }
        return variables.get(name);
    }

    @Override
    public Expression derivative(String varName) {
        if (this.name.equals(varName)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }
}