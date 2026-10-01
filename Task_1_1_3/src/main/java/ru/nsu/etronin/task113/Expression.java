package ru.nsu.etronin.task113;

import java.util.HashMap;
import java.util.Map;

abstract class Expression {
    public abstract String toString();

    public abstract int evaluate(Map<String, Integer> variables);

    public abstract Expression derivative(String varName);

    public void print() {
        System.out.println(this.toString());
    }

    public int eval(String assignmentStr) {
        Map<String, Integer> vars = parseAssignments(assignmentStr);
        return this.evaluate(vars);
    }

    private Map<String, Integer> parseAssignments(String str) {
        Map<String, Integer> map = new HashMap<>();
        if (str == null || str.trim().isEmpty()) return map;

        String[] parts = str.split(";");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                int value = Integer.parseInt(keyValue[1].trim());
                map.put(key, value);
            }
        }
        return map;
    }
}
