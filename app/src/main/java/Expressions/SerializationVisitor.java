package Expressions;

public class SerializationVisitor implements ExpressionVisitor {
    private final StringBuilder builder = new StringBuilder();
    private double result;

    @Override
    public void visit(NumberExpression number) {
        builder.append(number.getValue()); 
    }

    @Override
    public void visit(OperationExpression operation) {
        // Process left operand (recursive)
        operation.getLeft().accept(this);
        
        // Append operator (e.g., "+")
        builder.append(" ").append(operation.getOperator()).append(" ");
        
        // Process right operand (recursive)
        operation.getRight().accept(this);
        
        // Calculate the result of this operation
        EvaluationVisitor evaluator = new EvaluationVisitor();
        operation.accept(evaluator);
        this.result = evaluator.getResult();
    }

    public String getFullMessage() {
        return builder.toString() + " = " + result;
    }

    public double getResult() {
        return result;
    }
}
