package Expressions;

public class SymbolParser {
    private final String input;
    private int pos = -1;
    private char currentChar;

    public SymbolParser(String input) {
        this.input = input.replaceAll("\\s+", "");
        advance();
    }

    private void advance() {
        pos++;
        currentChar = pos < input.length() ? input.charAt(pos) : '\0';
    }

    public Expression parse() {
        return parseExpression();
    }

    private Expression parseExpression() {
        Expression expr = parseTerm();
        while (currentChar == '+' || currentChar == '-') {
            char op = currentChar;
            advance();
            expr = new OperationExpression(expr, String.valueOf(op), parseTerm());
        }
        return expr;
    }

    private Expression parseTerm() {
        Expression expr = parseFactor();
        while (currentChar == '*' || currentChar == '/') {
            char op = currentChar;
            advance();
            expr = new OperationExpression(expr, String.valueOf(op), parseFactor());
        }
        return expr;
    }

    private Expression parseFactor() {
        if (Character.isDigit(currentChar)) {
            return parseNumber();
        } else if (currentChar == '(') {
            advance();
            Expression expr = parseExpression();
            if (currentChar != ')') throw new RuntimeException("Missing ')'");
            advance();
            return expr;
        }
        throw new RuntimeException("Unexpected character: " + currentChar);
    }

    private NumberExpression parseNumber() {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(currentChar) || currentChar == '.') {
            sb.append(currentChar);
            advance();
        }
        return new NumberExpression(Double.parseDouble(sb.toString()));
    }
}