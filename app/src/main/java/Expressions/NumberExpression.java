package Expressions;

public class NumberExpression implements Expression {
    private final double value;
    
    public NumberExpression(double value) { 
        this.value = value; 
    }
    
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this); // Delegate to the visitor's logic for numbers
    }
    
    public double getValue() { return value; }
}
