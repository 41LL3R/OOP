package ru.nsu.etronin.task113;

/**
 * Задача счета производных выражений.
 */

public class Main {

    /**
     * Запуск примера из задания.
     */

    public static void main(String[] args) {
        String inputExpr = "(3+(2*x))";
        Expression e = ExpressionParser.parse(inputExpr);

        System.out.println("Исходное выражение:");
        e.print();

        System.out.println("\nПроизводная по x:");
        Expression de = e.derivative("x");
        de.print();

        System.out.println("\nВычисление значения (x=10):");
        int result = e.eval("x = 10; y = 13");
        System.out.println(result);
    }
}