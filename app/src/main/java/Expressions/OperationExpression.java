package Expressions;

public class OperationExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final String operator;
    
    public OperationExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this); // Delegate to the visitor's logic for operations
    }
    
    public Expression getLeft() { return left; }
    public Expression getRight() { return right; }
    public String getOperator() { return operator; }
}
