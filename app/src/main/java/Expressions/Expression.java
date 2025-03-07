package Expressions;

public interface Expression {
    void accept(ExpressionVisitor visitor);
}
