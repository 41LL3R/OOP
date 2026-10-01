package ru.nsu.etronin.task113;

class ExpressionParser {
    private final String input;
    private int pos;

    public ExpressionParser(String input) {
        this.input = input.replace(" ", "");
        this.pos = 0;
    }

    public static Expression parse(String str) {
        ExpressionParser parser = new ExpressionParser(str);
        return parser.parseExpression();
    }

    private Expression parseExpression() {
        if (pos >= input.length()) throw new RuntimeException("Unexpected end of input");

        char c = input.charAt(pos);

        if (c == '(') {
            pos++;
            Expression left = parseExpression();

            char op = input.charAt(pos);
            pos++;

            Expression right = parseExpression();

            if (input.charAt(pos) != ')') throw new RuntimeException("Expected ')' at pos " + pos);
            pos++;

            switch (op) {
                case '+': return new Add(left, right);
                case '-': return new Sub(left, right);
                case '*': return new Mul(left, right);
                case '/': return new Div(left, right);
                default: throw new RuntimeException("Unknown operator: " + op);
            }
        } else {
            int start = pos;
            while (pos < input.length() && (Character.isLetterOrDigit(input.charAt(pos)))) {
                pos++;
            }
            String token = input.substring(start, pos);

            if (token.isEmpty()) throw new RuntimeException("Empty token at " + pos);

            if (Character.isDigit(token.charAt(0))) {
                return new Number(Integer.parseInt(token));
            } else {
                return new Variable(token);
            }
        }
    }
}