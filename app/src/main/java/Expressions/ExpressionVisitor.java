package Expressions;

public interface ExpressionVisitor {
    void visit(NumberExpression number);
    void visit(OperationExpression operation);
}
